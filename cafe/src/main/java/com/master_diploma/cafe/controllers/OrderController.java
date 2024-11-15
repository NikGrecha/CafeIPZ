package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.dto.DishOrderDTO;
import com.master_diploma.cafe.models.*;
import com.master_diploma.cafe.repositories.*;
import com.master_diploma.cafe.services.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private DishOrderRepository dishOrderRepository;
    @Autowired
    private DishFavoriteDishDTORepository dishFavoriteDishDTORepository;
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private HistoryDishOrderRepository historyDishOrderRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private DishRepository dishRepository;
    @GetMapping("/all-orders")
    public String findAll(Model model, UserTable userTable){
        model.addAttribute("currentUser", myUserDetailsService.getCurrentUserId());
        model.addAttribute("desks", deskService.findAll());
        model.addAttribute("users", userTableService.findAll());
        model.addAttribute("orders", orderTableService.findAll());
        return "all-orders";
    }
    @GetMapping("/user-orders/{id}")
    public String userPanelPage(Model model, @PathVariable Long id){
        model.addAttribute("currentUser", myUserDetailsService.getCurrentUserId());
        model.addAttribute("orders", orderTableService.findByUserId(id));
        return "user-orders";
    }
    @GetMapping("/menu/{institutionId}")
    public String menuView(Model model, @PathVariable long institutionId){
        model.addAttribute("dishes", dishFavoriteDishDTORepository.findByInstitutionId(institutionId, myUserDetailsService.getCurrentUserId()));
        model.addAttribute("dishOfTheDay", dishRepository.findDishOfTheDay(institutionId, LocalDate.now()));
        model.addAttribute("currentUserId", myUserDetailsService.getCurrentUserId());
        model.addAttribute("currentUserRole", myUserDetailsService.getCurrentUserRole());
        model.addAttribute("institutionId", institutionId);
        if(myUserDetailsService.getCurrentUserRole().equals("ROLE_WAITER")){
            model.addAttribute("users", userTableService.findAll());
        }
        return "menu";
    }
    @PostMapping("/new-order")
    public String save(@ModelAttribute DishOrderDTO dishOrderDTO){

        saveOrder(dishOrderDTO);

        if(myUserDetailsService.getCurrentUserRole().equals("ROLE_CLIENT"))
            return "redirect:/api/v1/apps/user-orders/" + myUserDetailsService.getCurrentUserId();
        else
            return "redirect:/api/v1/apps/desks/" + dishOrderDTO.getInstitutionId();
    }

    @PostMapping("/update-status")
    public String updateStatusOrder(@RequestParam String status, @RequestParam Long id){
        orderTableService.updateStatus(status, id);
        return "redirect:/api/v1/apps/all-orders";
    }
    @GetMapping("/order-details/{orderId}")
    public String orderDetailsPage(Model model, @PathVariable Long orderId) {
        OrderTable order = orderTableService.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        List<DishOrder> dishes = dishOrderRepository.findByOrderId(orderId);
        model.addAttribute("order", order);
        model.addAttribute("dishes", dishes);
        return "order-details";
    }
    // Procedure for save orderTable, dishOrders and HistoryDishOrders
    private void saveOrder(DishOrderDTO dishOrderDTO){
        // If there is no reserve yet, creating Reserve object
        Reserve reserve;

        if(dishOrderDTO.getReserveId() == 0){
            reserve = new Reserve();
            reserve.setDateOfReserve(LocalDateTime.now());
            reserve.setDesk(deskService.findRandomByInstitutionId(dishOrderDTO.getInstitutionId()));
            userTableService.findById(dishOrderDTO.getUserClientId()).ifPresent(reserve::setUser);

            reserve = reserveRepository.save(reserve);
        } else{
            reserve = reserveRepository.findById(dishOrderDTO.getReserveId()).get();
        }

        double totalPrice = 0;

        // Creating dishOrders and historyDishOrders
        List<DishOrder> dishOrders = new ArrayList<>();
        List<HistoryDishOrder> historyDishOrders = new ArrayList<>();
        for (int i = 0; i < dishOrderDTO.getCountDish().length; i++) {
            if(dishOrderDTO.getCountDish()[i] > 0){
                // count totalPrice for OrderTable
                totalPrice += dishOrderDTO.getCountDish()[i] * dishRepository.findById(dishOrderDTO.getDishId()[i]).get().getPrice();

                for(int j = 0; j < dishOrderDTO.getCountDish()[i]; j++){
                    DishOrder dishOrder = new DishOrder();
                    dishRepository.findById(dishOrderDTO.getDishId()[i]).ifPresent(dishOrder::setDish);
                    dishOrder.setUserWorker(userTableService.findAnyCook());

                    dishOrders.add(dishOrder);
                    historyDishOrders.add(new HistoryDishOrder(0, null, LocalDateTime.now(), dishOrder));
                }
            }
        }

        OrderTable orderTable = new OrderTable();
        orderTable.setWishes(dishOrderDTO.getWishes());
        orderTable.setDateOfCreation(LocalDateTime.now());
        orderTable.setPrice(totalPrice);
        orderTable.setReserve(reserve);
        userTableService.findById(dishOrderDTO.getUserClientId())
                .ifPresent(orderTable::setUser);

        orderTable = orderTableService.save(orderTable);

        OrderTable finalOrderTable = orderTable;
        dishOrders.forEach(el -> el.setOrder(finalOrderTable));

        dishOrderRepository.saveAll(dishOrders);
        historyDishOrderRepository.saveAll(historyDishOrders);
    }
}