package com.expense.tracker.service.impl;

import com.expense.tracker.dto.request.PaymentRequestDto;
import com.expense.tracker.dto.response.PaymentResponseDto;
import com.expense.tracker.entity.Invoice;
import com.expense.tracker.repository.InvoiceRepository;
import com.expense.tracker.service.PaymentProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProcessorServiceImpl implements PaymentProcessorService {

    private final InvoiceRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    @KafkaListener(topics = "billing.pay.request", groupId = "billing-service")
    public void handlePaymentRequest(PaymentRequestDto message) {
        boolean success = false;
        try {
            Invoice invoice = repository.findById(message.getInvoiceId())
                    .orElseThrow(() -> new RuntimeException("Invoice not found"));
            invoice.setStatus(Invoice.Status.valueOf(message.getStatus()));
            repository.save(invoice);
            success = true;
        } catch (Exception ignored) {}

        kafkaTemplate.send(
                "billing.pay.response",
                new PaymentResponseDto(message.getInvoiceId(), success ? "SUCCESS" : "FAIL")
        );
    }

}
