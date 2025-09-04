package com.example.promotion.service;

import com.example.promotion.model.Ticket;
import com.example.promotion.model.Promotion;
import org.evrete.KnowledgeService;
import org.evrete.api.Knowledge;
import org.evrete.api.StatefulSession;
import org.evrete.dsl.annotation.Rule;
import org.evrete.dsl.annotation.RuleSet;
import org.evrete.dsl.annotation.Fact;
import org.evrete.dsl.annotation.Where;
import org.springframework.stereotype.Service;

@Service
public class PromotionRuleService {
    
    private final KnowledgeService knowledgeService;
    private final Knowledge knowledge;

    public PromotionRuleService() {
        this.knowledgeService = new KnowledgeService();
        this.knowledge = knowledgeService.newKnowledge();
        try {
            this.knowledge.importRules("JAVA-CLASS", PromotionRuleSet.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize promotion rules", e);
        }
    }

    public Ticket applyPromotion(Ticket ticket) {
        try (StatefulSession session = knowledge.newStatefulSession()) {
            session.insert(ticket);
            if (ticket.getAppliedPromotion() != null) {
                session.insert(ticket.getAppliedPromotion());
            }
            session.fire();
            return ticket;
        }
    }

    @RuleSet
    public static class PromotionRuleSet {
        
        @Rule("Apply Promotion")
        @Where("$t.appliedPromotion != null && $t.appliedPromotion.active && $t.basePrice >= $t.appliedPromotion.minimumPrice")
        public void applyPromotion(@Fact("$t") Ticket ticket, @Fact("$p") Promotion promotion) {
            double discountAmount = ticket.getBasePrice() * (promotion.getDiscountPercentage() / 100.0);
            ticket.setDiscountedPrice(ticket.getBasePrice() - discountAmount);
            ticket.setPromotionApplied(promotion.getName());
        }

        @Rule("No Promotion")
        @Where("$t.appliedPromotion == null || !$t.appliedPromotion.active || $t.basePrice < $t.appliedPromotion.minimumPrice")
        public void noPromotion(@Fact("$t") Ticket ticket) {
            ticket.setDiscountedPrice(ticket.getBasePrice());
            ticket.setPromotionApplied("No promotion applied");
        }
    }
}
