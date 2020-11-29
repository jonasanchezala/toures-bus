package com.baeldung.camel.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderResponse implements Serializable {

    private int id;
    private int client_id;
    private String total;
    private String status;
    private String comments;
    private Date created_at;
    private Date updated_at;
    private String payment_method;
    private String credit_number_card;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}