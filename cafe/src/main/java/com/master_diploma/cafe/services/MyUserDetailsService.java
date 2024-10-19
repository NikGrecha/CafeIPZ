package com.master_diploma.cafe.services;

import com.master_diploma.cafe.config.MyUserDetails;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.UserTableRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserTableRepository userTableRepository;

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<UserTable> user = Optional.ofNullable(userTableRepository.findByFirstName(firstName));
        user.ifPresent(u -> System.out.println("Roles: " + u.getRole()));
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(firstName + " not found"));
    }
}
