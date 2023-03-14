package dev.aknb.osavdouz.service;

import dev.aknb.osavdouz.entities.User;
import dev.aknb.osavdouz.mapper.SecurityUserMapper;
import dev.aknb.osavdouz.models.SecurityUser;
import dev.aknb.osavdouz.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElse(null);
        return SecurityUserMapper.userToSecurityUser(user);
    }
}
