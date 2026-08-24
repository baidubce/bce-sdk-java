/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.baidubce.services.cas.model.request.ApplyCertRequest;
import com.baidubce.services.cas.model.request.BatchRequest;
import com.baidubce.services.cas.model.request.CertDetailRequest;
import com.baidubce.services.cas.model.request.DownloadCertFileRequest;
import com.baidubce.services.cas.model.request.ListCertsRequest;
import com.baidubce.services.cas.model.request.NewOrderConfig;
import com.baidubce.services.cas.model.request.PriceConfig;
import com.baidubce.services.cas.model.request.RenewOrderConfig;
import com.baidubce.services.cas.model.request.ReplaceCertRequest;
import com.baidubce.services.cas.model.response.ApiOrderResponse;
import com.baidubce.services.cas.model.response.CertContactResponse;
import com.baidubce.services.cas.model.response.CertDetails;
import com.baidubce.services.cas.model.response.CertDetailsListResponse;
import com.baidubce.services.cas.model.response.CertPkiResponse;
import com.baidubce.services.cas.model.response.CheckPurchasableResponse;
import com.baidubce.services.cas.model.response.PriceResponse;
import com.baidubce.services.cas.model.response.ReplaceResponse;
import com.baidubce.services.cas.model.type.CertBrand;
import com.baidubce.services.cas.model.type.CertFileType;
import com.baidubce.services.cas.model.type.CertType;
import com.baidubce.services.cas.model.type.ProductType;
import com.baidubce.services.cas.model.type.TimeUnit;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Tests whether the CAS open api can be reached with the current signature and models.
 *
 * <p>Fill in accessKey, secretKey and certId before running. The tests annotated with
 * {@link Ignore} either create paid orders or change the state of a certificate, so they
 * are skipped by default and should only be run against a test account on purpose.
 */
@Slf4j
public class CasClientTest {

    private static final String accessKey = "";
    private static final String secretKey = "";
    private static final String endpoint = "https://cas.baidubce.com";

    /**
     * Id of an existing certificate of the account, used by the query tests.
     */
    private static final String certId = "";

    private CasClient casClient;

    @Before
    public void setUp() {
        casClient = CasClient.createCasClient(accessKey, secretKey, endpoint);
    }

    @Test
    public void testListCerts() {
        CertDetailsListResponse response = casClient.listCerts();
        log.info("listCerts response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getResult());
    }

    @Test
    public void testListCertsWithConditions() {
        ListCertsRequest request = new ListCertsRequest();
        request.setPageNo(1);
        request.setPageSize(10);

        CertDetailsListResponse response = casClient.listCerts(request);
        log.info("listCerts response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getTotalCount() >= 0);
    }

    @Test
    public void testCheckFreeCertPurchasable() {
        CheckPurchasableResponse response = casClient.checkFreeCertPurchasable();
        log.info("checkFreeCertPurchasable response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getFreeCount() >= 0);
    }

    @Test
    public void testCalculatePrice() {
        PriceConfig config = new PriceConfig();
        config.setCertBrand(CertBrand.TRUSTASIA);
        config.setCertType(CertType.DV);
        config.setProductType(ProductType.SINGLE);
        config.setStandardDomainNum(1);
        config.setDuration(1);
        config.setTimeUnit(TimeUnit.YEAR);

        PriceResponse response = casClient.calculatePrice(config);
        log.info("calculatePrice response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getTotalPrice());
    }

    @Test
    public void testGetCertDetails() {
        CertDetails details = casClient.getCertDetails(certId);
        log.info("getCertDetails response: {}", JsonUtils.toJsonString(details));
        Assert.assertNotNull(details);
        Assert.assertEquals(certId, details.getCertId());
    }

    @Test
    public void testGetCertDetailsWithRequest() {
        CertDetailRequest request = new CertDetailRequest();
        request.setCertId(certId);

        CertDetails details = casClient.getCertDetails(certId, request);
        log.info("getCertDetails response: {}", JsonUtils.toJsonString(details));
        Assert.assertNotNull(details);
    }

    @Test
    public void testGetCertPki() {
        CertPkiResponse response = casClient.getCertPki(certId);
        log.info("getCertPki response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetCertContact() {
        CertContactResponse response = casClient.getCertContact(certId);
        log.info("getCertContact response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
    }

    @Test
    public void testDownloadConfirmLetterTemplate() {
        byte[] content = casClient.downloadConfirmLetterTemplate(certId);
        log.info("downloadConfirmLetterTemplate response, content length: {}", content == null ? 0 : content.length);
        Assert.assertNotNull(content);
        Assert.assertTrue(content.length > 0);
    }

    @Test
    public void testDownloadCertFile() {
        DownloadCertFileRequest request = new DownloadCertFileRequest();
        request.setFormat(CertFileType.PEM.getValue());

        byte[] content = casClient.downloadCertFile(certId, request);
        log.info("downloadCertFile response, content length: {}", content == null ? 0 : content.length);
        Assert.assertNotNull(content);
        Assert.assertTrue(content.length > 0);
    }

    @Ignore("creates a paid order")
    @Test
    public void testCreateOrder() {
        NewOrderConfig config = new NewOrderConfig();
        config.setCertBrand(CertBrand.TRUSTASIA);
        config.setCertType(CertType.DV);
        config.setProductType(ProductType.SINGLE);
        config.setStandardDomainNum(1);
        config.setDuration(1);
        config.setTimeUnit(TimeUnit.YEAR);

        ApiOrderResponse response = casClient.createOrder(config);
        log.info("createOrder response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response.getOrderId());
        Assert.assertFalse(response.getCertIds().isEmpty());
    }

    @Ignore("creates a paid renewal order")
    @Test
    public void testCreateRenewOrder() {
        RenewOrderConfig config = new RenewOrderConfig();
        config.setSourceResourceId(certId);
        config.setCertBrand(CertBrand.TRUSTASIA);
        config.setCertType(CertType.DV);
        config.setProductType(ProductType.SINGLE);
        config.setStandardDomainNum(1);
        config.setDuration(1);
        config.setTimeUnit(TimeUnit.YEAR);

        ApiOrderResponse response = casClient.createRenewOrder(config);
        log.info("createRenewOrder response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response.getOrderId());
    }

    @Ignore("submits the certificate application")
    @Test
    public void testApplyCert() {
        // 不抛出异常就表示成功
        ApplyCertRequest request = new ApplyCertRequest();
        request.setPrimaryDomain("test.example.com");

        casClient.applyCert(certId, request);
        log.info("applyCert succeeded, certId: {}", certId);
    }

    @Ignore("re-issues the certificate")
    @Test
    public void testReplaceCert() {
        ReplaceCertRequest request = new ReplaceCertRequest();
        request.setDomain("test.example.com");

        ReplaceResponse response = casClient.replaceCert(certId, request);
        log.info("replaceCert response: {}", JsonUtils.toJsonString(response));
        Assert.assertNotNull(response.getReplaceOrderId());
    }

    @Ignore("uploads the confirmation letter of the certificate")
    @Test
    public void testUploadConfirmLetter() {
        // 不抛出异常就表示成功
        casClient.uploadConfirmLetter(certId, casClient.downloadConfirmLetterTemplate(certId));
        log.info("uploadConfirmLetter succeeded, certId: {}", certId);
    }

    @Ignore("cancels the pending application")
    @Test
    public void testCancelApply() {
        // 不抛出异常就表示成功
        casClient.cancelApply(certId);
        log.info("cancelApply succeeded, certId: {}", certId);
    }

    @Ignore("deletes the certificate")
    @Test
    public void testDeleteApply() {
        // 不抛出异常就表示成功
        casClient.deleteApply(certId);
        log.info("deleteApply succeeded, certId: {}", certId);
    }

    @Ignore("transfers the certificate to another user")
    @Test
    public void testTransCerts() {
        // 不抛出异常就表示成功
        String newUserId = "";
        casClient.transCerts(newUserId, new BatchRequest<String>(Collections.singletonList(certId)));
        log.info("transCerts succeeded, certId: {}, newUserId: {}", certId, newUserId);
    }
}
