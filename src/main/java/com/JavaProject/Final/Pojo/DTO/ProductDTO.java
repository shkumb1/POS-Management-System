package com.JavaProject.Final.Pojo.DTO;

import com.JavaProject.Final.Pojo.ProductType;

public class ProductDTO {
    private String name;
    private ProductType productType;
    private double price;
    private int stock;

    public ProductDTO(String name, ProductType productType, double price, int stock) {
        this.name = name;
        this.productType = productType;
        this.price = price;
        this.stock = stock;
    }

    public ProductDTO() {
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

