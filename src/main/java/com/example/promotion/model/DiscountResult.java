package com.example.promotion.model;

public class DiscountResult {
    private String productId;
    private double originalPrice;
    private double discountedPrice;
    private String promotionApplied;

    public DiscountResult() {}
    public DiscountResult(String productId, double originalPrice, double discountedPrice, String promotionApplied) {
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.promotionApplied = promotionApplied;
    }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(double originalPrice) { this.originalPrice = originalPrice; }
    public double getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(double discountedPrice) { this.discountedPrice = discountedPrice; }
    public String getPromotionApplied() { return promotionApplied; }
    public void setPromotionApplied(String promotionApplied) { this.promotionApplied = promotionApplied; }
}
