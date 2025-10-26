package com.expense.tracker.service.impl;

import com.expense.tracker.dto.request.PaymentRequestDto;
import com.expense.tracker.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, PaymentRequestDto> kafkaTemplate;

    @Override
    public void pay(Long invoiceId) {
        kafkaTemplate.send("billing.pay.request", new PaymentRequestDto(invoiceId, "PAID"));
    }

}
