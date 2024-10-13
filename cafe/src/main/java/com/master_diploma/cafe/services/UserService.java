package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserTableRepository userTableRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserTableRepository userTableRepository, PasswordEncoder passwordEncoder) {
        this.userTableRepository = userTableRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void addUser(UserTable userTable) {
        userTable.setPassword(passwordEncoder.encode(userTable.getPassword()));
        userTableRepository.save(userTable);
    }
}
