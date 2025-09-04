/*
package com.example.promotion.service;

import com.example.promotion.model.Product;
import com.example.promotion.model.DiscountResult;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.evrete.api.StatefulSession;
import org.evrete.dsl.annotation.Fact;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.RuleSet;
import org.evrete.dsl.annotation.Where;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final KnowledgeService service;
    private final StatefulSession session;

    public DiscountService() {
        this.service = new KnowledgeService();
        Knowledge knowledge = service.newKnowledge();
        knowledge.addImport(DiscountRuleSet.class);
        this.session = knowledge.newStatefulSession();
    }

    public DiscountResult evaluateDiscount(Product product) {
        try {
            session.clear();
            
            // Initialize memory values
            Double[] discountedPrice = new Double[1];
            String[] promotionApplied = new String[1];
            
            // Set initial values
            session.set("discountedPrice", discountedPrice);
            session.set("promotionApplied", promotionApplied);
            
            // Insert fact and fire rules
            session.insert(product);
            session.fire();
            
            // Get the results
            if (discountedPrice[0] == null) {
                discountedPrice[0] = product.getPrice();
            }
            if (promotionApplied[0] == null) {
                promotionApplied[0] = "No promotion";
            }
            
            return new DiscountResult(
                product.getId(),
                product.getPrice(),
                discountedPrice[0],
                promotionApplied[0]
            );
        } catch (Exception e) {
            return new DiscountResult(
                product.getId(),
                product.getPrice(),
                product.getPrice(),
                "No promotion (error)"
            );
        }
    }

    @RuleSet
    public static class DiscountRuleSet {

        @Rule
        @Where("$p.price > 100")
        public void highValueDiscount(@Fact("$p") Product product, @Value("discountedPrice") Double[] discountedPrice, @Value("promotionApplied") String[] promotionApplied) {
            discountedPrice[0] = product.getPrice() * 0.9;
            promotionApplied[0] = "10% off";
            System.out.printf("Applied high value discount to product %s. Original price: %.2f, Discounted price: %.2f%n",
                    product.getName(),
                    product.getPrice(),
                    discountedPrice[0]
            );
        }

        @Rule
        @Where("$p.price <= 100")
        public void lowValueDiscount(@Fact("$p") Product product, @Value("discountedPrice") Double[] discountedPrice, @Value("promotionApplied") String[] promotionApplied) {
            discountedPrice[0] = product.getPrice() * 0.95;
            promotionApplied[0] = "5% off";
            System.out.printf("Applied low value discount to product %s. Original price: %.2f, Discounted price: %.2f%n",
                    product.getName(),
                    product.getPrice(),
                    discountedPrice[0]
            );
        }
    }
}
*/
