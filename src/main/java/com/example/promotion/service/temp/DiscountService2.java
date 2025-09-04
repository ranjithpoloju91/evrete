package com.example.promotion.service.temp;

import com.example.promotion.model.Product;
import com.example.promotion.model.DiscountResult;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.evrete.api.StatefulSession;
import org.springframework.stereotype.Service;

@Service
public class DiscountService2 {

    private final KnowledgeService service;
    private final StatefulSession session;

    public DiscountService2() {
        System.out.println("Initializing DiscountService...");
        this.service = new KnowledgeService();
        
        // Create knowledge base with fluent API
        Knowledge knowledge = service.newKnowledge();
        
        // High value discount rule
        knowledge.builder()
            .newRule("HighValueDiscount")
            .forEach("$p", Product.class)
            .where("$p.getPrice() > 100")
            .execute(ctx -> {
                Product p = ctx.get("$p");
                System.out.println("High value rule executing for: " + p.getName() + " with price: " + p.getPrice());
                double discountedPrice = p.getPrice() * 0.9;
                p.setDiscountedPrice(discountedPrice);
                p.setPromotionApplied("10% off");
            })
            .build();

        // Low value discount rule
        knowledge.builder()
            .newRule("LowValueDiscount")
            .forEach("$p", Product.class)
            .where("$p.getPrice() <= 100")
            .execute(ctx -> {
                Product p = ctx.get("$p");
                System.out.println("Low value rule executing for: " + p.getName() + " with price: " + p.getPrice());
                double discountedPrice = p.getPrice() * 0.95;
                p.setDiscountedPrice(discountedPrice);
                p.setPromotionApplied("5% off");
            })
            .build();

        System.out.println("Creating new stateful session...");
        this.session = knowledge.newStatefulSession();
        System.out.println("DiscountService initialized successfully.");
    }

    public DiscountResult evaluateDiscount(Product product) {
        try {
            session.clear();
            System.out.println("\nProcessing discount for product:");
            System.out.println("Name: " + product.getName());
            System.out.println("Price: $" + product.getPrice());
            
            // Insert and fire
            session.insert(product);
            System.out.println("Product inserted, firing rules...");
            session.fire();
            
            // Get results
            Double discountedPrice = (Double) session.get("discountedPrice");
            String promotionApplied = (String) session.get("promotionApplied");
            
            System.out.println("\nRule execution results:");
            System.out.println("Discounted price: " + discountedPrice);
            System.out.println("Promotion applied: " + promotionApplied);
            
            if (discountedPrice == null) {
                System.out.println("Warning: No discount was applied, using original price");
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
            System.err.println("Error processing discount:");
            e.printStackTrace();
            return new DiscountResult(
                product.getId(),
                product.getPrice(),
                product.getPrice(),
                "No promotion (error): " + e.getMessage()
            );
        }
    }
}
