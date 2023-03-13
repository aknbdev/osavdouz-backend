//package dev.aknb.osavdouz.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//
//public class CustomAuthenticationFilter extends BasicAuthenticationFilter {
//    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//
////        try {
////
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//
//        chain.doFilter(request,response);
//    }
//
//    public Authentication convertBearerHeaderToToken(HttpServletRequest request, HttpServletResponse response) {
//
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header == null) {
//            return null;
//        }
//        if (!StringUtils.startsWithIgnoreCase(header, TokenType.BEARER.getValue())) {
//            return null;
//        }
//        if (header.equalsIgnoreCase(TokenType.BEARER.getValue())) {
//
//            return null;
//        }
//        return null;
//    }
//
//    public String convertToJson(Object object) throws JsonProcessingException {
//
//        if (object == null) {
//            return null;
//        }
//        return new ObjectMapper()
//                .writeValueAsString(object);
//    }
//}
//
//
///*
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            UsernamePasswordAuthenticationToken authRequest = this.authenticationConverter.convert(request);
//            if (authRequest == null) {
//                this.logger.trace("Did not process authentication request since failed to find username and password in Basic Authorization header");
//                chain.doFilter(request, response);
//                return;
//            }
//
//            String username = authRequest.getName();
//            this.logger.trace(LogMessage.format("Found username '%s' in Basic Authorization header", username));
//            if (this.authenticationIsRequired(username)) {
//                Authentication authResult = this.authenticationManager.authenticate(authRequest);
//                SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
//                context.setAuthentication(authResult);
//                this.securityContextHolderStrategy.setContext(context);
//                if (this.logger.isDebugEnabled()) {
//                    this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
//                }
//
//                this.rememberMeServices.loginSuccess(request, response, authResult);
//                this.securityContextRepository.saveContext(context, request, response);
//                this.onSuccessfulAuthentication(request, response, authResult);
//            }
//        } catch (AuthenticationException var8) {
//            this.securityContextHolderStrategy.clearContext();
//            this.logger.debug("Failed to process authentication request", var8);
//            this.rememberMeServices.loginFail(request, response);
//            this.onUnsuccessfulAuthentication(request, response, var8);
//            if (this.ignoreFailure) {
//                chain.doFilter(request, response);
//            } else {
//                this.authenticationEntryPoint.commence(request, response, var8);
//            }
//
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//*/