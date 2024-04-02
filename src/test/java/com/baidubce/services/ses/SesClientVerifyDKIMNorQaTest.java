package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
import com.baidubce.services.ses.model.VerifyDKIMRequest;
import com.baidubce.services.ses.model.VerifyDKIMResponse;

@RunWith(Parameterized.class)
public class SesClientVerifyDKIMNorQaTest {

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
	@Parameter(4)
	public String inMailDomainCert;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ 0, "tom.com", null, "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 1, "tom.com", "none:none", "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 2, "tom.com", "none:pending", "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 3, "tom.com", "none:success", "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 4, "tom.com", "none:failed", "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 5, "tom.com", "none:temporaryfailure", "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 6, "tom.com", "none:notstarted", "none:notstarted", "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" }, 
				}
		);
	}

	@Before
	public void setUp() {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
		jedis = new Jedis(SesConf.SES_REDIS_HOST, SesConf.SES_REDIS_PORT);
		jedis.hset(SesConf.DOMAIN_CERT_INFO_KEY, inDomainName, inMailDomainCert);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inDomainName);
		jedis.hdel(SesConf.DKIM_CERT_INFO_KEY, inDomainName);
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		jedis.hdel(SesConf.DOMAIN_CERT_INFO_KEY, inDomainName);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inDomainName);
		jedis.hdel(SesConf.DKIM_CERT_INFO_KEY, inDomainName);
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

		VerifyDKIMResponse vResponse = this.client.verifyDKIM(inDomainName);
		Thread.sleep(1000);

		Assert.assertTrue(vResponse.getToken().getKeys().size() > 0);

		Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_STATUS_KEY,
				inDomainName));
		Assert.assertTrue(jedis
				.hexists(SesConf.DKIM_CERT_INFO_KEY, inDomainName));
		Assert.assertEquals(expStatus,
				jedis.hget(SesConf.MAIL_CERT_STATUS_KEY, inDomainName));
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

		VerifyDKIMResponse vResponse = this.client
				.verifyDKIM(new VerifyDKIMRequest()
						.withDomainName(inDomainName));
		Thread.sleep(1000);
		
		Assert.assertTrue(vResponse.getToken().getKeys().size() > 0);

		Assert.assertTrue(jedis.hexists(SesConf.MAIL_CERT_STATUS_KEY,
				inDomainName));
		Assert.assertTrue(jedis
				.hexists(SesConf.DKIM_CERT_INFO_KEY, inDomainName));
		Assert.assertEquals(expStatus,
				jedis.hget(SesConf.MAIL_CERT_STATUS_KEY, inDomainName));
	}

}
