package com.payment.paymentpage.controller;


import com.payment.paymentpage.entity.Client;
import com.payment.paymentpage.entity.InquiryResponse;
import com.payment.paymentpage.service.ClientService;
import com.payment.paymentpage.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ClientRestController {

    private ClientService clientService;

    private PaymentService paymentService;

    @Autowired
    public ClientRestController(ClientService theClientService, PaymentService thePaymentService) {
        clientService = theClientService;
        paymentService = thePaymentService;
    }


    // Crud Endpoints
    @GetMapping("/clients")
    public List<Client> findAll() {return clientService.findAll();}

    @GetMapping("/clients/{clientId}")
    public Client getClient(@PathVariable int clientId) {

        Client theClient = clientService.findById(clientId);


        if (theClient == null) {
            throw new RuntimeException("Client id not found - " + clientId);
        }

        return theClient;
    }
    @PostMapping("/clients")
    public Client addClient(@RequestBody Client theClient) {

        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update

        theClient.setId(0);

        Client dbClient = clientService.save(theClient);

        return dbClient;
    }

    @GetMapping("/clients/inquire/{clientId}")
    public InquiryResponse getAmountByClientId(@PathVariable("clientId") int clientId){
        InquiryResponse inquiryResponse = clientService.findSumAmountByClientId(clientId);

        Client theClient = clientService.findById(clientId);
//        System.out.println(clientService.sumAmount(clientId));
        if (theClient == null) {
            throw new RuntimeException("Client id not found - " + clientId);
        }

        return inquiryResponse;
    }

    @GetMapping("/clients/pay/{clientId}")
    public Client payAllAmountByClientId(@PathVariable("clientId") int clientId){

        Client theClient = clientService.findById(clientId);
//        System.out.println(clientService.sumAmount(clientId));
        if (theClient == null) {
            throw new RuntimeException("Client id not found - " + clientId);
        }
        theClient = clientService.payAllAmountByClientId(clientId);

        Client dbClient = clientService.save(theClient);

        return dbClient;
    }
}
