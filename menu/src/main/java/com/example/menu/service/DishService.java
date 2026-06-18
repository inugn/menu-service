package com.example.menu.service;

import com.example.menu.model.Dish;
import com.example.menu.dto.DishDTO;
import com.example.menu.model.MenuCategory;
import com.example.menu.repository.DishRepository;
import com.example.menu.repository.MenuCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final MenuCategoryRepository categoryRepository;

    public DishService(DishRepository dishRepository, MenuCategoryRepository categoryRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
    }

    public DishDTO create(Dish dish, Long categoryId) {
        MenuCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));
        dish.setCategory(category);
        return new DishDTO(dishRepository.save(dish));
    }

    public List<DishDTO> getAll() {
        return dishRepository.findAllWithCategory().stream()
                .map(DishDTO::new)
                .collect(Collectors.toList());
    }

    public List<DishDTO> getByCategoryId(Long categoryId) {
        return dishRepository.findByCategoryId(categoryId).stream()
                .map(DishDTO::new)
                .collect(Collectors.toList());
    }

    public DishDTO update(Long id, Dish updatedDish) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Блюдо не найдено"));
        dish.setName(updatedDish.getName());
        dish.setPrice(updatedDish.getPrice());
        return new DishDTO(dishRepository.save(dish));
    }

    public void delete(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new RuntimeException("Блюдо не найдено");
        }
        dishRepository.deleteById(id);
    }
}