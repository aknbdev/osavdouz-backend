package dev.aknb.osavdouz.mapper;


import dev.aknb.osavdouz.entities.Role;
import dev.aknb.osavdouz.entities.User;
import dev.aknb.osavdouz.models.SecurityRole;
import dev.aknb.osavdouz.models.SecurityUser;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SecurityUserMapper {

    public static SecurityUser userToSecurityUser(User user) {
        if (user == null) {
            return null;
        }

        SecurityUser securityUser = new SecurityUser();

        securityUser.setId(user.getId());
        securityUser.setFirstname(user.getFirstName());
        securityUser.setLastname(user.getLastName());
        securityUser.setEmail(user.getEmail());
        securityUser.setUsername(user.getUsername());
        securityUser.setPassword(user.getPassword());
        securityUser.setPhoneNumber(user.getPhoneNumber());
        securityUser.setVerified(user.getVerified());
        securityUser.setPasswordChangedDate(user.getPasswordChangedDate());
        securityUser.setUserRoles(userRoleToSecurityRole(user.getUserRoles()));
        securityUser.setAddress(user.getAddress());
        return securityUser;
    }

    private static Set<SecurityRole> userRoleToSecurityRole(Set<Role> userRoles) {
        return userRoles.stream().map(role -> new SecurityRole(role.getName())).collect(Collectors.toSet());
    }
}
