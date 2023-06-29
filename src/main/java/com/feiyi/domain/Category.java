package com.feiyi.domain;

import lombok.Data;

@Data
public class Category {
    private int id;
    private String categoryname;//非遗类别名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Category(int id, String categoryname) {
        this.id = id;
        this.categoryname = categoryname;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryname='" + categoryname + '\'' +
                '}';
    }
}
