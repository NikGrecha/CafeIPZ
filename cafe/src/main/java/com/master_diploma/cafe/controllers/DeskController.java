package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Desk;
import com.master_diploma.cafe.repositories.DeskRepository;
import com.master_diploma.cafe.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("api/v1/apps")
@AllArgsConstructor
@NoArgsConstructor
public class DeskController {
    @Autowired
    private DeskRepository deskRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @GetMapping("/desks")
    public String allDesks(Model model){
        model.addAttribute("desks", deskRepository.findAll());
        return "desks";
    }
    @GetMapping("/desk/{id}")
    public String byId(Model model, @PathVariable Long id){
        model.addAttribute("currentUser", myUserDetailsService.getCurrentUserId());
        Optional<Desk> deskOptional = deskRepository.findById(id);
        deskOptional.ifPresent(desk -> model.addAttribute("desk", desk));
        return "reserve";
    }
}
