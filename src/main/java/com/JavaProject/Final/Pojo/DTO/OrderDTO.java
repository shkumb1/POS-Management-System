package com.JavaProject.Final.Pojo.DTO;

import com.JavaProject.Final.Pojo.Tables;
import com.JavaProject.Final.Pojo.Waiter;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Date orderDate;
    private double totalPrice;
    private List<OrderProductDTO> orderProducts;
    private Waiter waiter;
    private Tables table;

    public OrderDTO(Date orderDate, double totalPrice, List<OrderProductDTO> orderProducts,Waiter waiter, Tables table) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderProducts = orderProducts;
        this.waiter = waiter;
        this.table = table;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }
}

