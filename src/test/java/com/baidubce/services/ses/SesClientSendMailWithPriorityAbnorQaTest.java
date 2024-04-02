package com.baidubce.services.ses;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
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
import com.baidubce.services.ses.model.SendEmailRequest;
import com.baidubce.services.ses.model.SendEmailResponse;
import com.baidubce.services.ses.model.SendEmailRequest.Mail;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Message;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Source;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Subject;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination.Addr;

@RunWith(Parameterized.class)
public class SesClientSendMailWithPriorityAbnorQaTest {
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
	@Parameter(8)
	public int inPriority;
	@Parameter(9)
	public int inCharset;
	@Parameter(10)
	public String inReturnPath;
	@Parameter(11)
	public String inReplyTo;
	@Parameter(12)
	public String expException;
	@Parameter(13)
	public String expMessage;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// Priority=-1
						{ 0, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset0-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, -1, 0, null,
								null, "java.lang.IllegalArgumentException",
								"illegal priority" },
						// Priority=100
						{ 1, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset0-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 100, 0, null,
								null, "java.lang.IllegalArgumentException",
								"illegal priority" },
						// charset=-1
						{ 2, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset0-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 0, -1, null,
								null, "java.lang.IllegalArgumentException",
								"illegal charset" },
						// charset=5
						{ 3, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset0-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 0, 5, null,
								null, "java.lang.IllegalArgumentException",
								"illegal charset" }, });
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
	public void test() throws URISyntaxException, InterruptedException, ClassNotFoundException {
		thrown.expect((Class<? extends Throwable>) Class.forName(expException));
		thrown.expectMessage(expMessage);

		File[] attachFile = new File[inAttachName.length];
		int i = 0;
		for (String attachName : inAttachName) {
			attachFile[i] = new File(SesClientSendMailSimpleNorQaTest.class
					.getResource(attachName).toURI());
			i++;
		}
        SendEmailResponse sResponse =
                this.client.sendEmail(inFrom, null, inReturnPath, inReplyTo, inToAddr, inCcAddr, inBccAddr, inSubject,
                        inBody, inPriority, inCharset, attachFile);
		Assert.assertNotNull(sResponse.getMessageId());
		Thread.sleep(1000);
	}

	@Test
	public void testRequest() throws URISyntaxException, InterruptedException, ClassNotFoundException {
		thrown.expect((Class<? extends Throwable>) Class.forName(expException));
		thrown.expectMessage(expMessage);

		Source source = new Source();
		source.setFrom(inFrom);
		source.setReturnPath(inReturnPath);
		source.setReplyTo(inReplyTo);

		Destination dest = new Destination();
		dest.setToAddr(Addr.asAddrList(inToAddr));
		dest.setCcAddr(Addr.asAddrList(inCcAddr));
		dest.setBccAddr(Addr.asAddrList(inBccAddr));

		Subject subject = new Subject();
		subject.setData(inSubject);
		subject.setCharset(new Integer(inCharset));

		Message message = new Message();
		Subject bodySubject = new Subject();
		bodySubject.setData(inBody);
		bodySubject.setCharset(new Integer(inCharset));
		message.setHtml(bodySubject);

		Mail mail = new Mail();
		mail.setSource(source);
		mail.setDestination(dest);
		mail.setSubject(subject);
		mail.setMessage(message);
		mail.setPriority(new Integer(inPriority));

		SendEmailRequest sRequest = new SendEmailRequest();
		sRequest.setMail(mail);

		File[] attachFile = new File[inAttachName.length];
		int i = 0;
		for (String attachName : inAttachName) {
			attachFile[i] = new File(SesClientSendMailSimpleNorQaTest.class
					.getResource(attachName).toURI());
			i++;
		}

		SendEmailResponse sResponse = this.client.sendEmail(sRequest);
		Thread.sleep(1000);
		Assert.assertNotNull(sResponse.getMessageId());
	}
}
