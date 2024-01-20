package com.example.diplom.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "order_t")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="description_f", nullable = true)
    private String description;

    @Column(name="phoneNumber_f", nullable = true)
    private String phoneNumber;

    @Column(name="price_f", nullable = true)
    private Integer price;

    @Column(name="name_f", nullable = false)
    private String name;

    @ManyToOne(optional = false)  //
    @JoinColumn(name="car_id", unique = true, nullable = false, updatable = false)
    public Car car;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order() {
        id = 0;
        name = null;
        description = null;
        car = null;
        price = 0;
        phoneNumber = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", Дополнительная информация:'" + description + '\'' +
                ", name='" + name + '\'' +
                ", car=" + car +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
