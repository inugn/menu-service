package com.example.menu.repository;

import com.example.menu.model.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
}
