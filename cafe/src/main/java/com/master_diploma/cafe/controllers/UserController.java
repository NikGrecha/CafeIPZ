package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.UserInstitution;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.repositories.RoleRepository;
import com.master_diploma.cafe.repositories.UserInstitutionRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import com.master_diploma.cafe.services.InstitutionService;
import com.master_diploma.cafe.services.MyUserDetailsService;
import com.master_diploma.cafe.services.UserService;
import com.master_diploma.cafe.services.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/apps")
public class UserController {
    @Autowired
    private UserTableService userTableService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/all-workers/{institutionId}")
    public String workersPage(Model model, Long roleId, @PathVariable Long institutionId){
        model.addAttribute("users", userTableService.findAllWorkers(roleId));
        model.addAttribute("institutionId", institutionId);
        model.addAttribute("currentUserRole", myUserDetailsService.getCurrentUserRole());
        model.addAttribute("roles", roleRepository.findAll());
        return "workers";
    }
    @PostMapping("/update/role")
    public String updateRole(@RequestParam Long roleId, @RequestParam Long workerId, @RequestParam Long institutionId){
        userTableService.updateRoleUser(roleId, workerId);
        return "redirect:/api/v1/apps/all-workers/" + institutionId;
    }
    @PostMapping("/sign-up-worker")
    public String signUpWorker(@RequestParam Long institutionId, @ModelAttribute UserTable userTable){
        userService.registerNewWorker(userTable, institutionId);
        userTableService.save(userTable);
        return "redirect:/api/v1/apps/all-workers/" + institutionId;
    }
}
