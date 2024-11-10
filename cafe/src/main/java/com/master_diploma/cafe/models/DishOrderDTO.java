package com.master_diploma.cafe.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishOrderDTO {
    private long deskId;
    private long[] dishId;
    private int[] countDish;
    private long[] userWorkerId;
    private String wishes;
    private long userClientId;
    private long reserveId;
}
