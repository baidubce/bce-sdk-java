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
public class SesClientSendMailSimpleNorQaTest {
	private SesClient client;
	private Jedis jedis;
	private String domainName = null;
	private String verifyTarget = null;

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

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// toAddr含1个
						{ 
							0,
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing-01@baidu.com", },
							"testSubject-1个toAddr-中文主题", "testBody中文内容",
							new String[] { "/attachment-test1.txt" } },
						// toAddr含2个
						{
							1,
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing2010@126.com",
									"zinier@tom.com" },
							"testSubject-2个toAddr", "testBody",
							new String[] { "/attachment-test1.txt" } },
						// attachment含2个
						{
							2,
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-2个attachment",
							"testBody",
							new String[] { "/attachment-test1.txt",
									"/attachment-test2.md" } },
						// attachment含0个
						{ 
							3, 
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-0个attachment", "testBody",
							new String[] {} },
						// domainVerified
						{ 
							4, "wanglinqing01@baidu.com",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-domainVerified", "testBody",
							new String[] { "/attachment-test1.txt" } },
						// domainVerifiedFail,emailVerifiedSuccess
						{
							5,
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-domainVerifiedFail-emailVerifiedSuccess",
							"testBody",
							new String[] { "/attachment-test1.txt" } },
						//domainVerifiedSuccess,emailVerifiedFail
						{
							6,
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-domainVerifiedSuccess-emailVerifiedFail",
							"testBody",
							new String[] { "/attachment-test1.txt" } }, 
						// attachmentFiles为null
						{ 
							7, 
							"wanglinqing01@baidu.com",
							new String[] { "wanglinqing01@baidu.com", },
									"testSubject-attachmentNull", "testBody中文内容",
							null},
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
		domainName = inFrom.split("@")[1];
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inFrom);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, domainName);

		switch (tag) {
		case 4: {
			verifyTarget = domainName;
			break;
		}
		case 5: {
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, domainName, "fail:none");
			verifyTarget = inFrom;
			break;
		}
		case 6:{
			jedis.hset(SesConf.DOMAIN_CERT_INFO_KEY, domainName, "ZDUyYzAyMGE2OGM1MmY5NGEyZGM4OD");
			jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, domainName, "success:none");
			verifyTarget = domainName;
			break;
		}
		default:
			verifyTarget = inFrom;
		}
		jedis.hset(SesConf.MAIL_CERT_STATUS_KEY, verifyTarget, "success:none");
	}

	@After
	public void tearDown() {
		this.client.shutdown();
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, inFrom);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, domainName);
		jedis.hdel(SesConf.DOMAIN_CERT_INFO_KEY, domainName);
		jedis.hdel(SesConf.MAIL_CERT_STATUS_KEY, verifyTarget);
		jedis.quit();
	}

	@Test
	public void test() throws URISyntaxException, InterruptedException {
		File[] attachFile = null;
		switch(tag){
		case 7:break;
		default:{
			attachFile = new File[inAttachName.length];
			int i = 0;
			for (String attachName : inAttachName) {
				attachFile[i] = new File(SesClientSendMailSimpleNorQaTest.class
						.getResource(attachName).toURI());
				i++;
			}
			
		}
		}
		
		SendEmailResponse sResponse = this.client.sendEmail(inFrom, inToAddr,
				inSubject, inBody, attachFile);
		Thread.sleep(1000);
		Assert.assertNotNull(sResponse.getMessageId());
	}

}
