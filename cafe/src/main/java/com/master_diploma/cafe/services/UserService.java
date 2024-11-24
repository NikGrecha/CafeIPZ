package com.master_diploma.cafe.services;

import com.master_diploma.cafe.dto.UserRegistrationDto;
import com.master_diploma.cafe.models.Role;
import com.master_diploma.cafe.models.UserInstitution;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.RoleRepository;
import com.master_diploma.cafe.repositories.UserInstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private UserInstitutionRepository userInstitutionRepository;
    @Autowired
    private UserTableService userTableService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public void addUser(UserTable userTable) {
        userTable.setPassword(passwordEncoder.encode(userTable.getPassword()));
        userTableService.save(userTable);
    }
    public boolean userExists(String email) {
        return userTableService.findByEmail(email) != null;
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

        userTableService.save(newUser);
    }
    public void registerNewWorker(UserTable user, Long institutionId){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userTableService.save(user);
        userInstitutionRepository.save(new UserInstitution(0, userTableService.findById(user.getId()), institutionService.findById(institutionId), null));
    }
}
