package com.baidubce.services.bcd;

import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcd.model.AddDomainResolveRequest;
import com.baidubce.services.bcd.model.AuditInfo;
import com.baidubce.services.bcd.model.AuditTemplateInfoRequest;
import com.baidubce.services.bcd.model.ChangeDnsRequest;
import com.baidubce.services.bcd.model.CheckOrderRequest;
import com.baidubce.services.bcd.model.CheckOrderResponse;
import com.baidubce.services.bcd.model.DeleteDomainResolveRequest;
import com.baidubce.services.bcd.model.DeleteTemplateInfoRequest;
import com.baidubce.services.bcd.model.DomainOrderResponse;
import com.baidubce.services.bcd.model.GetDomainAuditRequest;
import com.baidubce.services.bcd.model.GetDomainAuditResponse;
import com.baidubce.services.bcd.model.GetDomainDetailRequest;
import com.baidubce.services.bcd.model.GetDomainDetailResponse;
import com.baidubce.services.bcd.model.GetDomainPriceRequest;
import com.baidubce.services.bcd.model.GetDomainPriceResponse;
import com.baidubce.services.bcd.model.GetTemplateInfoRequest;
import com.baidubce.services.bcd.model.GetTemplateInfoResponse;
import com.baidubce.services.bcd.model.ListDomainResolveRequest;
import com.baidubce.services.bcd.model.ListDomainResolveResponse;
import com.baidubce.services.bcd.model.ListTemplateInfoRequest;
import com.baidubce.services.bcd.model.ListTemplateInfoResponse;
import com.baidubce.services.bcd.model.ModifyTemplateInfoRequest;
import com.baidubce.services.bcd.model.ModifyTemplateInfoResponse;
import com.baidubce.services.bcd.model.Region;
import com.baidubce.services.bcd.model.RegisterDomainRequest;
import com.baidubce.services.bcd.model.RegisterDomainResponse;
import com.baidubce.services.bcd.model.RenewDomainRequest;
import com.baidubce.services.bcd.model.RenewDomainResponse;
import com.baidubce.services.bcd.model.SearchDomainRequest;
import com.baidubce.services.bcd.model.SearchDomainResponse;
import com.baidubce.services.bcd.model.TemplateRegisterDomainRequest;
import com.baidubce.services.bcd.model.TemplateUpdateOwnerRequest;
import com.baidubce.services.bcd.model.UpdateContactRequest;
import com.baidubce.services.bcd.model.UpdateDomainResolveRequest;
import com.baidubce.services.bcd.model.UpdateOwnerRequest;
import com.baidubce.services.bcd.model.UploadAuditDataRequest;
import com.google.common.collect.Lists;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yangzhensheng
 * @date 2021/5/27
 * @desc
 */
@RunWith(Enclosed.class)
public class BcdClientTest {

    public static class BcdBase {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(BcdClientTest.class);
        protected final String ak = "your ak";
        protected final String sk = "your sk";
        protected static String endpoint = "xxx";

        protected BcdClientConfiguration config;

        public void setUp() {
            this.config = new BcdClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);
        }

        public void tearDown() {
            logger.info("Base test tearDown");
        }

    }

    public static class InstanceTest extends BcdBase {
        protected BcdClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new BcdClient(config);
        }

        @After
        public void tearDown() {
            super.tearDown();
        }

        /**
         * add domain resolve record
         */
        @Test
        public void addDomainResolveTest() {
            AddDomainResolveRequest request = new AddDomainResolveRequest();
            request.setDomain("ddd");
            request.setZoneName("fdsafasfaad.net");
            request.setView("DEFAULT");
            request.setTtl(60);
            request.setRdType("A");
            request.setRdata("127.0.0.1");
            client.addDomainResolve(request);
        }

        /**
         * delete domain resolve record
         */
        @Test
        public void deleteDomainResolveTest() {
            DeleteDomainResolveRequest request = new DeleteDomainResolveRequest();
            request.setRecordId(2153);
            request.setZoneName("ohprujdftrppgln.com");
            client.deleteDomainResolve(request);
        }

        /**
         * update domain resolve record by recordId which can find by the list interface
         */
        @Test
        public void updateDomainResolveTest() {
            UpdateDomainResolveRequest request = new UpdateDomainResolveRequest();
            request.setDomain("ddd");
            request.setZoneName("ohprujdftrppgln.com");
            request.setView("DEFAULT");
            request.setTtl(60);
            request.setRdType("A");
            request.setRdata("118.118.118.118");
            request.setRecordId(2153);
            client.updateDomainResolve(request);
        }

        /**
         * get all template infos
         */
        @Test
        public void getTemplateInfoListTest() {
            ListTemplateInfoRequest request = new ListTemplateInfoRequest();
            request.setPageSize(5);
            request.setPageNo(1);
            ListTemplateInfoResponse result = client.listTemplateInfo(request);
        }

        /**
         * add template info
         */
        @Test
        public void addTemplateInfoTest() {
            ModifyTemplateInfoRequest request = new ModifyTemplateInfoRequest();
            request.setUserType("INDIVIDUAL");
            request.setPostalCode("100000");
            request.setOwnerEnglish("Zhang San");
            request.setOwnerChinese("张三");
            request.setMobilePhone("13355201111");
            request.setEmail("xxx@163.com");
            request.setCountryCode("86");
            request.setContactEnglish("Zhang San");
            request.setContactChinese("张三");
            request.setAreaCode("");
            request.setAddressEnglish("beijing aaa");
            request.setAddressChinese("北京市海淀区");
            Region region = new Region();
            region.setCountry("China");
            region.setProvince("BeiJing");
            region.setCity("BeiJing");
            request.setRegion(region);
            ModifyTemplateInfoResponse response = client.addTemplateInfo(request);
        }

        /**
         * get template info by templateId
         */
        @Test
        public void getTemplteInfoTest() {
            GetTemplateInfoRequest request = new GetTemplateInfoRequest();
            request.setTemplateId("d3789a19f9e7bcfdc9e27a3df06ef31a");
            GetTemplateInfoResponse result = client.getTemplateInfo(request);
        }

        /**
         * delete template info by templateId
         */
        @Test
        public void deleteTemplateInfoTest() {
            DeleteTemplateInfoRequest request = new DeleteTemplateInfoRequest();
            request.setTemplateId("51452efb1deec441e4a2eebc1d65a73c");
            client.deleteTemplateInfo(request);
        }

        /**
         * update template info
         */
        @Test
        public void updateTemplateInfoTest() {
            ModifyTemplateInfoRequest request = new ModifyTemplateInfoRequest();
            request.setTemplateId("51452efb1deec441e4a2eebc1d65a73c");
            request.setUserType("INDIVIDUAL");
            request.setPostalCode("200000");
            request.setOwnerEnglish("Li Si");
            request.setOwnerChinese("李四");
            request.setMobilePhone("18802357700");
            request.setEmail("lisi@163.com");
            request.setCountryCode("86");
            request.setContactEnglish("Li Si");
            request.setContactChinese("李四");
            request.setAddressEnglish("beijing aaa");
            request.setAddressChinese("北京市海淀区信息路");
            Region region = new Region();
            region.setCountry("China");
            region.setProvince("BeiJing");
            region.setCity("BeiJing");
            request.setRegion(region);
            client.updateTemplateInfo(request);
        }

        /**
         * audit template info
         */
        @Test
        public void auditTemplteInfoTest() {
            AuditTemplateInfoRequest request = new AuditTemplateInfoRequest();
            request.setTemplateId("b6822cde1622d617d5a74e9caec9c5c2");

            AuditInfo auditInfo = new AuditInfo();
            auditInfo.setCertType("SFZ");
            auditInfo.setOwnerCode("341282199107018623");
            auditInfo.setAuditFile("aaa");
            request.setAuditInfo(auditInfo);
            client.auditTemplateInfo(request);
        }

        /**
         * get domain resolve record list test.
         */
        @Test
        public void listDomainResolveTest() {
            ListDomainResolveRequest request = new ListDomainResolveRequest();
            request.setDomain("fdsafasfaad.net");
            ListDomainResolveResponse response = client.listDomainResolve(request);
        }

        /**
         * change owner test.
         */
        @Test
        public void updateOwnerTest() {
            UpdateOwnerRequest request = new UpdateOwnerRequest();
            request.setDomain("fasdfqwerq.com");
            request.setUserType("INDIVIDUAL");
            request.setOwnerEnglish("zhangsan");
            request.setOwnerChinese("李四");
            request.setContactEnglish("zhangsan");
            request.setContactChinese("李四");
            request.setEmail("zhangsan@163.com");
            client.updateOwner(request);
        }

        /**
         * change owner by template test.
         */
        @Test
        public void templateUpdateOwnerTest() {
            TemplateUpdateOwnerRequest request = new TemplateUpdateOwnerRequest();
            request.setDomain("asd12312341339995.top");
            request.setTemplateId("80846cf0eb8a65afdd56453953f35f0d");
            client.updateOwnerByTemplate(request);
        }

        /**
         * change contact test.
         */
        @Test
        public void updateContactTest() {
            UpdateContactRequest request = new UpdateContactRequest();
            request.setDomain("fdsafasfaad.net");
            request.setUserType("INDIVIDUAL");
            request.setContactChinese("李四");
            request.setContactEnglish("li si");
            request.setAddressChinese("北京市昌平区");
            request.setAddressEnglish("BeiJing Changping");
            request.setPhoneNumber("");
            request.setCountryCode("86");
            request.setAreaCode("021");
            request.setPostalCode("200000");
            request.setMobilePhone("13366201024");
            Region region = new Region();
            region.setCountry("China");
            region.setProvince("BeiJing");
            region.setCity("BeiJing");
            request.setRegion(region);
            client.updateContact(request);
        }

        /**
         * get domain audit status info test.
         */
        @Test
        public void getDomainAuditTest() {
            GetDomainAuditRequest request = new GetDomainAuditRequest();
            request.setDomain("fasdfqwerq.com");
            GetDomainAuditResponse domainAudit = client.getDomainAudit(request);
        }

        /**
         * change dns info test.
         */
        @Test
        public void changeDnsTest() {
            ChangeDnsRequest request = new ChangeDnsRequest();
            request.setDomain("fasdfqwerq.com");
            request.setDns(new String[] {"223.5.5.5", "8.8.8.8"});
            client.changeDns(request);
        }

        /**
         * get domain detail info test.
         */
        @Test
        public void getDomainDetailTest() {
            GetDomainDetailRequest request = new GetDomainDetailRequest();
            request.setDomain("fasdfqwerq.com");
            GetDomainDetailResponse domainDetail = client.getDomainDetail(request);
        }

        /**
         * audit domain status info test.
         */
        @Test
        public void uploadAuditTest() {
            UploadAuditDataRequest request = new UploadAuditDataRequest();
            request.setDomain("asdfasfasdfczxwecxv.com");
            request.setFile("your file data");
            request.setCertificationNo("372301199609120336");
            client.uploadAudit(request);
        }

        /**
         * search domain test.
         */
        @Test
        public void searchDomainTest() {
            SearchDomainRequest request = new SearchDomainRequest();
            request.setDomain("asd12312344339995.com");
            SearchDomainResponse response = client.searchDomain(request);
        }

        /**
         * get domain price test.
         */
        @Test
        public void getDomainPriceTest() {
            GetDomainPriceRequest request = new GetDomainPriceRequest();
            request.setDomain("asd12312344339995.com");
            GetDomainPriceResponse response = client.getDomainPrice(request);
        }

        /**
         * register domain test.
         */
        @Test
        public void registerDomainTest() {
            RegisterDomainRequest request = new RegisterDomainRequest();
            request.setDomain("asd12312344339995.top");
            request.setUserType("INDIVIDUAL");
            request.setOwnerChinese("测试用");
            request.setOwnerEnglish("ceshiyong");
            request.setContactChinese("测试用");
            request.setContactEnglish("ceshiyong");
            request.setEmail("ceshiyong@126.com");
            request.setYears(1);
            request.setAddressChinese("测试用的地址");
            request.setAddressEnglish("ceshiyong de dizhi");
            request.setPostalCode("100000");
            request.setMobilePhone("13812345678");
            request.setAreaCode("010");
            request.setPhoneNumber("12345678");
            RegisterDomainRequest.Region region = new RegisterDomainRequest.Region();
            region.setCity("110000");
            region.setProvince("110000");
            request.setRegion(region);
            request.setPrivacy(false);
            request.setDns(Lists.newArrayList("dns1.baidu.com", "dns2.baidu.com"));
            RegisterDomainResponse response = client.registerDomain(request);
        }

        /**
         * async register domain test.
         */
        @Test
        public void asyncRegisterDomainTest() {
            RegisterDomainRequest request = new RegisterDomainRequest();
            request.setDomain("asd12312341339995.top");
            request.setUserType("INDIVIDUAL");
            request.setOwnerChinese("测试用");
            request.setOwnerEnglish("ceshiyong");
            request.setContactChinese("测试用");
            request.setContactEnglish("ceshiyong");
            request.setEmail("ceshiyong@126.com");
            request.setYears(1);
            request.setAddressChinese("测试用的地址");
            request.setAddressEnglish("ceshiyong de dizhi");
            request.setPostalCode("100000");
            request.setMobilePhone("13812345678");
            request.setAreaCode("010");
            request.setPhoneNumber("12345678");
            RegisterDomainRequest.Region region = new RegisterDomainRequest.Region();
            region.setCity("110000");
            region.setProvince("110000");
            request.setRegion(region);
            request.setPrivacy(false);
            request.setDns(Lists.newArrayList("dns1.baidu.com", "dns2.baidu.com"));
            DomainOrderResponse response = client.asyncRegisterDomain(request);
        }

        /**
         * register domain by template test.
         */
        @Test
        public void templateRegisterDomainTest() {
            TemplateRegisterDomainRequest request = new TemplateRegisterDomainRequest();
            request.setDomain("asd12312344339997.top");
            request.setYears(1);
            request.setPrivacy(false);
            request.setDns(Lists.newArrayList("dns1.baidu.com", "dns2.baidu.com"));
            request.setTemplateId("4afb70196d9bf04e4625c0b994e42a8d");
            RegisterDomainResponse response = client.registerDomainByTemplate(request);
        }

        /**
         * async register domain by template test.
         */
        @Test
        public void asyncTemplateRegisterDomainTest() {
            TemplateRegisterDomainRequest request = new TemplateRegisterDomainRequest();
            request.setDomain("asd12312344339907.top");
            request.setYears(1);
            request.setPrivacy(false);
            request.setDns(Lists.newArrayList("dns1.baidu.com", "dns2.baidu.com"));
            request.setTemplateId("4afb70196d9bf04e4625c0b994e42a8d");
            DomainOrderResponse response = client.asyncRegisterDomainByTemplate(request);
        }

        /**
         * check order status test.
         */
        @Test
        public void checkOrderStatusTest() {
            CheckOrderRequest request = new CheckOrderRequest();
            request.setBceOrderId("dadb959dd18e45058d809989fb8130ce");
            CheckOrderResponse response = client.checkOrderStatus(request);
        }

        /**
         * renew domain test.
         */
        @Test
        public void renewOrderTest() {
            RenewDomainRequest request = new RenewDomainRequest();
            request.setDomain("asd12312344339907.top");
            request.setYears(1);
            RenewDomainResponse response = client.renewDomain(request);
        }

        /**
         * async renew domain test.
         */
        @Test
        public void asyncRenewOrderTest() {
            RenewDomainRequest request = new RenewDomainRequest();
            request.setDomain("asd12312344339907.top");
            request.setYears(1);
            DomainOrderResponse response = client.asyncRenewDomain(request);
        }
    }
}
