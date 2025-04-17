package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.entities.Payment;
import com.prajjwal.project.Uber.entities.Ride;
import com.prajjwal.project.Uber.repositories.PaymentRepository;
import com.prajjwal.project.Uber.services.PaymentService;
import com.prajjwal.project.Uber.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Payment payment) {
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        return null;
    }
}
