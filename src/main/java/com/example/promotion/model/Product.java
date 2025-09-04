package com.example.promotion.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private double discountedPrice;
    private String promotionApplied;

    public Product() {}
    
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountedPrice = price;
        this.promotionApplied = "No promotion";
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }
    public String getPromotionApplied() { return promotionApplied; }
    public void setPromotionApplied(String promotionApplied) { this.promotionApplied = promotionApplied; }
}
