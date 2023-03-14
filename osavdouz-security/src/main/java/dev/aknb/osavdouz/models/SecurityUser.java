package dev.aknb.osavdouz.models;

import dev.aknb.osavdouz.entities.address.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SecurityUser implements UserDetails {

    private Long id;
    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private String phoneNumber;

    private Boolean verified = Boolean.FALSE;
    private Instant passwordChangedDate = Instant.now(Clock.systemUTC());

    private Set<SecurityRole> userRoles = new HashSet<>();

    private Address address;

    private Long addressId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return verified;
    }
}
