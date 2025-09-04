/*
package com.example.promotion.service.temp;

import com.example.promotion.model.Product;
import com.example.promotion.model.DiscountResult;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.evrete.api.StatefulSession;
import org.evrete.dsl.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DiscountService1 {

    private final KnowledgeService service;
    private final StatefulSession session;

    public DiscountService1() {
        this.service = new KnowledgeService();
        try {
            System.out.println("Initializing DiscountService...");
            Knowledge knowledge = service.newKnowledge();
            
            // Register the Product class
            knowledge.builder()
                .declareFact("$p", Product.class);
            
            System.out.println("Importing rules from DiscountRuleSet...");
            knowledge = knowledge.importRules("JAVA-CLASS", DiscountRuleSet.class);
            
            System.out.println("Creating new stateful session...");
            this.session = knowledge.newStatefulSession();
            System.out.println("DiscountService initialized successfully.");
        } catch (IOException e) {
            System.err.println("Error initializing DiscountService: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public DiscountResult evaluateDiscount(Product product) {
        try {
            session.clear();
            
            // Initialize arrays for the @Value parameters
            Double[] discountedPriceArray = new Double[1];
            String[] promotionAppliedArray = new String[1];
            
            System.out.println("Before rule execution:");
            System.out.println("Initial discountedPriceArray[0]: " + discountedPriceArray[0]);
            System.out.println("Initial promotionAppliedArray[0]: " + promotionAppliedArray[0]);
            
            // Set the arrays in session memory
            session.set("discountedPrice", discountedPriceArray);
            session.set("promotionApplied", promotionAppliedArray);
            
            // Debug session state
            System.out.println("\nSession memory before insert:");
            System.out.println("discountedPrice in memory: " + session.get("discountedPrice"));
            System.out.println("promotionApplied in memory: " + session.get("promotionApplied"));
            
            // Insert and fire
            System.out.println("\nInserting product: " + product.getName() + " with price: $" + product.getPrice());
            session.insert(product);
            System.out.println("Product inserted, now firing rules...");
            session.fire();
            
            // Get values from the arrays
            Double discountedPrice = discountedPriceArray[0];
            String promotionApplied = promotionAppliedArray[0];
            
            System.out.println("\nAfter rule execution:");
            System.out.println("Final discountedPriceArray[0]: " + discountedPriceArray[0]);
            System.out.println("Final promotionAppliedArray[0]: " + promotionAppliedArray[0]);
            
            if (discountedPrice == null) {
                discountedPrice = product.getPrice();
            }
            if (promotionApplied == null) {
                promotionApplied = "No promotion";
            }

            return new DiscountResult(
                product.getId(),
                product.getPrice(),
                discountedPrice,
                promotionApplied
            );
        } catch (Exception e) {
            e.printStackTrace();  // Print the stack trace for debugging
            return new DiscountResult(
                product.getId(),
                product.getPrice(),
                product.getPrice(),
                "No promotion (error): " + e.getMessage()
            );
        }
    }

    @RuleSet
    public static class DiscountRuleSet {

        @Rule
        @Where("$p.getPrice() > 100.0")
        public void highValueDiscount(@Fact("$p") Product product, @Value("discountedPrice") Double[] discountedPrice, @Value("promotionApplied") String[] promotionApplied) {
            System.out.println("\nExecuting high value discount rule:");
            System.out.println("Product: " + product.getName() + ", Price: $" + product.getPrice());
            System.out.println("Before calculation - discountedPrice[0]: " + discountedPrice[0]);
            
            discountedPrice[0] = product.getPrice() * 0.9;
            promotionApplied[0] = "10% off";
            
            System.out.println("After calculation - discountedPrice[0]: " + discountedPrice[0]);
            System.out.println("Applied promotion: " + promotionApplied[0]);
        }

        @Rule
        @Where("$p.getPrice() <= 100.0")
        public void lowValueDiscount(@Fact("$p") Product product, @Value("discountedPrice") Double[] discountedPrice, @Value("promotionApplied") String[] promotionApplied) {
            System.out.println("Low value rule triggered for product: " + product.getName());
            System.out.println("\nExecuting low value discount rule:");
            System.out.println("Product: " + product.getName() + ", Price: $" + product.getPrice());
            System.out.println("Before calculation - discountedPrice[0]: " + discountedPrice[0]);
            
            discountedPrice[0] = product.getPrice() * 0.95;
            promotionApplied[0] = "5% off";
            
            System.out.println("After calculation - discountedPrice[0]: " + discountedPrice[0]);
            System.out.println("Applied promotion: " + promotionApplied[0]);
        }
    }
}
*/
