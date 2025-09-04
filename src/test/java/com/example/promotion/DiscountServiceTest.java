/*
package com.example.promotion;

import com.example.promotion.model.DiscountResult;
import com.example.promotion.model.Product;
import com.example.promotion.service.temp.DiscountService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DiscountServiceTest {

    @Autowired
    private DiscountService2 discountService;

    @Test
    public void testHighValueDiscount() {
        // Test high value product (> $100)
        Product laptop = new Product("1", "Laptop", 1200.0);
        DiscountResult result = discountService.evaluateDiscount(laptop);
        
        assertNotNull(result);
        assertEquals(1200.0, result.getOriginalPrice(), 0.01);
        assertEquals(1080.0, result.getDiscountedPrice(), 0.01); // 90% of 1200
        assertEquals("10% off", result.getPromotionApplied());
        
        System.out.println("High Value Test Result:");
        System.out.println("Product: " + laptop.getName());
        System.out.println("Original Price: $" + result.getOriginalPrice());
        System.out.println("Discounted Price: $" + result.getDiscountedPrice());
        System.out.println("Promotion: " + result.getPromotionApplied());
    }

    @Test
    public void testLowValueDiscount() {
        // Test low value product (<= $100)
        Product book = new Product("2", "Book", 50.0);
        DiscountResult result = discountService.evaluateDiscount(book);
        
        assertNotNull(result);
        assertEquals(50.0, result.getOriginalPrice(), 0.01);
        assertEquals(47.5, result.getDiscountedPrice(), 0.01); // 95% of 50
        assertEquals("5% off", result.getPromotionApplied());
        
        System.out.println("\nLow Value Test Result:");
        System.out.println("Product: " + book.getName());
        System.out.println("Original Price: $" + result.getOriginalPrice());
        System.out.println("Discounted Price: $" + result.getDiscountedPrice());
        System.out.println("Promotion: " + result.getPromotionApplied());
    }
}
*/
