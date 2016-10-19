package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import redis.clients.jedis.Jedis;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.SesRequest;
import com.baidubce.services.ses.model.GetQuotaResponse;

//@RunWith(Parameterized.class)
public class SesClientSetGetQuotaNorQaTest {

	private SesClient client;
	private Jedis jedis;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public int inMaxPerDay;
	@Parameter(2)
	public int inMaxPerSecond;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
//				{ 0, 100, 10 },
				// 不传入任何参数，则设置为默认值
//				{ 1, 100, 10 }, 
				});
	}

	@Before
	public void setUp() {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
		this.jedis = new Jedis(SesConf.SES_REDIS_HOST, SesConf.SES_REDIS_PORT);
	}

	@After
	public void tearDown() {
		this.client.shutdown();
	}

	@Test
	public void test() throws InterruptedException {
//		SetQuotaRequest sRequest = new SetQuotaRequest();
//		switch (tag) {
//		case 1:
//			break;
//		default: {
//			sRequest.setMaxPerDay(inMaxPerDay);
//			sRequest.setMaxPerSecond(inMaxPerSecond);
//		}
//		}
//		this.client.setQuota(sRequest);
//		Thread.sleep(1000);

		GetQuotaResponse gResponse = this.client.getQuota();
		Thread.sleep(1000);

		Assert.assertEquals(jedis.get(SesConf.MAIL_QUOTA_KEY), gResponse.getMaxPerDay().toString());
		Assert.assertEquals(jedis.get(SesConf.MAIL_RATE_LIMIT_KEY), gResponse.getMaxPerSecond().toString());
		Assert.assertEquals(jedis.get(SesConf.MAIL_SEND_COUNT_KEY), gResponse
				.getUsedToday().toString());
	}

	@Test
	public void testRequest() throws InterruptedException {

		GetQuotaResponse gResponse = this.client.getQuota(new SesRequest());
		Thread.sleep(1000);
		
		Assert.assertEquals(jedis.get(SesConf.MAIL_QUOTA_KEY), gResponse.getMaxPerDay().toString());
		Assert.assertEquals(jedis.get(SesConf.MAIL_RATE_LIMIT_KEY), gResponse.getMaxPerSecond().toString());
		Assert.assertEquals(jedis.get(SesConf.MAIL_SEND_COUNT_KEY), gResponse
				.getUsedToday().toString());
	}

}
