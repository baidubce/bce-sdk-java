package com.baidubce.services.ses;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class SesClientSendMailSimpleAbnorQaTest {
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
	public String expException;
	@Parameter(7)
	public String expMessage;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				// from为null
				{ 0, null, new String[] { "wanglinqing01@baidu.com", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "from should not be null or empty" },
				// from为""
				{ 1, "", new String[] { "wanglinqing01@baidu.com", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "from should not be null or empty" },
				// from为" "
				{ 2, " ", new String[] { "wanglinqing01@baidu.com", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "from should not be null or empty" },
				// from不符合格式"xxx@yyy"
				{ 3, "abc", new String[] { "wanglinqing01@baidu.com", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "illegal email" },
				// toAddr为null
				{ 4, "wanglinqing01@baidu.com", null, "testSubject",
						"testBody", new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "toAddr should not be null or empty" },
				// toAddr全部为""
				{ 5, "wanglinqing01@baidu.com", new String[] { "", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "illegal email" },
				// toAddr全部为" "
				{ 6, "wanglinqing01@baidu.com", new String[] { " ", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "illegal email" },
				// toAddr全部不符合格式"xxx@yyy"
				{ 7, "wanglinqing01@baidu.com", new String[] { "abc", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "illegal email" },
				// subject为null
				{ 8, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com" }, null,
						"testBody", new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "subject should not be null or empty" },
				// subject为""
				{ 9, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com" }, "",
						"testBody", new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "subject should not be null or empty" },
				// subject为" "
				{ 10, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com" }, " ",
						"testBody", new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "subject should not be null or empty" },
				// body为null
				{ 11, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com" },
						"testSubject", null,
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "body should not be null or empty" },
				// body为""
				{ 12, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com" },
						"testSubject", "",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "body should not be null or empty" },
				// body为" "
				{ 13, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com" },
						"testSubject", " ",
						new String[] { "/attachment-test1.txt" },
						"java.lang.IllegalArgumentException", "body should not be null or empty" },

				// from未经验证
				{ 14, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"com.baidubce.BceServiceException",
						"sender address is not verified" },
				// from验证fail
				{ 15, "wanglinqing01@baidu.com",
						new String[] { "wanglinqing01@baidu.com", },
						"testSubject", "testBody",
						new String[] { "/attachment-test1.txt" },
						"com.baidubce.BceServiceException",
						"sender address is not verified" },

		});
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
		this.jedis = new Jedis(SesConf.SES_REDIS_HOST, SesConf.SES_REDIS_PORT);

		if (inFrom != null && !inFrom.trim().isEmpty()) {
			if (inFrom.contains("@")) {
				domainName = inFrom.split("@")[1];
				jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, domainName);
			}
			jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inFrom);

			switch (tag) {
			case 14: {
				break;
			}
			case 15: {
				jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inFrom, "fail:none");
				break;
			}
			default:
				jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, inFrom, "success:none");
			}
		}
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		if (inFrom != null && !inFrom.isEmpty()) {
			jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inFrom);
		}
		jedis.quit();
	}

	@Test
	public void test() throws URISyntaxException, ClassNotFoundException,
			InterruptedException {
		thrown.expect((Class<? extends Throwable>) Class.forName(expException));
		thrown.expectMessage(expMessage);

		File[] attachFile = new File[inAttachName.length];
		int i = 0;
		for (String attachName : inAttachName) {
			attachFile[i] = new File(SesClientSendMailSimpleNorQaTest.class
					.getResource(attachName).toURI());
			i++;
		}

		SendEmailResponse sResponse = this.client.sendEmail(inFrom, inToAddr,
				inSubject, inBody, attachFile);
		Thread.sleep(1000);
	}

}
