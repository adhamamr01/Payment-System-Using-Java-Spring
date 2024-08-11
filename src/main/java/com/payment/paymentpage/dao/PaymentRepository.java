package com.payment.paymentpage.dao;

import com.payment.paymentpage.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment,Integer> {

}
