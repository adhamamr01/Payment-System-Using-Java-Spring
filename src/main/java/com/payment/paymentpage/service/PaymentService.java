package com.payment.paymentpage.service;

import com.payment.paymentpage.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(int theId);

    Payment save(Payment thePayment);


}
