/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.billing.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.billing.BillingClient;
import com.baidubce.services.billing.BillingClientConfiguration;
import com.baidubce.services.billing.model.ResourceMonthBillRequest;
import com.baidubce.services.billing.model.ResourceMonthBillResponse;
import com.baidubce.services.billing.model.bill.PrepayShareBillRequest;
import com.baidubce.services.billing.model.bill.PrepayShareBillResponse;
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
import com.baidubce.services.billing.model.renew.RenewResourceListRequest;
import com.baidubce.services.billing.model.renew.RenewResourceResponse;
import com.baidubce.services.billing.model.renew.ResourceAutoRenewRequest;
import com.baidubce.util.JsonUtils;
import com.google.common.collect.Lists;

/**
 * examples for billing open api
 */
public class BillingExample {

    private static final String ACCESS_KEY_ID = "ak ";
    private static final String SECRET_ACCESS_KEY = "sk ";
    private static final String ENDPOINT = "https://billing endpoint";
    private static final String TEST_ACCOUNT_ID = "test id";

    public static void main(String[] args) {
        sampleForQueryResourceBillList();
        sampleForQueryRenewResourceList();
        // sampleForSetRenewResourceRule();
        sampleForQueryPrepayShareBill();
        sampleForQueryShareBill();
        sampleForQueryProductMonthBillSummary();
        sampleForGetResourceMonthBills();
        sampleForQueryOrderList();
        sampleForGetOrderWithUuid();
        sampleForCancelOrders();
        sampleForPayOrder();
        sampleForgetSpecificCptPrice();
        sampleForgetSpecificCpcPrice();
        sampleForCashBalanceQuery();
        sampleForUserBalanceQuery();
    }

    private static void sampleForQueryRenewResourceList() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        RenewResourceListRequest request = new RenewResourceListRequest();
        request.setServiceType("CDS");
        request.setPageNo(1);
        request.setPageSize(50);

        RenewResourceResponse response = client.queryRenewResourceList(request);

        System.out.println("==================================");
        System.out.println("sampleForQueryRenewResourceList RenewResourceResponse result:");
        System.out.println("    accountId:  " + response.getAccountId());
        System.out.println("    loginName:  " + response.getLoginName());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    resources:  " + response.getResources());
        System.out.println("==================================");
    }

    private static void sampleForSetRenewResourceRule() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        ResourceAutoRenewRequest request = new ResourceAutoRenewRequest();
        request.setServiceType("CDS");
        request.setInstanceId("id ");
        request.setRegion("bj");
        request.setRenewTime(1);
        request.setRenewTimeUnit("year");

        client.setRenewResourceRule(request);

        System.out.println("==================================");
        System.out.println("sampleForSetRenewResourceRule ResourceAutoRenewRequest :");
        System.out.println("    serviceType:  " + request.getServiceType());
        System.out.println("    instanceId:  " + request.getInstanceId());
        System.out.println("    region:  " + request.getRegion());
        System.out.println("    renewTime:  " + request.getRenewTime());
        System.out.println("    renewTimeUnit:  " + request.getRenewTimeUnit());
        System.out.println("==================================");
    }

    private static void sampleForQueryResourceBillList() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        ResourceBillListQueryRequest request = new ResourceBillListQueryRequest();
        request.setBillMonth("2020-08");
        request.setProductType("prepay");
        request.setPageNo(1);
        request.setPageSize(10);

        ResourceBillListQueryResponse response = client.getResourceBillList(request);

        System.out.println("==================================");
        System.out.println("sampleForQueryResourceBillList ResourceBillListQueryResponse result:");
        System.out.println("    accountId:  " + response.getAccountId());
        System.out.println("    month:  " + response.getBillMonth());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    bills:  " + response.getBills());
        System.out.println("==================================");
    }

    private static void sampleForQueryPrepayShareBill() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        PrepayShareBillRequest request = new PrepayShareBillRequest();
        request.setMonth("2020-04");
        request.setPageNo(1);
        request.setPageSize(50);

        PrepayShareBillResponse response = client.queryPrepayShareBill(request);

        System.out.println("==================================");
        System.out.println("sampleForQueryPrepayShareBill PrepayShareBillResponse result:");
        System.out.println("    accountId:  " + response.getAccountId());
        System.out.println("    month:  " + response.getBillMonth());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    bills:  " + response.getBills());
        System.out.println("==================================");
    }

    private static void sampleForQueryShareBill() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        ShareBillRequest request = new ShareBillRequest();
        request.setMonth("2022-09");
        request.setPageNo(1);
        request.setPageSize(50);

        ShareBillResponse response = client.queryShareBill(request);

        System.out.println("==================================");
        System.out.println("sampleForQueryShareBill ShareBillResponse result:");
        System.out.println("    accountId:  " + response.getAccountId());
        System.out.println("    month:  " + response.getBillMonth());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    bills:  " + response.getBills());
        System.out.println("==================================");
    }

    private static void sampleForQueryProductMonthBillSummary() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        ProductMonthBillSummaryRequest request = new ProductMonthBillSummaryRequest();
        request.setBillMonth("2022-09");

        ProductMonthBillSummaryResponse response = client.queryProductMonthBillSummary(request);

        System.out.println("==================================");
        System.out.println("sampleForQueryShareBill ShareBillResponse result:");
        System.out.println("    accountId:  " + response.getAccountId());
        System.out.println("    data:  " + response.getData());
        System.out.println("==================================");
    }

    private static void sampleForGetResourceMonthBills() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        ResourceMonthBillRequest request = new ResourceMonthBillRequest();
        request.setMonth("2019-04");
        request.setProductType("postpay");
        request.setPageNo(1);
        request.setPageSize(50);

        ResourceMonthBillResponse response = client.getResourceMonthBill(request);

        System.out.println("==================================");
        System.out.println("sampleForGetResourceMonthBills ResourceMonthBillResponse result:");
        System.out.println("    accountId:  " + response.getAccountId());
        System.out.println("    month:  " + response.getBillMonth());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    bills:  " + response.getBills());
        System.out.println("==================================");
    }

    private static void sampleForQueryOrderList() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        OrderListRequest request = new OrderListRequest();
        request.setProductType("postpay");
        request.setPageNo(1);
        request.setPageSize(50);

        OrderListResponse response = client.getOrderList(request);

        System.out.println("==================================");
        System.out.println("sampleForQueryOrderList OrderListResponse result:");
        System.out.println("    pageNo:  " + response.getPageNo());
        System.out.println("    pageSize:  " + response.getPageSize());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    orders:  " + response.getOrders());
        System.out.println("==================================");
    }

    private static void sampleForGetOrderWithUuid() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        OrderUuidQueryRequest request = new OrderUuidQueryRequest();
        request.setQueryAccountId("query accountId");
        List<String> uuidList = new ArrayList<String>();
        uuidList.add("order id A");
        uuidList.add("order id B");
        request.setUuids(uuidList);


        OrderListResponse response = client.getOrdersByUuid(request);

        System.out.println("==================================");
        System.out.println("sampleForGetOrderWithUuid OrderListResponse result:");
        System.out.println("    pageNo:  " + response.getPageNo());
        System.out.println("    pageSize:  " + response.getPageSize());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    orders:  " + response.getOrders());
        System.out.println("==================================");
    }

    private static void sampleForCancelOrders() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        OrderCancelRequest request = new OrderCancelRequest();
        List<String> orderIds = new ArrayList<String>();
        orderIds.add("test order uuid 1");
        orderIds.add("test order uuid 2");
        request.setOrderIds(orderIds);

        OrderCancelResponse response = client.cancelOrders(request);

        System.out.println("==================================");
        System.out.println("sampleForCancelOrders OrderCancelResponse result:");
        System.out.println("    success:  " + response.getSuccess());
        System.out.println("==================================");
    }

    private static void sampleForPayOrder() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        OrderPaymentRequest request = new OrderPaymentRequest();
        request.setOrderId("test order uuid");

        OrderPaymentResponse response = client.payOrder(request);

        System.out.println("==================================");
        System.out.println("sampleForPayOrder OrderPaymentResponse result:");
        System.out.println("    success:  " + response.getSuccess());
        System.out.println("==================================");
    }

    private static void sampleForgetSpecificCptPrice() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        Flavor flavor = new Flavor();
        List<ChargeItem> items = new ArrayList<ChargeItem>();
        items.add(new ChargeItem("memory", "1g", BigDecimal.valueOf(8)));
        items.add(new ChargeItem("physicalZone", "zone id", BigDecimal.valueOf(8)));
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

        PricingQueryResponse response = client.getSpecificCptPrice(request);

        System.out.println("==================================");
        System.out.println("sampleForgetSpecificCptPrice PricingQueryResponse result:");
        System.out.println("    price:  " + response.getPrice());
        System.out.println("    catalogPrice:  " + response.getCatalogPrice());
        System.out.println("==================================");
    }

    private static void sampleForgetSpecificCpcPrice() {
        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        Flavor flavor = new Flavor();
        List<ChargeItem> items = new ArrayList<ChargeItem>();
        items.add(new ChargeItem("OutBytes", "10G", null));
        flavor.setChargeItems(items);

        CpcPricingRequest request = new CpcPricingRequest();
        request.setServiceType("LSS");
        request.setProductType("ON_DEMAND");
        request.setRegion("global");
        request.setFlavor(flavor);
        request.setCount(1);

        PricingQueryResponse response = client.getSpecificCpcPrice(request);

        System.out.println("==================================");
        System.out.println("sampleForgetSpecificCpcPrice PricingQueryResponse result:");
        System.out.println("    price:  " + response.getPrice());
        System.out.println("    catalogPrice:  " + response.getCatalogPrice());
        System.out.println("==================================");
    }

    private static void sampleForBalanceTransfer() {

        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        SupervisorBalanceTransferRequest request = new SupervisorBalanceTransferRequest();
        request.setSupervisedId("subUserId");
        request.setAmount(new BigDecimal("20"));

        TransferResultResponse response = client.balanceTransfer(request);

        System.out.println("==================================");
        System.out.println("sampleForBalanceTransfer response result:");
        System.out.println("    result:  " + response.isSuccess());
        System.out.println("==================================");
    }

    private static void sampleForTransactionList() {

        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        SupervisorTransactionPageRequest request = new SupervisorTransactionPageRequest();
        request.setBeginTime("2019-12-31T16:00:00");
        request.setEndTime("2020-01-31T15:59:59");
        request.setPageNo(1);
        request.setPageSize(100);

        SupervisorTransactionResponse response = client.transactionList(request);

        System.out.println("==================================");
        System.out.println("sampleForBalanceTransfer response result:");
        System.out.println("    pageno:  " + response.getPageNo());
        System.out.println("    pagesize:  " + response.getPageSize());
        System.out.println("    size:  " + response.getTotalCount());
        System.out.println("    result:  " + response.getResult());
        System.out.println("==================================");
    }

    private static void sampleForCashBalanceQuery() {

        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        SupervisorBalanceQueryRequest request = new SupervisorBalanceQueryRequest();
        request.setAccountIds(Lists.newArrayList(TEST_ACCOUNT_ID));

        SupervisorBalanceResponse response = client.balanceQuery(request);

        System.out.println("==================================");
        System.out.println("SupervisorBalance response result:");
        System.out.println("    result:  " + JsonUtils.toJsonString(response.getResult()));
        System.out.println("==================================");
    }

    private static void sampleForUserBalanceQuery() {

        BceCredentials credentials = new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        BillingClient client = new BillingClient(
                new BillingClientConfiguration().withEndpoint(ENDPOINT).withCredentials(credentials)
        );

        UserBalanceQueryResponse response = client.userBalanceQuery();

        System.out.println("============================");
        System.out.println("UserBalance response result:");
        System.out.println("    result:  " + JsonUtils.toJsonString(response.getCashBalance()));
        System.out.println("============================");
    }
}
