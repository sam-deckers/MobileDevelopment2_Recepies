package com.sampxl.recipeapp;

public class RecipeItem {
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public RecipeItem(String imageUrl, String creator, int likes) {
        mImageUrl = imageUrl;
        mCreator = creator;
        mLikes = likes;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getCreator() {
        return mCreator;
    }

    public int getLikeCount() {
        return mLikes;
    }
}
