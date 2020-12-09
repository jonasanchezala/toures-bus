package com.baeldung.camel.process;

import com.baeldung.camel.pojo.*;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class ProcessCampaignDetailInfo implements Processor {


    public void process(Exchange exchange) throws Exception {

        CampaignResponse campaign  = (CampaignResponse) exchange.getProperty("campaign");
        List<CampaignProductsResponse> campaignProducts = (List<CampaignProductsResponse>) exchange.getProperty("campaignProducts");
        List<ProductResponse> products = (List<ProductResponse>) exchange.getProperty("products");

        CampaignDetailResponse campaignDetailResponse = new CampaignDetailResponse();


        campaignProducts.stream().forEach(
                campaignProduct ->
                        campaignProduct.setName(
                                products.stream()
                                        .filter(productResponse -> productResponse.getId() == campaignProduct.getProduct_id())
                                        .findFirst()
                                        .get()
                                        .getShowName()));

        campaignDetailResponse.setId(campaign.getId());
        campaignDetailResponse.setComments(campaign.getComments());
        campaignDetailResponse.setDescription(campaign.getDescription());
        campaignDetailResponse.setName(campaign.getName());
        campaignDetailResponse.setStart_at(campaign.getStart_at());
        campaignDetailResponse.setFinish_at(campaign.getFinish_at());
        campaignDetailResponse.setCreated_at(campaign.getCreated_at());
        campaignDetailResponse.setUpdated_at(campaign.getUpdated_at());
        campaignDetailResponse.setProducts(campaignProducts);

        exchange.getOut().setBody(campaignDetailResponse);
        exchange.removeProperties("campaign", "campaignProducts", "products");
    }
}
