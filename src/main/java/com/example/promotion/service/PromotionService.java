package com.example.promotion.service;

import com.example.promotion.model.Ticket;
import com.example.promotion.model.Promotion;
import com.example.promotion.repository.TicketRepository;
import com.example.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionRuleService ruleService;

    @Transactional
    public Ticket createTicket(Ticket ticket) {
        ticket.setDiscountedPrice(ticket.getBasePrice());
        ticket.setPromotionApplied("No promotion");
        return ticketRepository.save(ticket);
    }

    @Transactional
    public Ticket applyPromotion(Long ticketId, Long promotionId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        
        Promotion promotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        ticket.setAppliedPromotion(promotion);
        ticket = ruleService.applyPromotion(ticket);
        return ticketRepository.save(ticket);
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Transactional
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }
}
