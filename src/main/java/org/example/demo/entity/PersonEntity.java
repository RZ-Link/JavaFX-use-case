package org.example.demo.entity;

public class PersonEntity {
    private String name;
    private int age;

    public PersonEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
