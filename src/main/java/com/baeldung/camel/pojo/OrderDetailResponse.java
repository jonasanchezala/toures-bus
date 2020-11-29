package com.baeldung.camel.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDetailResponse implements Serializable {

    private int id;
    private String name_client;
    private String total;
    private String status;
    private String comments;
    private String created_at;
    private String updated_at;
    private String payment_method;
    private String credit_number_card;
    private List<ProductResponse> productResponses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
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

    public List<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(List<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }
}