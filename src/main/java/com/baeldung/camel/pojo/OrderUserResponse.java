package com.baeldung.camel.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderUserResponse implements Serializable {

    private int id;
    private String client_name;
    private Double total;
    private String status;
    private String comments;
    private String created_at;
    private String updated_at;
    private String payment_method;
    private String credit_number_card;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getCredit_number_card() {
        return credit_number_card;
    }

    public void setCredit_number_card(String credit_number_card) {
        this.credit_number_card = credit_number_card;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}