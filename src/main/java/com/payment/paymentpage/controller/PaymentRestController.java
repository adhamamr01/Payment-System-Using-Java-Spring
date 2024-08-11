package com.payment.paymentpage.controller;


import com.payment.paymentpage.entity.Client;
import com.payment.paymentpage.entity.Payment;
import com.payment.paymentpage.service.ClientService;
import com.payment.paymentpage.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PaymentRestController {

    private PaymentService paymentService;

    private ClientService clientService;

    @Autowired
    public PaymentRestController(PaymentService thePaymentService, ClientService theClientService) {
        paymentService = thePaymentService;
        clientService = theClientService;
    }

    // CRUD Endpoints
    @GetMapping("/payments")
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @GetMapping("/payments/{paymentId}")
    public Payment getPayment(@PathVariable int paymentId) {

        Payment thePayment = paymentService.findById(paymentId);

        if (thePayment == null) {
            throw new RuntimeException("Payment id not found - " + paymentId);
        }

        return thePayment;
    }
    @PostMapping("/payments")
    public Client addPayment(@RequestBody Payment thePayment) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update


        int clientId = thePayment.getClient().getId();
        Client theClient = clientService.findById(clientId);

        thePayment.setId(0);


        if (theClient == null) {
            throw new RuntimeException("Client id not found - " + clientId);
        }

        theClient.add(thePayment);
        Client dbClient = clientService.save(theClient);
//        Payment dbPayment = paymentService.save(thePayment);


        return dbClient;
    }

}
