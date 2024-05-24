package org.example.demo.entity;

public class PersonEntity {
    private Long id;
    private String name;
    private Integer age;

    public PersonEntity(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
