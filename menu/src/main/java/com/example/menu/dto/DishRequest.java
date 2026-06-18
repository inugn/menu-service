package com.example.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishRequest {

    @NotBlank(message = "Название блюда обязательно")
    private String name;

    @Positive(message = "Цена должна быть больше нуля")
    private Double price;

    private Boolean isVegetarian;

    private List<String> ingredients;
}