package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.UserInstitution;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.models.WorkSchedule;
import com.master_diploma.cafe.repositories.RoleRepository;
import com.master_diploma.cafe.repositories.UserInstitutionRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import com.master_diploma.cafe.repositories.WorkScheduleRepository;
import com.master_diploma.cafe.services.InstitutionService;
import com.master_diploma.cafe.services.MyUserDetailsService;
import com.master_diploma.cafe.services.UserService;
import com.master_diploma.cafe.services.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

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
    private WorkScheduleRepository workScheduleRepository;
    @Autowired
    private UserInstitutionRepository userInstitutionRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/all-workers/{institutionId}")
    public String workersPage(Model model, @PathVariable Long institutionId){
        model.addAttribute("workers", userTableService.findAllWorkersByInstitutionId(institutionId));
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
    @GetMapping("/work-schedule-management/{institutionId}")
    public String adminPage(Model model, @PathVariable Long institutionId){
        model.addAttribute("workerScheduleDTOs", workScheduleRepository.findWorkerScheduleByInstitutionId(institutionId));
        model.addAttribute("workerInstitutionDTOs", userTableService.findAllWorkerInstitutionByInstitutionId(institutionId));
        model.addAttribute("institutionId", institutionId);
        return "work-schedule";
    }
    @PostMapping("/create-schedule")
    public String createSchedule(@ModelAttribute WorkSchedule workSchedule, @RequestParam Long institutionId){
        workScheduleRepository.save(workSchedule);
        return "redirect:/api/v1/apps/work-schedule-management/" + institutionId;
    }
    @PostMapping("/set-hoursOfWork")
    public String setHoursOfWork(@RequestParam long workScheduleId, @RequestParam LocalTime hoursOfWork, @RequestParam long institutionId){
        workScheduleRepository.updateHoursOfWork(workScheduleId, hoursOfWork);
        return "redirect:/api/v1/apps/work-schedule-management/" + institutionId;
    }
}
