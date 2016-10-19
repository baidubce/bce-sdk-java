package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;

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
import com.baidubce.services.ses.model.VerifyDomainRequest;
import com.baidubce.services.ses.model.VerifyDomainResponse;

@RunWith(Parameterized.class)
public class SesClientVerifyDomainNorQaTest {

	private SesClient client;
	private Jedis jedis;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inDomainName;
	@Parameter(2)
	public String inStatus;
	@Parameter(3)
	public String expStatus;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ 0, "tom.com", null, "notstarted:none" },
				{ 1, "tom.com", "none:none", "notstarted:none" },
				{ 2, "tom.com", "pending:none", "notstarted:none" },
				{ 3, "tom.com", "success:none", "notstarted:none" },
				{ 4, "tom.com", "failed:none", "notstarted:none" },
				{ 5, "tom.com", "temporaryfailure:none", "notstarted:none" },
				{ 6, "tom.com", "notstarted:none", "notstarted:none" }, });
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
//		jedis.hdel(SesConf.DOMAIN_CERT_INFO_KEY, inDomainName);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inDomainName);
		Thread.sleep(1000);
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		jedis.hdel(SesConf.DOMAIN_CERT_INFO_KEY, inDomainName);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inDomainName);
		jedis.quit();
	}

	@Test
	public void test() throws InterruptedException {
		switch (tag) {
		case 0: {
			break;
		}
		default: {
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inDomainName, inStatus);
		}
		}

		VerifyDomainResponse vResponse = this.client.verifyDomain(inDomainName);
		Thread.sleep(1000);

		Assert.assertTrue(vResponse.getToken().length()>0);

		Assert.assertTrue(jedis.hexists(SesConf.DOMAIN_CERT_INFO_KEY,
				inDomainName));
		Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_STATUS_KEY,
				inDomainName));
		Assert.assertEquals(
				jedis.hget(SesConf.MAIL_CERT_STATUS_KEY, inDomainName),
				expStatus);
	}

	@Test
	public void testRequest() throws InterruptedException {
		switch (tag) {
		case 0: {
			break;
		}
		default: {
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inDomainName, inStatus);
		}
		}
		VerifyDomainResponse vResponse = this.client
				.verifyDomain(new VerifyDomainRequest()
						.withDomainName(inDomainName));
		Thread.sleep(1000);

		Assert.assertTrue(vResponse.getToken().length()>0);

		Assert.assertTrue(jedis.hexists(SesConf.DOMAIN_CERT_INFO_KEY,
				inDomainName));
		Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_STATUS_KEY,
				inDomainName));
		Assert.assertEquals(
				jedis.hget(SesConf.MAIL_CERT_STATUS_KEY, inDomainName),
				expStatus);
	}

}
