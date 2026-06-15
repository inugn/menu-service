package com.example.menu.model;

public class DishDTO {

    private Long id;
    private String name;
    private Double price;
    private Boolean isVegetarian;
    private Long categoryId;
    private String categoryName;

    public DishDTO() {}

    public DishDTO(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();
        this.isVegetarian = dish.getIsVegetarian();
        this.categoryId = dish.getCategory().getId();
        this.categoryName = dish.getCategory().getName();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getIsVegetarian() { return isVegetarian; }
    public void setIsVegetarian(Boolean isVegetarian) { this.isVegetarian = isVegetarian; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}