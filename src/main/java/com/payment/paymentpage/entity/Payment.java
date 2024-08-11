package com.payment.paymentpage.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="ID")
    private Integer id;

    @Getter
    @Setter
    @Column(name="AMOUNT")
    private int amount;

    @Getter
    @Setter
    @Column(name = "PAID")
    private int paid;

    @Getter
    @Setter
    @ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "CLIENT_ID")
    @JsonBackReference
    private Client client;

    Payment(){

    }

    public Payment(int amount, Client client) {
        this.amount = amount;
        this.client = client;
        this.paid = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
