package com.example.cookingclass;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String title;
    private int imageResourceId;
    private String ingredients;
    private String instructions;

    public Recipe(String title, int imageResourceId, String ingredients, String instructions) {
        this.title = title;
        this.imageResourceId = imageResourceId;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}
