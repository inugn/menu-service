package com.example.menu.repository;

import  com.example.menu.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query("SELECT DISTINCT d FROM Dish d JOIN FETCH d.category LEFT JOIN FETCH d.ingredients")
    List<Dish> findAllWithCategory();

    @Query("SELECT DISTINCT d FROM Dish d JOIN FETCH d.category LEFT JOIN FETCH d.ingredients WHERE d.category.id = :categoryId")
    List<Dish> findByCategoryId(Long categoryId);
}