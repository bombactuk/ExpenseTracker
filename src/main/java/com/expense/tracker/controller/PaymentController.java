package com.expense.tracker.controller;

import com.expense.tracker.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{invoiceId}")
    public String pay(@PathVariable Long invoiceId) {
        paymentService.pay(invoiceId);
        return "Payment request sent for invoice " + invoiceId;
    }

}