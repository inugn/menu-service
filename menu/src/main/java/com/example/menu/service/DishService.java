package com.example.menu.service;

import com.example.menu.model.Dish;
import com.example.menu.dto.DishRequest;
import com.example.menu.dto.DishDTO;
import com.example.menu.model.Ingredient;
import com.example.menu.model.MenuCategory;
import com.example.menu.repository.DishRepository;
import com.example.menu.repository.IngredientRepository;
import com.example.menu.repository.MenuCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final MenuCategoryRepository categoryRepository;
    private final IngredientRepository ingredientRepository;

    public DishService(DishRepository dishRepository, MenuCategoryRepository categoryRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public DishDTO create(DishRequest request, Long categoryId) {
        MenuCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Категория не найдена"));
        Dish dish = new Dish();
        dish.setName(request.getName());
        dish.setPrice(request.getPrice());
        dish.setIsVegetarian(request.getIsVegetarian());
        dish.setCategory(category);
        dish.setIngredients(resolveIngredients(request.getIngredients()));

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

    public DishDTO update(Long id, DishRequest request) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Блюдо не найдено"));
        dish.setName(updatedDish.getName());
        dish.setPrice(updatedDish.getPrice());
        dish.setIsVegetarian(request.getIsVegetarian());

        if (request.getIngredients() != null) {
            dish.setIngredients(resolveIngredients(request.getIngredients()));
        }
        return new DishDTO(dishRepository.save(dish));
    }

    public void delete(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new RuntimeException("Блюдо не найдено");
        }
        dishRepository.deleteById(id);
    }

    private Set<Ingredient> resolveIngredients(List<String> names) {
        Set<Ingredient> result = new HashSet<>();
        if (names == null) return result;

        for (String name : names) {
            Ingredient ingredient = ingredientRepository.findByName(name)
                    .orElseGet(() -> {
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(name);
                        return ingredientRepository.save(newIngredient);
                    });
            result.add(ingredient);
        }
        return result;
    }
}