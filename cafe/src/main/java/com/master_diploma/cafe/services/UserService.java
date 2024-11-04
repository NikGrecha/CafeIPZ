package com.master_diploma.cafe.services;

import com.master_diploma.cafe.dto.UserRegistrationDto;
import com.master_diploma.cafe.models.Role;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.RoleRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserTableRepository userTableRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserTableRepository userTableRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userTableRepository = userTableRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }
    public void addUser(UserTable userTable) {
        userTable.setPassword(passwordEncoder.encode(userTable.getPassword()));
        userTableRepository.save(userTable);
    }
    public boolean userExists(String email) {
        return userTableRepository.findByEmail(email).isPresent();
    }
    public void registerNewUser(UserRegistrationDto registrationDto) {
        UserTable newUser = new UserTable();
        newUser.setFirstName(registrationDto.getFirstName());
        newUser.setLastName(registrationDto.getLastName());
        newUser.setPhone(registrationDto.getPhone());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role defaultRole = roleRepository.findByName("ROLE_CLIENT")
                .orElseThrow(() -> new RuntimeException("Роль ROLE_CLIENT не найдена"));
        newUser.setRole(defaultRole);
        newUser.setRole(defaultRole);

        userTableRepository.save(newUser);
    }
}
