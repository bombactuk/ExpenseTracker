package com.expense.tracker.service;

import com.expense.tracker.dto.response.PaymentResponseDto;

public interface PaymentService {

    void pay(Long invoiceId);

}
