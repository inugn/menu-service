package com.example.menu.controller;

import com.example.menu.model.MenuCategory;
import com.example.menu.service.MenuCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class MenuCategoryController {

    private final MenuCategoryService categoryService;

    public MenuCategoryController(MenuCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<MenuCategory> create(@RequestBody MenuCategory category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @GetMapping
    public ResponseEntity<List<MenuCategory>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            categoryService.delete(id);
            return ResponseEntity.ok("Категория удалена");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}