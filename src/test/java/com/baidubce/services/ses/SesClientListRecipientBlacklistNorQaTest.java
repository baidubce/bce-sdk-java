package com.baidubce.services.ses;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.SesRequest;
import com.baidubce.services.ses.model.ListRecipientBlacklistResponse;

public class SesClientListRecipientBlacklistNorQaTest {
	private SesClient client;
	private Jedis jedis;

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
	public void test() {
		ListRecipientBlacklistResponse lResponse = this.client
				.listRecipientBlacklist();
		Assert.assertEquals(jedis.hlen(SesConf.RECP_BLACKLIST_KEY).intValue(),lResponse.getRecpt().size());
	}

	@Test
	public void testRequest() {
		ListRecipientBlacklistResponse lResponse = this.client
				.listRecipientBlacklist(new SesRequest());
		Assert.assertEquals(jedis.hlen(SesConf.RECP_BLACKLIST_KEY).intValue(),lResponse.getRecpt().size());
	}

}
