package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Desk;
import com.master_diploma.cafe.models.OrderTable;
import com.master_diploma.cafe.services.DeskService;
import com.master_diploma.cafe.services.OrderTableService;
import com.master_diploma.cafe.services.UserTableService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/apps")
@AllArgsConstructor
@NoArgsConstructor
public class OrderController {
    @Autowired
    private OrderTableService orderTableService;
    @Autowired
    private DeskService deskService;
    @Autowired
    private UserTableService userTableService;

    @GetMapping("/all-orders")
    public String findAll(Model model){
        System.out.println(deskService.findAll());
        model.addAttribute("desks", deskService.findAll());
        model.addAttribute("users", userTableService.findAll());
        model.addAttribute("orders", orderTableService.findAll());
        return "all-orders";
    }

    @PostMapping("/new-order")
    @PreAuthorize("hasAnyRole('WAITER', 'CLIENT')")
    public String save(@ModelAttribute OrderTable orderTable){
        orderTableService.save(orderTable);
        System.out.println("Order is saved");
        return "redirect:/api/v1/apps/all-orders";
    }

}
