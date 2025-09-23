package com.klef.dev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_table")
public class Food {
    
    @Id
    @Column(name = "food_id")
    private int id;

    @Column(name = "food_name", nullable = false, length = 50)
    private String name;

    @Column(name = "food_category", nullable = false, length = 30)
    private String category;

    @Column(name = "food_price", nullable = false)
    private double price;

    @Column(name = "food_description", length = 200)
    private String description;

    @Column(name = "food_availability", nullable = false, length = 20)
    private String availability;  // AVAILABLE or OUT_OF_STOCK

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Food [id=" + id + ", name=" + name + ", category=" + category +
               ", price=" + price + ", description=" + description +
               ", availability=" + availability + "]";
    }
}
