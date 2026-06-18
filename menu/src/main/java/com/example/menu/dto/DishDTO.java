package com.example.menu.dto;
import com.example.menu.model.Dish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DishDTO {

    private Long id;
    private String name;
    private Double price;
    private Boolean isVegetarian;
    private Long categoryId;
    private String categoryName;


    public DishDTO(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.isVegetarian = dish.getIsVegetarian();
        this.categoryId = dish.getCategory().getId();
        this.categoryName = dish.getCategory().getName();
    }

}