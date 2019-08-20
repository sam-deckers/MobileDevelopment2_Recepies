package com.sampxl.recipeapp;

public class RecipeItem {
    private String mImageUrl;
    private String mName;
    private String mSteps;

    public RecipeItem(String imageUrl, String name, String steps) {
        mImageUrl = imageUrl;
        mName = name;
        mSteps = steps;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getSteps() {
        return mSteps;
    }
}
