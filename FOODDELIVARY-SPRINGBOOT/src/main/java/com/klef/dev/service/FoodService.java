package com.klef.dev.service;

import java.util.List;
import com.klef.dev.entity.Food;

public interface FoodService {
    Food addFood(Food food);
    List<Food> getAllFoods();
    Food getFoodById(int id);
    Food updateFood(Food food);
    void deleteFoodById(int id);
}
