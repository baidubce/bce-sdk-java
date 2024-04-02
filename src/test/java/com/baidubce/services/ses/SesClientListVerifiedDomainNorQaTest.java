package com.baidubce.services.ses;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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
import com.baidubce.services.ses.model.SesRequest;
import com.baidubce.services.ses.model.DomainDetailModel;
import com.baidubce.services.ses.model.ListVerifiedDomainResponse;

@RunWith(Parameterized.class)
public class SesClientListVerifiedDomainNorQaTest {

	private SesClient client;
	private Jedis jedis;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inDomainName;
	@Parameter(2)
	public String inStatus;
	@Parameter(3)
	public int inStatusCode;
	@Parameter(4)
	public String inMailDomainCert;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ 0, "tom.com", "success:none", 0, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 1, "tom.com", "failed:none", 1, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 2, "tom.com", "temporaryfailure:none", 2, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 3, "tom.com", "pending:none", 3, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 4, "tom.com", "notstarted:none", 4, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
				{ 5, "tom.com", "none:none", 5, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD" },
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
		jedis.hset(SesConf.DOMAIN_CERT_INFO_KEY, inDomainName, inMailDomainCert);
		jedis.hset(SesConf.MAIL_CERT_INFO_KEY, inDomainName, inMailDomainCert);
		jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inDomainName, inStatus);
		Thread.sleep(1000);
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		jedis.hdel(SesConf.DOMAIN_CERT_INFO_KEY, inDomainName);
		jedis.hdel(SesConf.DKIM_CERT_INFO_KEY, inDomainName);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inDomainName);
		this.jedis.quit();
	}

	@Test
	public void test() throws InterruptedException {
		ListVerifiedDomainResponse lResponse = this.client.listVerifiedDomain();
		Thread.sleep(1000);

		executeAssert(lResponse);
	}

	@Test
	public void testRequest() throws InterruptedException {
		ListVerifiedDomainResponse lResponse = this.client
				.listVerifiedDomain(new SesRequest());
		Thread.sleep(1000);

		executeAssert(lResponse);
	}

	private void executeAssert(ListVerifiedDomainResponse lResponse) {
		int validDomainCount = 0;
		Iterator<String> rIterator = jedis.hgetAll(SesConf.MAIL_CERT_STATUS_KEY)
				.keySet().iterator();
		while (rIterator.hasNext()) {
			if (!rIterator.next().contains("@")) {
				validDomainCount++;
			}

		}
		Assert.assertEquals(validDomainCount, lResponse.getDetails().size());

		Iterator<DomainDetailModel> eIterator = lResponse.getDetails()
				.iterator();
		while (eIterator.hasNext()) {
			DomainDetailModel domainDetail = eIterator.next();
			if (domainDetail.getDomain().equals(inDomainName)) {
				Assert.assertEquals(inStatusCode, domainDetail.getDomainAttr()
						.getStatus().intValue());
			}
		}
	}

}
