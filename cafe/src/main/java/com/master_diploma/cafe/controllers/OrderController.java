package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.OrderTable;
import com.master_diploma.cafe.models.UserTable;
import com.master_diploma.cafe.services.DeskService;
import com.master_diploma.cafe.services.MyUserDetailsService;
import com.master_diploma.cafe.services.OrderTableService;
import com.master_diploma.cafe.services.UserTableService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/all-orders")
    public String findAll(Model model, UserTable userTable){
        model.addAttribute("currentUser", myUserDetailsService.getCurrentUserId());
        model.addAttribute("desks", deskService.findAll());
        model.addAttribute("users", userTableService.findAll());
        model.addAttribute("orders", orderTableService.findAll());
        return "all-orders";
    }

    @PostMapping("/new-order")
    public String save(@ModelAttribute OrderTable orderTable){
        orderTableService.save(orderTable);
        return "redirect:/api/v1/apps/all-orders";
    }
    @PostMapping("/update-status")
    public String updateStatusOrder(@RequestParam String status, @RequestParam Long id){
        orderTableService.updateStatus(status, id);
        return "redirect:/api/v1/apps/all-orders";
    }
}