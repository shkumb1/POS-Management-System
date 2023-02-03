package com.JavaProject.Final.Pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productID;

    @Column(name = "product_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "prodct_type")
    private ProductType productType;

    @Column(name = "product_price")
    private double price;

    @Column(name = "stock")
    private int stock;


    public Product(Integer productID, String name, ProductType productType, double price, int stock) {
        this.productID = productID;
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

