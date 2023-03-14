package dev.aknb.osavdouz.service;

import dev.aknb.osavdouz.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenService {
    public static final String BEARER_TOKEN = "Bearer ";

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public TokenService(UserDetailsServiceImpl userDetailsService, JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.userDetailsService = userDetailsService;
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateAccessToken(String subject) {
        return generateAccessToken(new HashMap<>(), subject);
    }

    public String generateAccessToken(Map<String, Object> extraClaims, String subject) {

        Instant now = Instant.now(Clock.systemUTC());

        JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now).expiresAt(now.plus(1, ChronoUnit.DAYS)).subject(subject).build();
        claims.getClaims().putAll(extraClaims);
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean validateToken(String token) {
        try {
            this.jwtDecoder.decode(token);
            return true;
        } catch (JwtException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: ", e);
            return false;
        }
    }

    public Optional<Authentication> getAuthentication(String token) {

        Jwt claims = this.extractAllClaims(token);
        Collection<GrantedAuthority> authorities =
                claims.hasClaim("scope") ?
                        Arrays.stream(
                                claims.getClaimAsString("scope").split(",")
                        ).map(SimpleGrantedAuthority::new).collect(Collectors.toList()) :
                        Collections.emptyList();
        User user = new User(claims.getSubject(), "", authorities);
        return Optional.of(new UsernamePasswordAuthenticationToken(user, token, authorities));
    }

    public String getScope(Set<Role> roles) {

        return roles.stream().map(role -> role.getName().name()).collect(Collectors.joining(","));
    }

    public Boolean isIssuedAtAfter(String token, Instant date) {
        Jwt jwt = extractAllClaims(token);
        return Objects.requireNonNull(jwt.getIssuedAt()).isAfter(date);
    }

    public String extractEmail(String token) {

        return extractClaim(token, jwt -> jwt.getClaim("email"));
    }

    public String extractUsername(String token) {

        return extractClaim(token, Jwt::getSubject);
    }

    public <T> T extractClaim(String token, Function<Jwt, T> claimsResolver) {

        final Jwt claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Jwt extractAllClaims(String token) {

        return this.jwtDecoder.decode(token);
    }
}
