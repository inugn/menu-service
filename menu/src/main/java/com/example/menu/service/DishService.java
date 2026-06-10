package com.example.menu.service;

import com.example.menu.model.Dish;
import com.example.menu.model.MenuCategory;
import com.example.menu.repository.DishRepository;
import com.example.menu.repository.MenuCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final MenuCategoryRepository categoryRepository;

    public DishService(DishRepository dishRepository, MenuCategoryRepository categoryRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
    }

    public Dish create(Dish dish, Long categoryId) {
        MenuCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));
        dish.setCategory(category);
        return dishRepository.save(dish);
    }

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public List<Dish> getByCategoryId(Long categoryId) {
        return dishRepository.findByCategoryId(categoryId);
    }

    public Dish update(Long id, Dish updatedDish) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Блюдо не найдено"));
        dish.setName(updatedDish.getName());
        dish.setPrice(updatedDish.getPrice());
        return dishRepository.save(dish);
    }

    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}