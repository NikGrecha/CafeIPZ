package com.master_diploma.cafe.services;

import com.master_diploma.cafe.config.MyUserDetails;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserTableRepository userTableRepository;
    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<UserTable> user = Optional.ofNullable(userTableRepository.findByFirstName(firstName));
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(firstName + " not found"));
    }
}
