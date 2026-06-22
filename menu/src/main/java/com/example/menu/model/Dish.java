package com.example.menu.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor
@NamedEntityGraph(
        name = "Dish.withCategoryAndIngredients",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("ingredients")
        }
)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название блюда обязательно")
    @Column(nullable = false)
    private String name;

    @Positive(message = "Цена должна быть больше нуля")
    private BigDecimal price;

    private Boolean isVegetarian;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private MenuCategory category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private Set<Ingredient> ingredients = new HashSet<>();

}