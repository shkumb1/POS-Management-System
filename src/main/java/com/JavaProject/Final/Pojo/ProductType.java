package com.JavaProject.Final.Pojo;

public enum ProductType {
    DRINK("Drink"),
    FOOD("Food");

    String text;

    ProductType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

}
