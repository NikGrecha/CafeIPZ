package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.OrderTable;
import com.master_diploma.cafe.services.DeskService;
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

    @GetMapping("/all-orders")
    public String findAll(Model model){
        model.addAttribute("desks", deskService.findAll());
        model.addAttribute("users", userTableService.findAll());
        model.addAttribute("orders", orderTableService.findAll());
        return "all-orders";
    }

    @PostMapping("/new-order")
    public String save(@ModelAttribute OrderTable orderTable){
        orderTableService.save(orderTable);
        System.out.println("Order is saved");
        return "redirect:/api/v1/apps/all-orders";
    }
    @PostMapping("/update-status")
    public String updateStatusOrder(@RequestParam String status, @RequestParam Long id){
        orderTableService.updateStatus(status, id);
        System.out.println("Successful");
        return "redirect:/api/v1/apps/all-orders";
    }
}