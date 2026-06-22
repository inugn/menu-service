package com.example.menu.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishRequest {

    private String name;

    @Positive(message = "Цена должна быть больше нуля")
    private BigDecimal price;

    private Boolean isVegetarian;

    private List<String> ingredients;
}