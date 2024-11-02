package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Desk;
import com.master_diploma.cafe.repositories.DeskRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/v1/apps")
@AllArgsConstructor
@NoArgsConstructor
public class DeskController {
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @GetMapping("/desks/{institutionId}")
    public String allDesks(@PathVariable long institutionId, Model model){
        model.addAttribute("desks", deskRepository.findByInstitutionId(institutionId));
        model.addAttribute("institutionId", institutionId);
        model.addAttribute("currentUserRole", myUserDetailsService.getCurrentUserRole());
        return "desks";
    }
    @GetMapping("/desk/{id}")
    public String byId(Model model, @PathVariable Long id){
        model.addAttribute("currentUser", myUserDetailsService.getCurrentUserId());
        Optional<Desk> deskOptional = deskRepository.findById(id);
        deskOptional.ifPresent(desk -> model.addAttribute("desk", desk));
        return "reserve";
    }
    @PostMapping("/new-desk")
    public String saveDesk(@ModelAttribute Desk desk){
        ;
        long institutionId = desk.getInstitution().getId();
        institutionRepository.findById(institutionId)
                .ifPresent(desk::setInstitution);
        deskRepository.save(desk);
        return "redirect:/api/v1/apps/desks/" + institutionId;
    }
}
