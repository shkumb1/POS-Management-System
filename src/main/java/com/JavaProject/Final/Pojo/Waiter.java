package com.JavaProject.Final.Pojo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "waiters")
public class Waiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiter_id")
    private Integer waiterID;

    @Column(name = "name")
    private String name;


    public Integer getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(Integer waiterID) {
        this.waiterID = waiterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "waiter")
    private List<Order> orders;

    public Waiter(Integer waiterID, String name, List<Order> orders) {
        this.waiterID = waiterID;
        this.name = name;
        this.orders = orders;
    }
    public Waiter() {
    }
}
