package com.payment.paymentpage.service;

import com.payment.paymentpage.entity.Client;
import com.payment.paymentpage.entity.InquiryResponse;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(int theId);

    Client save(Client theClient);

    List<InquiryResponse> findSumAmount();

    InquiryResponse findSumAmountByClientId(int client_id);

    Client payAllAmountByClientId(int client_id);


}
