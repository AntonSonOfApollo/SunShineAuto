package com.example.diplom.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "car_t")

public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;

    @Column(name="year_f", nullable = false)
    private Integer year;

    @Column(name="description_f", nullable = true)
    private String description;

    @Column(name="price_f", nullable = false)
    private Integer price;

    @ManyToOne(optional = false)  //
    @JoinColumn(name="owner_id", unique = true, nullable = false, updatable = false)
    public Owner owner;

    @OneToMany(mappedBy = "car")
    private Set<Order> orders;


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Car() {
        id = 0;
        name = "";
        year = 0;
        description = null;
        price = 0;
        owner = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return  " Марка машины ='" + name + '\'' +
                " Год выпуска =" + year + '\'' +
                " Описание = '" + description + '\'' +
                " Начальная цена = " + price;
    }

}
