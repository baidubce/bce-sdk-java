package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import redis.clients.jedis.Jedis;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.VerifyEmailRequest;

@RunWith(Parameterized.class)
public class SesClientVerifyEmailNorQaTest {
	private SesClient client;
	private Jedis jedis;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inEmailAddr;
	@Parameter(2)
	public String inStatus;
	@Parameter(3)
	public String expStatus;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ 0, "wanglinqing01@baidu.com", null, "pending:none" },
						{ 1, "wanglinqing01@baidu.com", "none:none",
								"pending:none" },
						{ 2, "wanglinqing01@baidu.com", "pending:none",
								"pending:none" },
						{ 3, "wanglinqing01@baidu.com", "success:none",
								"pending:none" },
						{ 4, "wanglinqing01@baidu.com", "failed:none",
								"pending:none" },
						{ 5, "wanglinqing01@baidu.com",
								"temporaryfailure:none", "pending:none" },
						{ 6, "wanglinqing01@baidu.com", "notstarted:none",
								"pending:none" },
						{ 7, "wanglinqingNotExist@baidu.com", null,
								"pending:none" },

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
		jedis = new Jedis(SesConf.SES_REDIS_HOST, SesConf.SES_REDIS_PORT);
		// jedis.hdel(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr);
		Thread.sleep(1000);
	}

	@After
	public void tearDown() throws InterruptedException {
		this.client.shutdown();
		// jedis.hdel(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr);
		jedis.quit();
		Thread.sleep(1000);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void test() throws InterruptedException {
		if (tag == 3){
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr, inStatus);
			expectedException.expect(BceServiceException.class);
	    	expectedException.expectMessage("mail addr [" + inEmailAddr + "] already verified, please not re-verify");
	    	this.client.verifyEmail(new VerifyEmailRequest()
				.withEmailAddress(inEmailAddr));
	    	Thread.sleep(1000);
		}
		else{
		switch (tag) {
			case 0:{
				break;
			}
			case 7:{
				break;
			}
			default: {
				jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr, inStatus);
			}
			}

			this.client.verifyEmail(inEmailAddr);
			Thread.sleep(1000);

			Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr));
			Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_STATUS_KEY,
					inEmailAddr));
			Assert.assertEquals(expStatus,
					jedis.hget(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr));

	}
	}

	@Test
	public void testRequest() throws InterruptedException {
		if (tag == 3){
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr, inStatus);
			expectedException.expect(BceServiceException.class);
	    	expectedException.expectMessage("mail addr [" + inEmailAddr + "] already verified, please not re-verify");
	    	this.client.verifyEmail(new VerifyEmailRequest()
				.withEmailAddress(inEmailAddr));
	    	Thread.sleep(1000);
		}
		else{
			switch (tag) {
			case 0: {
				break;
			}
			case 7:{
				break;
			}
			default: {
				jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr, inStatus);
			}
		}

		this.client.verifyEmail(new VerifyEmailRequest()
				.withEmailAddress(inEmailAddr));
		Thread.sleep(1000);

		Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_INFO_KEY, inEmailAddr));
		Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_STATUS_KEY,
				inEmailAddr));
		Assert.assertEquals(expStatus,
				jedis.hget(SesConf.MAIL_CERT_STATUS_KEY, inEmailAddr));
	}
	}
}
