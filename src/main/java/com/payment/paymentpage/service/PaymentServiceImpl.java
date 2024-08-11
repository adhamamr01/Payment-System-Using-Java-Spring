package com.payment.paymentpage.service;

import com.payment.paymentpage.dao.PaymentRepository;
import com.payment.paymentpage.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    PaymentRepository paymentRepository;

    @Autowired
    PaymentServiceImpl(PaymentRepository thePaymentRepository){
        paymentRepository=thePaymentRepository;

    }

    @Override
    public List<Payment> findAll() {
        System.out.println("this is the payment find all service");
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(int theId) {
        System.out.println("this is the payment findbyid service");
        Optional<Payment> result = paymentRepository.findById(theId);

        Payment thePayment = null;

        if (result.isPresent()) {
            thePayment = result.get();
        }
        else {
            // we didn't find the client
            throw new RuntimeException("Did not find payment id - " + theId);
        }

        return thePayment;
    }

    @Override
    public Payment save(Payment thePayment) {
        return paymentRepository.save(thePayment);
    }
}