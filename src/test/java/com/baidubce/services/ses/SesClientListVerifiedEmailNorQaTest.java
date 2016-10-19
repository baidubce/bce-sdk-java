package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import redis.clients.jedis.Jedis;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.SesRequest;
import com.baidubce.services.ses.model.EmailDetailModel;
import com.baidubce.services.ses.model.ListVerifiedEmailResponse;

@RunWith(Parameterized.class)
public class SesClientListVerifiedEmailNorQaTest {

	private SesClient client;
	private Jedis jedis;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inEmailAddr;
	@Parameter(2)
	public String inStatus;
	@Parameter(3)
	public int inStatusCode;
	@Parameter(4)
	public String inMailAddrCert;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ 0, "wanglinqing01@baidu.com", "none:none", 5, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 1, "wanglinqing01@baidu.com", "pending:none", 3, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 2, "wanglinqing01@baidu.com", "success:none", 0, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 3, "wanglinqing01@baidu.com", "failed:none", 1, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 4, "wanglinqing01@baidu.com", "temporaryfailure:none", 2, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 5, "wanglinqing01@baidu.com", "notstarted:none", 4, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },

		});
	}

	@Before
	public void setUp() throws InterruptedException {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
		this.jedis = new Jedis(SesConf.SES_REDIS_HOST, SesConf.SES_REDIS_PORT);
		jedis.hset(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr, inMailAddrCert);
		jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr, inStatus);
		Thread.sleep(1000);
	}

	@After
	public void tearDown() throws InterruptedException {
		this.client.shutdown();
		jedis.hdel(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr);
		this.jedis.quit();
		Thread.sleep(1000);
	}

	@Test
	public void test() throws InterruptedException {
		ListVerifiedEmailResponse lResponse = this.client.listVerifiedEmail();
		Thread.sleep(1000);

		executeAssert(lResponse);
	}

	 @Test
	public void testRequest() throws InterruptedException {
		ListVerifiedEmailResponse lResponse = this.client
				.listVerifiedEmail(new SesRequest());
		Thread.sleep(1000);
		executeAssert(lResponse);
	}
	
	private void executeAssert(ListVerifiedEmailResponse lResponse){
		int validAddrCount = 0;
		Iterator<String> rIterator = jedis.hgetAll(SesConf.MAIL_CERT_STATUS_KEY)
				.keySet().iterator();
		while (rIterator.hasNext()) {
			if (rIterator.next().contains("@")) {
				validAddrCount++;
			}

		}
		Assert.assertEquals(validAddrCount, lResponse.getDetails().size());

		Iterator<EmailDetailModel> eIterator = lResponse.getDetails()
				.iterator();
		while (eIterator.hasNext()) {
			EmailDetailModel emailDetail = eIterator.next();
			if (emailDetail.getAddress().equals(inEmailAddr)) {
				Assert.assertEquals(inStatusCode, emailDetail.getStatus()
						.intValue());
			}
		}
	}

}
