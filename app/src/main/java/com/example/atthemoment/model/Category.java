package com.example.atthemoment.model;

public class Category {
    private String categoryId;
    private String categoryName;
    private boolean hasTimeTables;
    private Object icons;
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isHasTimeTables() {
        return hasTimeTables;
    }

    public void setHasTimeTables(boolean hasTimeTables) {
        this.hasTimeTables = hasTimeTables;
    }

    public Object getIcons() {
        return icons;
    }

    public void setIcons(Object icons) {
        this.icons = icons;
    }
}
