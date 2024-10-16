package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Desk;
import com.master_diploma.cafe.models.Dish;
import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.DeskRepository;
import com.master_diploma.cafe.repositories.DishRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import com.master_diploma.cafe.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class LoginTestController {
    private InstitutionRepository institutionRepository;
    private UserService userService;
    private DeskRepository deskRepository;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }

    @GetMapping("/all-institutions")
//    @PreAuthorize("hasAuthority('Waiter')")
    public Iterable<Institution> allInstitutions() {
        return institutionRepository.findAll();
    }

    @GetMapping("/all-desks")
//    @PreAuthorize("hasAuthority('cook')")
    public Iterable<Desk> allDesks() {
        return deskRepository.findAll();
    }
    @PostMapping("/new-user")
    public String addUser(@RequestBody UserTable userTable) {
        userService.addUser(userTable);
        return "User is saved";
    }
}
