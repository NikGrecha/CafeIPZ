package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.*;
import com.master_diploma.cafe.repositories.*;
import com.master_diploma.cafe.services.MyUserDetailsService;
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
    private DishRepository dishRepository;
    @GetMapping("/all-institutions")
    public Iterable<Institution> allInstitutions() {
        return institutionRepository.findAll();
    }

    @PostMapping("/new-user")
    public String addUser(@RequestBody UserTable userTable) {
        userService.addUser(userTable);
        return "User is saved";
    }

    @PostMapping("/new-dish")
    public String addDish(@RequestBody Dish dish) {
        dishRepository.save(dish);
        return "Dish is saved";
    }
}
