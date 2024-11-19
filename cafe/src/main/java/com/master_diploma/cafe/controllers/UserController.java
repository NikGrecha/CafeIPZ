package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.UserInstitution;
import com.master_diploma.cafe.repositories.UserInstitutionRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import com.master_diploma.cafe.services.InstitutionService;
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
    private UserInstitutionRepository userInstitutionRepository;
    @Autowired
    private InstitutionService institutionService;
    @GetMapping("/all-workers/{institutionId}")
    public String workersPage(Model model, Long roleId, @PathVariable Long institutionId){
        model.addAttribute("users", userTableService.findAllWorkers(roleId));
        model.addAttribute("institutionId", institutionId);
        return "workers";
    }
    @PostMapping("/update/role")
    public String updateRole(@RequestParam Long roleId, @RequestParam Long workerId, @RequestParam Long institutionId){
        userTableService.updateRoleUser(roleId, workerId);
        userInstitutionRepository.save(new UserInstitution(0, userTableService.findById(workerId), institutionService.findById(institutionId)));
        return "redirect:/api/v1/apps/all-workers/" + institutionId;
    }
}
