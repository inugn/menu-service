package com.example.menu.controller;

import com.example.menu.dto.DishDTO;
import com.example.menu.dto.DishRequest;
import com.example.menu.service.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<DishDTO> create(@Valid @RequestBody DishRequest request, @RequestParam Long categoryId) {
        return ResponseEntity.ok(dishService.create(request, categoryId));
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> getAll() {
        return ResponseEntity.ok(dishService.getAll());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DishDTO>> getByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(dishService.getByCategoryId(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> update(@PathVariable Long id, @Valid @RequestBody DishRequest request) {
        return ResponseEntity.ok(dishService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        dishService.delete(id);
        return ResponseEntity.ok("Блюдо удалено");
    }
}