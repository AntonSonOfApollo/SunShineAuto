package com.example.diplom.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "owner_t")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="age_f", nullable = false)
    private Integer age;

    @Column(name="name_f", nullable = false)
    private String name;

    @Column(name="phoneNumber_f", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private Set<Car> cars;

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Owner() {
        id = 0;
        age = 0;
        name = "";
        phoneNumber ="";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return   "Имя=" + name +
                ", Возраст=" + age +
                ", Номер телефона :" + phoneNumber ;
    }
}
