package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.*;
import com.master_diploma.cafe.repositories.DishFavoriteDishDTORepository;
import com.master_diploma.cafe.repositories.DishOrderRepository;
import com.master_diploma.cafe.repositories.DishRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.services.DeskService;
import com.master_diploma.cafe.services.MyUserDetailsService;
import com.master_diploma.cafe.services.OrderTableService;
import com.master_diploma.cafe.services.UserTableService;
import com.master_diploma.cafe.views.DishFavoriteDishView;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    private InstitutionRepository institutionRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private DishOrderRepository dishOrderRepository;
    @Autowired
    private DishFavoriteDishDTORepository dishFavoriteDishDTORepository;
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

    @GetMapping("/menu/{deskId}")
    public String menuView(Model model, @PathVariable long deskId){
        Desk desk = deskService.findById(deskId).get();
        long institutionId = institutionRepository.findById(desk.getInstitution().getId()).get().getId();

        model.addAttribute("dishes", dishFavoriteDishDTORepository.findByInstitutionId(institutionId, myUserDetailsService.getCurrentUserId()));
        model.addAttribute("currentUserId", myUserDetailsService.getCurrentUserId());
        model.addAttribute("currentUserRole", myUserDetailsService.getCurrentUserRole());
        model.addAttribute("deskId", deskId);
        if(myUserDetailsService.getCurrentUserRole().equals("ROLE_WAITER")){
            model.addAttribute("users", userTableService.findAll());
        }
        return "menu";
    }
    @GetMapping("/user-orders/{id}")
    public String userPanelPage(Model model, @PathVariable Long id){
        myUserDetailsService.getCurrentUserId();
        model.addAttribute("orders", orderTableService.findByUserId(id));
        return "user-orders";
    }
    @PostMapping("/new-order")
    public String save(@ModelAttribute OrderTable orderTable){

        correctOrderTableObject(orderTable);

        List<DishOrder> dishOrders = orderTable.getDishOrders();
        orderTable.setDishOrders(null);
        orderTableService.save(orderTable);

        dishOrders.forEach(el -> el.setOrder(orderTable));

        long institutionId = orderTable.getDesk().getInstitution().getId();
        dishOrderRepository.saveAll(dishOrders);

        if(myUserDetailsService.getCurrentUserRole().equals("ROLE_CLIENT"))
            return "redirect:/api/v1/apps/user-orders/" + myUserDetailsService.getCurrentUserId();
        else
            return "redirect:/api/v1/apps/desks/" + institutionId;
    }

    @PostMapping("/update-status")
    public String updateStatusOrder(@RequestParam String status, @RequestParam Long id){
        orderTableService.updateStatus(status, id);
        return "redirect:/api/v1/apps/all-orders";
    }

    private void correctOrderTableObject(OrderTable orderTable){

        // Removing DishOrders with countDish = 0
        orderTable.setDishOrders(orderTable.getDishOrders().stream()
                .filter(obj -> obj.getCountDish() > 0).toList());

        orderTable.setDateOfCreation(LocalDateTime.now());

        orderTable.getDishOrders().forEach(dishOrder -> {
            Dish fullDish = dishRepository.findById(dishOrder.getDish().getId()).get();
            dishOrder.setDish(fullDish);
        });

        // Calculating total order price
        orderTable.setPrice(orderTable.getDishOrders().stream()
                .mapToDouble(dishOrder -> dishOrder.getDish().getPrice() * dishOrder.getCountDish())
                .sum());
    }
}