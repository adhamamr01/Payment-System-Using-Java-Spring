package com.payment.paymentpage.controller;

import com.payment.paymentpage.entity.Payment;
import com.payment.paymentpage.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // MVC Endpoints
    @GetMapping("/list")
    public String listPayments(Model theModel) {

        // get the clients from db
        List<Payment> thePayments = paymentService.findAll();

        // add to the spring model
        theModel.addAttribute("payments", thePayments);

        return "list-payments";
    }
}
