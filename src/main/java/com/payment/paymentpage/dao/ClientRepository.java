package com.payment.paymentpage.dao;

import com.payment.paymentpage.entity.Client;
import com.payment.paymentpage.entity.InquiryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {


  @Query(nativeQuery = true, value = "SELECT c.FIRST_NAME first_name,c.LAST_NAME last_name , sum(p.AMOUNT) sumAmount \n" +
           "FROM Client c,Payment p\n" +
           "where c.ID = p.CLIENT_ID AND c.ID = :client_id AND p.PAID = 0\n" +
           "GROUP by c.FIRST_NAME,c.LAST_NAME")

  InquiryResponse findSumAmountByClientId(@Param("client_id") int client_id);


  @Query(nativeQuery = true, value = "SELECT c.FIRST_NAME first_name,c.LAST_NAME last_name , sum(p.AMOUNT) sumAmount \n" +
          "FROM Client c,Payment p\n" +
          "where c.ID = p.CLIENT_ID \n" +
          "GROUP by c.FIRST_NAME,c.LAST_NAME")
  List<InquiryResponse> findSumAmount();

}
