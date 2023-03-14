package dev.aknb.osavdouz.config;

import dev.aknb.osavdouz.models.SecurityUser;
import dev.aknb.osavdouz.resolver.MessageResolver;
import dev.aknb.osavdouz.response.Response;
import dev.aknb.osavdouz.service.TokenService;
import dev.aknb.osavdouz.service.UserDetailsServiceImpl;
import dev.aknb.osavdouz.utils.ObjectUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends BasicAuthenticationFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final MessageResolver messageResolver;
    private final TokenService tokenService;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, MessageResolver messageResolver, TokenService tokenService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.messageResolver = messageResolver;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            String token = getTokenOrElseNull(request.getHeader(HttpHeaders.AUTHORIZATION));

            if (token == null) {
                setErrorDataToResponse(response, "BAD_CREDENTIALS");
                return;
            }

            Optional<Authentication> auth = getAuthenticationOrElseNull(token);

            if (auth.isPresent()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(
                        ((User) auth.get().getPrincipal()).getUsername());

                if (userDetails == null ||
                    !tokenService.isIssuedAtAfter(token,
                            ((SecurityUser) userDetails).getPasswordChangedDate())) {

                    setErrorDataToResponse(response, "BAD_CREDENTIALS");
                    return;
                }
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(userDetails, auth.get().getCredentials(), auth.get().getAuthorities()));
                chain.doFilter(request, response);
            }
            SecurityContextHolder.getContext().setAuthentication(null);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<Authentication> getAuthenticationOrElseNull(String token) {

        if (StringUtils.isNotBlank(token) && tokenService.validateToken(token)) {
            return tokenService.getAuthentication(token);
        }
        return Optional.empty();
    }

    public void setErrorDataToResponse(HttpServletResponse response, String code) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                ObjectUtils.convertToJson(
                        Response.error(messageResolver.getMessage(code), code)));
    }

    public Boolean hasBearerToken(String bearerToken) {

        return bearerToken != null &&
               bearerToken.startsWith(TokenService.BEARER_TOKEN) &&
               !bearerToken.equalsIgnoreCase(TokenService.BEARER_TOKEN);
    }

    public String getTokenOrElseNull(String bearerToken) {

        if (hasBearerToken(bearerToken)) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }
}
