package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.models.Role;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.repositories.RoleRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class TestController {
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private UserTableRepository userTableRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/institutions")
    public Iterable<Institution> findAllInstitutions(Model model){
        //        model.addAttribute("institutions", institutions);
        return institutionRepository.findAll();
    }

    @RequestMapping(value = "/institutions/save", method = RequestMethod.POST)
    public ResponseEntity<Institution> saveInstitution(@RequestBody Institution institution) {
        try {
            Institution savedInstitution = institutionRepository.save(institution);
            return new ResponseEntity<>(savedInstitution, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public Iterable<UserTable> findAllUsers(Model model){
        //        model.addAttribute("institutions", institutions);
        return userTableRepository.findAll();
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public ResponseEntity<UserTable> saveUser(@RequestBody UserTable user) {
        try {
            UserTable savedUser = userTableRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/roles")
    public Iterable<Role> findAllRoles(Model model){
        //        model.addAttribute("institutions", institutions);
        return roleRepository.findAll();
    }

    @RequestMapping(value = "/roles/save", method = RequestMethod.POST)
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        roleRepository.save(role);
        try {
            Role savedRole = roleRepository.save(role);
            return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
