package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.repositories.DeskRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.repositories.ReviewRepository;
import com.master_diploma.cafe.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/apps")
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionController {
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/institutions")
    public String allInstitutions(Model model){
        model.addAttribute("currentUser", myUserDetailsService.getCurrentUserId());
        model.addAttribute("currentUserRole", myUserDetailsService.getCurrentUserRole());
        model.addAttribute("institutions", institutionRepository.findAll());
        if(myUserDetailsService.getCurrentUserRole().equals("ROLE_WAITER") || myUserDetailsService.getCurrentUserRole().equals("ROLE_COOK")) {
            model.addAttribute("institutionId", institutionRepository.findByWorkerId(myUserDetailsService.getCurrentUserId()).getId());
        }
        return "institutions";
    }
    @GetMapping("/institution/{ownerId}")
    public String institutionByOwnerId(Model model, @PathVariable Long ownerId){
        model.addAttribute("institutions", institutionRepository.findInstitutionByOwner(ownerId));
        return "ownerInstitution";
    }
}
