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
public class SesClientSendMailSimpleWithNameNorQaTest {
	private SesClient client;
	private Jedis jedis;
	private String domainName = null;
	private String verifyTarget = null;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public String inFrom;
	@Parameter(2)
	public String inName;
	@Parameter(3)
	public String[] inToAddr;
	@Parameter(4)
	public String inSubject;
	@Parameter(5)
	public String inBody;
	@Parameter(6)
	public String[] inAttachName;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// toAddr含1个
						{ 
							0,
							"wanglinqing01@baidu.com",
							"王林庆01",
							new String[] { "675686650@qq.com", },
							"testSubject-1个toAddr-中文主题-自定义名称", "testBody中文内容",
							new String[] { "/attachment-test1.txt" } },
						// toAddr含2个
						{
							1,
							"wanglinqing01@baidu.com",
							"测试人员abc",
							new String[] { "wanglinqing01@baidu.com",
									"wanglinqing2010@126.com" },
							"testSubject-2个toAddr-自定义名称", "testBody",
							new String[] { "/attachment-test1.txt" } },
						// attachment含2个
						{
							2,
							"wanglinqing01@baidu.com",
							"自定义名称！@#￥%……&*（）——+",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-2个attachment-自定义名称",
							"testBody",
							new String[] { "/attachment-test1.txt",
									"/attachment-test2.md" } },
						// attachment含0个
						{ 
							3, 
							"wanglinqing01@baidu.com",
							"自定义名称sdfghjdfghjksdgfhjsdfghjsdfadghghjjklklkyytyyuiuoiuoioioioiooioioioigfgfhjkjllk,,m,mxxzxzsdsdsds",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-0个attachment-自定义名称", "testBody",
							new String[] {} },
						// domainVerified
						{ 
							4, "wanglinqing01@baidu.com",
							"域名被验证",
							new String[] { "wanglinqing01@baidu.com" },
							"testSubject-domainVerified-自定义名称", "testBody",
							new String[] { "/attachment-test1.txt" } },
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
				attachFile[i] = new File(SesClientSendMailSimpleWithNameNorQaTest.class
						.getResource(attachName).toURI());
				i++;
			}
			
		}
		}
		
		SendEmailResponse sResponse = this.client.sendEmail(inFrom, inName, 
				inToAddr, inSubject, inBody, attachFile);
		Thread.sleep(1000);
		Assert.assertNotNull(sResponse.getMessageId());
	}

}
