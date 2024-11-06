package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.repositories.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/apps")
public class UserController {
    @Autowired
    private UserTableRepository userTableRepository;
    @GetMapping("/all-workers")
    public String workersPage(Model model, Long roleId){
        model.addAttribute("users", userTableRepository.findAllWorkers(roleId));
        return "workers";
    }
    @PostMapping("/update/role")
    public String updateRole(@RequestParam Long roleId, @RequestParam Long id){
        userTableRepository.updateRoleUser(roleId, id);
        return "redirect:/api/v1/apps/all-workers";
    }
}
