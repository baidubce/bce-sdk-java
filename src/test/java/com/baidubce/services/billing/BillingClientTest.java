/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.billing.model.ResourceMonthBillRequest;
import com.baidubce.services.billing.model.ResourceMonthBillResponse;
import com.baidubce.services.billing.model.bill.CostSplitBillRequest;
import com.baidubce.services.billing.model.bill.CostSplitBillResponse;
import com.baidubce.services.billing.model.bill.ProductMonthBillSummaryRequest;
import com.baidubce.services.billing.model.bill.ProductMonthBillSummaryResponse;
import com.baidubce.services.billing.model.bill.ResourceBillListQueryRequest;
import com.baidubce.services.billing.model.bill.ResourceBillListQueryResponse;
import com.baidubce.services.billing.model.bill.ShareBillRequest;
import com.baidubce.services.billing.model.bill.ShareBillResponse;
import com.baidubce.services.billing.model.finance.SupervisorBalanceQueryRequest;
import com.baidubce.services.billing.model.finance.SupervisorBalanceResponse;
import com.baidubce.services.billing.model.finance.SupervisorBalanceTransferRequest;
import com.baidubce.services.billing.model.finance.SupervisorTransactionPageRequest;
import com.baidubce.services.billing.model.finance.SupervisorTransactionResponse;
import com.baidubce.services.billing.model.finance.TransferResultResponse;
import com.baidubce.services.billing.model.finance.UserBalanceQueryResponse;
import com.baidubce.services.billing.model.order.OrderCancelRequest;
import com.baidubce.services.billing.model.order.OrderCancelResponse;
import com.baidubce.services.billing.model.order.OrderListRequest;
import com.baidubce.services.billing.model.order.OrderListResponse;
import com.baidubce.services.billing.model.order.OrderPaymentRequest;
import com.baidubce.services.billing.model.order.OrderPaymentResponse;
import com.baidubce.services.billing.model.order.OrderUuidQueryRequest;
import com.baidubce.services.billing.model.price.ChargeItem;
import com.baidubce.services.billing.model.price.CpcPricingRequest;
import com.baidubce.services.billing.model.price.CptPricingRequest;
import com.baidubce.services.billing.model.price.Flavor;
import com.baidubce.services.billing.model.price.PricingQueryResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;

/**
 * test for billing client
 */
public class BillingClientTest {

    private static final Logger logger = LoggerFactory.getLogger(BillingClientTest.class);
    private static final String YOUR_AK = "ak ";
    private static final String YOUR_SK = "sk ";
    private static final String ONLINE_URL = "https://billing endpoint";

    @Test
    public void testOrder() {
        OrderListRequest request = new OrderListRequest();
        request.setPageSize(1000);

        try {
            BillingClient billingClient = getBillingClient();
            int pageNo = 0;
            OrderListResponse response = new OrderListResponse();

            do {
                pageNo++;
                request.setPageNo(pageNo);
                response = billingClient.getOrderList(request);

                logger.info("OrderListResponse response : {}", response);
            } while (pageNo * request.getPageSize() < response.getTotalCount());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void testGetOrderByUuid() {
        try {
            BillingClient billingClient = getBillingClient();
            OrderUuidQueryRequest request = new OrderUuidQueryRequest();
            request.setQueryAccountId("query AccountId");
            request.setUuids(Arrays.asList("orderId"));

            OrderListResponse response = billingClient.getOrdersByUuid(request);

            logger.info("OrderListResponse response : {}", response);
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void testCancelOrders() {
        try {
            BillingClient billingClient = getBillingClient();
            OrderCancelRequest request = new OrderCancelRequest();
            request.setOrderIds(Arrays.asList("orderId"));

            OrderCancelResponse response = billingClient.cancelOrders(request);

            logger.info("OrderCancelResponse response : {}", response);
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void testPayOrder() {
        try {
            BillingClient billingClient = getBillingClient();
            OrderPaymentRequest request = new OrderPaymentRequest();
            request.setOrderId("orderId");

            OrderPaymentResponse response = billingClient.payOrder(request);

            logger.info("OrderPaymentResponse response : {}", response);
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void getPricingCPCTest() {
        Flavor flavor = new Flavor();
        ChargeItem item = new ChargeItem("cpu", "1", BigDecimal.ONE);
        item.setName("count");
        item.setValue("5000000");
        item.setScale(BigDecimal.valueOf(2));
        flavor.setChargeItems(Lists.newArrayList(item));

        CpcPricingRequest cpcRequst = new CpcPricingRequest();
        cpcRequst.setServiceType("HTTPDNS");
        cpcRequst.setProductType("RESERVED");
        cpcRequst.setRegion("global");
        cpcRequst.setFlavor(flavor);
        cpcRequst.setCount(1);

        getPriceCpc(cpcRequst);
    }

    @Test
    public void getPricingCPTTest() {
        Flavor flavor = new Flavor();
        List<ChargeItem> items = new ArrayList<ChargeItem>();
        items.add(new ChargeItem("memory", "1g", BigDecimal.valueOf(8)));
        items.add(new ChargeItem("physicalZone", "E10B59F709A01DD46F9BB62F5CB3EADB", BigDecimal.valueOf(8)));
        items.add(new ChargeItem("cpu", "1", BigDecimal.valueOf(2)));
        items.add(new ChargeItem("subServiceType", "defaultBcc3", null));
        flavor.setChargeItems(items);

        CptPricingRequest request = new CptPricingRequest();
        request.setServiceType("BCC");
        request.setProductType("RESERVED");
        request.setRegion("bj");
        request.setFlavor(flavor);
        request.setCount(1);
        request.setPeriod("P1M");

        getPriceCpt(request);
    }

    @Test
    public void ResourceBillListTest() {
        ResourceBillListQueryRequest request = new ResourceBillListQueryRequest();
        request.setBillMonth("2020-08");
        request.setProductType("prepay");
        request.setPageNo(1);
        request.setPageSize(10);

        try {
            BillingClient billingClient = getBillingClient();
            ResourceBillListQueryResponse response = billingClient.getResourceBillList(request);
            logger.info("response : {} size:{}", response.getBills(), response.getBills().size());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void resourceMonthBillCommonTest() {
        ResourceMonthBillRequest request = new ResourceMonthBillRequest();
        request.setMonth("2019-04");
        request.setProductType("postpay");
        // request.setServiceType("CDS");
        request.setPageNo(1);
        request.setPageSize(50);

        try {
            BillingClient billingClient = getBillingClient();
            ResourceMonthBillResponse response = billingClient.getResourceMonthBill(request);
            logger.info("response : {} size:{}", response.getBills(), response.getBills().size());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void productMonthSummaryTest() {
        ProductMonthBillSummaryRequest request = new ProductMonthBillSummaryRequest();
        request.setBillMonth("2022-09");
        try {
            BillingClient billingClient = getBillingClient();
            ProductMonthBillSummaryResponse response =
                    billingClient.queryProductMonthBillSummary(request);
            logger.info("response : {}", response.getData());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void shareBillTest() {
        ShareBillRequest request = new ShareBillRequest();
        request.setMonth("2022-09");
        try {
            BillingClient billingClient = getBillingClient();
            ShareBillResponse response = billingClient.queryShareBill(request);
            logger.info("response : {} size:{}", response.getBills(), response.getBills().size());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void costSplitBillTest() throws Exception {
        CostSplitBillRequest request = new CostSplitBillRequest();
        request.setMonth("2024-06");
        request.setPageSize(10);
        try {
            BillingClient billingClient = getBillingClient();
            CostSplitBillResponse response = billingClient.queryCostSplitBill(request);
            logger.info("response : {} size:{}", response.getBills(), response.getBills().size());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void testBalanceTransfer() {
        SupervisorBalanceTransferRequest request = new SupervisorBalanceTransferRequest();
        request.setSupervisedId("1be17d3ec7e047d78e79ac604c5c5dd1");
        request.setAmount(new BigDecimal("20"));

        try {
            BillingClient billingClient = getBillingClient();
            TransferResultResponse response = billingClient.balanceTransfer(request);
            logger.info("response : {} ", response.isSuccess());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void testBalanceGather() {
        SupervisorBalanceTransferRequest request = new SupervisorBalanceTransferRequest();
        request.setSupervisedId("1be17d3ec7e047d78e79ac604c5c5dd1");
        request.setAmount(new BigDecimal("10"));

        try {
            BillingClient billingClient = getBillingClient();
            TransferResultResponse response = billingClient.balanceGather(request);
            logger.info("response : {} ", response.isSuccess());
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    @Test
    public void testGetTransactionList() {

        SupervisorTransactionPageRequest request = new SupervisorTransactionPageRequest();
        request.setBeginTime("2019-12-31T16:00:00");
        request.setEndTime("2020-03-31T15:59:59");
        request.setPageNo(1);
        request.setPageSize(100);

        try {
            BillingClient billingClient = getBillingClient();
            SupervisorTransactionResponse response = billingClient.transactionList(request);
            logger.info(JsonUtils.toJsonPrettyString(response));

        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        } catch (JsonProcessingException e) {
            logger.info("JsonProcessingException : {}", e.getMessage());
        }
    }

    @Test
    public void testCashBalanceQuery() {
        SupervisorBalanceQueryRequest request = new SupervisorBalanceQueryRequest();
        request.setAccountIds(Lists.newArrayList("test id"));

        try {
            BillingClient billingClient = getBillingClient();
            SupervisorBalanceResponse response = billingClient.balanceQuery(request);
            logger.info(JsonUtils.toJsonPrettyString(response));

        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        } catch (JsonProcessingException e) {
            logger.info("JsonProcessingException : {}", e.getMessage());
        }
    }

    @Test
    public void testUserBalanceQuery() {
        try {
            BillingClient billingClient = getBillingClient();
            UserBalanceQueryResponse response = billingClient.userBalanceQuery();
            logger.info(JsonUtils.toJsonPrettyString(response));
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        } catch (JsonProcessingException e) {
            logger.info("JsonProcessingException : {}", e.getMessage());
        }
    }

    private void getPriceCpc(CpcPricingRequest cpcRequst) {
        try {
            BillingClient billingClient = getBillingClient();
            PricingQueryResponse response = billingClient.getSpecificCpcPrice(cpcRequst);
            logger.info("PricingQueryResponse response : {}", response);
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    private void getPriceCpt(CptPricingRequest cptRequst) {
        try {
            BillingClient billingClient = getBillingClient();
            PricingQueryResponse response = billingClient.getSpecificCptPrice(cptRequst);
            logger.info("PricingQueryResponse response : {}", response);
        } catch (BceServiceException e) {
            logger.info("BceServiceException : {}", e.getMessage());
        } catch (BceClientException e) {
            logger.info("BceClientException : {}", e.getMessage());
        }
    }

    private BillingClient getBillingClient() {
        BillingClientConfiguration config = new BillingClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(YOUR_AK, YOUR_SK));
        config.setEndpoint(ONLINE_URL);
        BillingClient billingClient = new BillingClient(config);
        return billingClient;
    }
}
