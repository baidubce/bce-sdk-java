package com.baidubce.services.ses;

import java.io.File;
import java.net.URISyntaxException;
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
import com.baidubce.services.ses.model.SendEmailResponse;

@RunWith(Parameterized.class)
public class SesClientSendMailWithCcNorQaTest {
	private SesClient client;
	private Jedis jedis;
	private String domainName = null;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inFrom;
	@Parameter(2)
	public String[] inToAddr;
	@Parameter(3)
	public String inSubject;
	@Parameter(4)
	public String inBody;
	@Parameter(5)
	public String[] inAttachName;
	@Parameter(6)
	public String[] inCcAddr;
	@Parameter(7)
	public String[] inBccAddr;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				// 1个toAddr-1个ccAddr-1个bccAddr
				{ 0, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com", },
						"testSubject-1个toAddr-1个ccAddr-1个bccAddr", "testBody",
						new String[] { "/attachment-test1.txt" },
						new String[] { "zinier@tom.com" },
						new String[] { "wanglinqing_1984@126.com" } },
				// 2个toAddr-0个ccAddr-0个bccAddr
				{
						1,
						"wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com",
								"zinier@tom.com" },
						"testSubject-2个toAddr-0个ccAddr-0个bccAddr", "testBody",
						new String[] { "/attachment-test1.txt" },
						new String[] {}, new String[] {} },
				// 1个toAddr-2个ccAddr-0个bccAddr
				{
						2,
						"wanglinqing01@baidu.com",
						new String[] {"wanglinqing01@baidu.com"},
						"testSubject-1个toAddr-2个ccAddr-0个bccAddr",
						"testBody",
						new String[] { "/attachment-test1.txt" },
						new String[] { "zini-er@tom.com","wanglinqing_1984@126.com" }, new String[] {} },
				// 1个toAddr-0个ccAddr-2个bccAddr
				{
						3,
						"wanglinqing01@baidu.com",
						new String[] {"wanglinqing01@baidu.com"},
						"testSubject-1个toAddr-0个ccAddr-2个bccAddr",
						"testBody",
						new String[] { "/attachment-test1.txt" },
						new String[] {},
						new String[] { "zinier@tom.com","wanglinqing-01@126.com" } }, });
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
		domainName = inFrom.split("@")[1];
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inFrom);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, domainName);
		jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inFrom, "success:none");
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inFrom);
		jedis.quit();
	}

	@Test
	public void test() throws URISyntaxException, InterruptedException {
		File[] attachFile = new File[inAttachName.length];
		int i = 0;
		for (String attachName : inAttachName) {
			attachFile[i] = new File(SesClientSendMailSimpleNorQaTest.class
					.getResource(attachName).toURI());
			i++;
		}
		
        SendEmailResponse sResponse =
                this.client.sendEmail(inFrom, null, inToAddr, inCcAddr, inBccAddr, inSubject, inBody, attachFile);
		Thread.sleep(1000);
		Assert.assertNotNull(sResponse.getMessageId());
	}

}
