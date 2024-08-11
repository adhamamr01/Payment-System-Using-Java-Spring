package com.payment.paymentpage.controller;

import com.payment.paymentpage.entity.Client;
import com.payment.paymentpage.entity.InquiryResponse;
import com.payment.paymentpage.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // MVC Endpoints
    @GetMapping("/list")
    public String listClients(Model theModel) {

        // get the clients from db
        List<Client> theClients = clientService.findAll();

        // add to the spring model
        theModel.addAttribute("clients", theClients);

        List<InquiryResponse> theUnpaidAmounts = clientService.findSumAmount();

        theModel.addAttribute("unpaidAmounts",theUnpaidAmounts);

        return "list-clients";
    }
    @GetMapping("/payUnpaidAmount")
    public String payUnpaidAmount(@RequestParam("clientId") int theId,
                                    Model theModel) {

        // get the client from the service
        Client theClient = clientService.findById(theId);

        if (theClient == null) {
            throw new RuntimeException("Client id not found - " + theId);
        }
        theClient = clientService.payAllAmountByClientId(theId);

        Client dbClient = clientService.save(theClient);

        List<Client> theClients = clientService.findAll();
        theModel.addAttribute("clients", theClients);

        return "redirect:/clients/list";
    }
}
