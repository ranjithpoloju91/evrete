package com.example.promotion.controller;

import com.example.promotion.model.DiscountResult;
import com.example.promotion.model.Product;
import com.example.promotion.service.temp.DiscountService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    @Autowired
    private DiscountService2 discountService;

    @PostMapping
    public DiscountResult getDiscount(@RequestBody Product product) {
        return discountService.evaluateDiscount(product);
    }
}
