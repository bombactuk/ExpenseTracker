package com.expense.tracker.service;

import com.expense.tracker.dto.request.PaymentRequestDto;

public interface PaymentProcessorService {

    void handlePaymentRequest(PaymentRequestDto message);

}
