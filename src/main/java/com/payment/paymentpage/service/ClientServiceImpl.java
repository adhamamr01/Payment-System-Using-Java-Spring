package com.payment.paymentpage.service;

import com.payment.paymentpage.dao.ClientRepository;
import com.payment.paymentpage.entity.Client;
import com.payment.paymentpage.entity.InquiryResponse;
import com.payment.paymentpage.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;


    @Autowired
    ClientServiceImpl(ClientRepository theClientRepository) {
        clientRepository = theClientRepository;

    }


    @Override
    public List<Client> findAll() {
        System.out.println("this is the client service");
        return clientRepository.findAll();
    }

    @Override
    public Client findById(int theId) {
        Optional<Client> result = clientRepository.findById(theId);

        Client theClient = null;

        if (result.isPresent()) {
            theClient = result.get();
        } else {
            // we didn't find the client
            throw new RuntimeException("Did not find client id - " + theId);
        }

        return theClient;
    }

    @Override
    public Client save(Client theClient) {
        return clientRepository.save(theClient);
    }

    @Override
    public List<InquiryResponse> findSumAmount() {
        List<InquiryResponse> sumAmount = clientRepository.findSumAmount();
        return sumAmount;
    }

    @Override
    public InquiryResponse findSumAmountByClientId(int client_id) {
        System.out.println(client_id);
        InquiryResponse sumAmountByClientId = clientRepository.findSumAmountByClientId(client_id);
        return sumAmountByClientId;
    }

    @Override
    public Client payAllAmountByClientId(int client_id) {
        Optional<Client> result = clientRepository.findById(client_id);

        Client theClient = null;

        if (result.isPresent()) {
            theClient = result.get();
        } else {
            // we didn't find the client
            throw new RuntimeException("Did not find client id - " + client_id);
        }

        List<Payment> temp = theClient.getPayments();
        temp.stream().filter(payment -> payment.getPaid()==0).forEach(p -> p.setPaid(1));

        return theClient;
    }


}



