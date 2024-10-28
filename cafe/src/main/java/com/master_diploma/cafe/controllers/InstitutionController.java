package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.repositories.DeskRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
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
    private DeskRepository deskRepository;
    @GetMapping("/institutions")
    public String allInstitutions(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        return "institutions";
    }
    @GetMapping("/desksByInstitution/{institutionId}")
    public String byInstitution(@PathVariable Long institutionId, Model model){
        model.addAttribute("desks", deskRepository.findByInstitutionId(institutionId));
        return "desks";
    }
}
