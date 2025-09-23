package com.klef.dev.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.dev.entity.Food;
import com.klef.dev.service.FoodService;

@RestController
@RequestMapping("/foodapi")
@CrossOrigin(origins = "http://localhost:5173")
  // Allow your frontend (React/Angular)
public class FoodController {

    @Autowired
    private FoodService foodService;

    // Health check
    @GetMapping("/")
    public String home() {
        return "Food Delivery API is running...";
    }

    // Add new food
    @PostMapping("/add")
    public ResponseEntity<Food> addFood(@RequestBody Food food) {
        Food savedFood = foodService.addFood(food);
        return new ResponseEntity<>(savedFood, HttpStatus.CREATED);
    }

    // Get all foods
    @GetMapping("/all")
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    // Get food by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable int id) {
        Food food = foodService.getFoodById(id);
        if (food != null) {
            return new ResponseEntity<>(food, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Food with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Update food
    @PutMapping("/update")
    public ResponseEntity<?> updateFood(@RequestBody Food food) {
        Food existing = foodService.getFoodById(food.getId());
        if (existing != null) {
            Food updatedFood = foodService.updateFood(food);
            return new ResponseEntity<>(updatedFood, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Food with ID " + food.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete food
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable int id) {
        Food existing = foodService.getFoodById(id);
        if (existing != null) {
            foodService.deleteFoodById(id);
            return new ResponseEntity<>("Food with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Food with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
