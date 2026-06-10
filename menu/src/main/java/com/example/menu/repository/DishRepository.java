package com.example.menu.repository;

import com.example.menu.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByCategoryId(Long categoryId);
}