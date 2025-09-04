package com.example.promotion.controller;

import com.example.promotion.model.Ticket;
import com.example.promotion.model.Promotion;
import com.example.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(promotionService.createTicket(ticket));
    }

    @PostMapping("/tickets/{ticketId}/promotions/{promotionId}")
    public ResponseEntity<Ticket> applyPromotion(
            @PathVariable Long ticketId,
            @PathVariable Long promotionId) {
        return ResponseEntity.ok(promotionService.applyPromotion(ticketId, promotionId));
    }

    @PostMapping("/promotions")
    public ResponseEntity<Promotion> createPromotion(@RequestBody Promotion promotion) {
        return ResponseEntity.ok(promotionService.createPromotion(promotion));
    }

    @GetMapping("/promotions")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(promotionService.getAllTickets());
    }
}
