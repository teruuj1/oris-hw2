package ru.itis304.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis304.entity.Role;
import ru.itis304.entity.User;
import ru.itis304.repository.RoleRepository;
import ru.itis304.repository.UserRepository;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//    @Transactional
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role defaultRole = roleRepository.findRoleByName("user")
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName("user");
                    return roleRepository.save(role);
                });
        user.setRoles(Set.of(defaultRole));

        return userRepository.save(user);
    }
}