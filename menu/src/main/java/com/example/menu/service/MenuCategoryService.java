package com.example.menu.service;

import com.example.menu.model.MenuCategory;
import com.example.menu.repository.DishRepository;
import com.example.menu.repository.MenuCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuCategoryService {

    private final MenuCategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    public MenuCategoryService(MenuCategoryRepository categoryRepository, DishRepository dishRepository) {
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    public MenuCategory create(MenuCategory category) {
        return categoryRepository.save(category);
    }

    public List<MenuCategory> getAll() {
        return categoryRepository.findAll();
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Категория не найдена");
        }
        if (!dishRepository.findByCategoryId(id).isEmpty()) {
            throw new RuntimeException("Нельзя удалить категорию, в которой уже есть блюда");
        }
        categoryRepository.deleteById(id);
    }
}