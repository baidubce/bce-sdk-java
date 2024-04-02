package com.baidubce.services.ses;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
import com.baidubce.services.ses.model.SendEmailRequest;
import com.baidubce.services.ses.model.SendEmailResponse;
import com.baidubce.services.ses.model.SendEmailRequest.Mail;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Attachment;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Message;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Source;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Subject;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination.Addr;

@RunWith(Parameterized.class)
public class SesClientSendMailWithPriorityNorQaTest {
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
	public String[][] inAttachData;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						// Priority=0,charset=0
						{ 0, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset0-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 0, 0, null,
								null,new String[][]{{"attach1.file","附件内容1"},{"attach2.file","附件内容2"}} },
						// Priority=99,charset=1
						{ 1, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority99-charset1-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 99, 1, null,
								null ,new String[][]{{"attach1.file","附件内容1"},{"attach2.file","附件内容2"}} },
						// Priority=0,charset=2
						{ 2, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset2-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 0, 2, null,
								null ,new String[][]{{"attach1.file","附件内容1"},{"attach2.file","附件内容2"}} },
						// Priority=0,charset=3
						{ 3, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset3-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 0, 3, null,
								null ,new String[][]{{"attach1.file","附件内容1"},{"attach2.file","附件内容2"}} },
						// Priority=0,charset=4
						{ 4, "wanglinqing01@baidu.com",
								new String[] { "wanglinqing01@baidu.com", },
								"testSubject-Priority0-charset4-中文标题",
								"testBody-中文内容",
								new String[] { "/attachment-test1.txt" },
								new String[] {}, new String[] {}, 0, 4, null,
								null ,new String[][]{{"attach1.file","附件内容1"},{"attach2.file","附件内容2"}} }, 
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
		domainName = inFrom.split("@")[1];
		Thread.sleep(1000);
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
                this.client.sendEmail(inFrom, null, inReturnPath, inReplyTo, inToAddr, inCcAddr, inBccAddr, inSubject,
                        inBody, inPriority, inCharset, attachFile);
		Assert.assertNotNull(sResponse.getMessageId());
		Thread.sleep(1000);
	}

	@Test
	public void testRequest() throws URISyntaxException, InterruptedException {
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

		List<Attachment> attachList=new ArrayList<Attachment>();
		for(String[] attach:inAttachData){
			attachList.add(new Attachment(attach[0],Base64.encodeBase64String(attach[1].getBytes(Charset.forName("UTF-8")))));
		}
		mail.setAttachments(attachList);
		
		SendEmailRequest sRequest = new SendEmailRequest();
		sRequest.setMail(mail);

		SendEmailResponse sResponse = this.client.sendEmail(sRequest);
		Thread.sleep(1000);
		Assert.assertNotNull(sResponse.getMessageId());
	}
}
