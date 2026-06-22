package com.example.menu.dto;
import com.example.menu.model.Dish;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DishDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean isVegetarian;
    private Long categoryId;
    private String categoryName;
    private List<String> ingredients;


    public DishDTO(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.isVegetarian = dish.getIsVegetarian();
        this.categoryId = dish.getCategory().getId();
        this.categoryName = dish.getCategory().getName();
        this.ingredients = dish.getIngredients().stream()
                .map(ingredient -> ingredient.getName())
                .collect(Collectors.toList());
    }

}