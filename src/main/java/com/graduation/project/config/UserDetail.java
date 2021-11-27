package com.graduation.project.config;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserDetail implements UserDetailsService {
    private final UserRepository repository;

    public UserDetail(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user from database (throw exception if not found)
        User user = repository.findByName(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<Role> roleSet = user.getRoles();
        List<String> roles = new ArrayList<>();
        for (Role role : roleSet){
            roles.add(role.getAuthority());
        }
        String[] authorities = (String[]) roles.toArray();

        // Return user object
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(authorities)
        );
    }
}
