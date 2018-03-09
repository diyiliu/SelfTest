package com.diyiliu.model;

/**
 * Description: Dog
 * Author: DIYILIU
 * Update: 2017-08-09 09:17
 */
public class Dog extends Animal {

    private Integer age;

    public Dog() {

    }

    public Dog(String name, Integer age) {
        super(name);
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
