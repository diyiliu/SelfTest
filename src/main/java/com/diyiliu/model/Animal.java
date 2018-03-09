package com.diyiliu.model;

/**
 * Description: Animal
 * Author: DIYILIU
 * Update: 2017-08-09 09:17
 */
public class Animal {

    protected String name;

    public Animal() {
        this.name = "动物";
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
