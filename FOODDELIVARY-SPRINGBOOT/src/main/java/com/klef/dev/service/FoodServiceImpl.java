package com.klef.dev.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.dev.entity.Food;
import com.klef.dev.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(int id) {
        Optional<Food> opt = foodRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Food updateFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void deleteFoodById(int id) {
        foodRepository.deleteById(id);
    }
}
