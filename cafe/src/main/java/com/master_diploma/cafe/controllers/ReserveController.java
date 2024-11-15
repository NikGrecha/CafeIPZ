package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Desk;
import com.master_diploma.cafe.models.Reserve;
import com.master_diploma.cafe.repositories.DeskRepository;
import com.master_diploma.cafe.repositories.ReserveRepository;
import com.master_diploma.cafe.services.DeskService;
import com.master_diploma.cafe.services.MyUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/apps")
@AllArgsConstructor
@NoArgsConstructor
public class ReserveController {
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private DeskService deskService;

    @PostMapping("/new-reserve")
    public String saveReserve(@ModelAttribute Reserve reserve){
        reserve = reserveRepository.save(reserve);
        long institutionId = deskService.findById(reserve.getDesk().getId()).get().getInstitution().getId();
        return "redirect:/api/v1/apps/menu/" + institutionId + "?reserveId=" + reserve.getId();
    }

}