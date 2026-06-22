package com.example.menu.repository;

import  com.example.menu.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    boolean existsByNameAndCategoryId(String name, Long categoryId);

    @EntityGraph("Dish.withCategoryAndIngredients")
    List<Dish> findAll();

    @EntityGraph("Dish.withCategoryAndIngredients")
    List<Dish> findByCategoryId(Long categoryId);
}