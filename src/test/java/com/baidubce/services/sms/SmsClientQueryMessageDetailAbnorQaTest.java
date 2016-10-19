package com.baidubce.services.sms;

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

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SmsConf;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.QueryMessageDetailRequest;
import com.baidubce.services.sms.model.QueryMessageDetailResponse;

@RunWith(Parameterized.class)
public class SmsClientQueryMessageDetailAbnorQaTest {
	private SmsClient client;

	@Parameter(0)
	public String inMessageId;
	@Parameter(1)
	public String expException;
	@Parameter(2)
	public String expMessage;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ null, "java.lang.IllegalArgumentException",
						"object messageId should not be null or empty" },
				{ "", "java.lang.IllegalArgumentException",
						"object messageId should not be null or empty" },
				{ " ", "java.lang.IllegalArgumentException",
						"object messageId should not be null or empty" },
				{ "notExistIdxxxx", "com.baidubce.BceServiceException",
						"no record found" }, });

	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws InterruptedException {
		this.client = new SmsClient(
				new SmsClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SmsConf.SMS_AK,
										SmsConf.SMS_SK)).withEndpoint(
								SmsConf.SMS_ENDPOINT));
		Thread.sleep(1000);
	}

	@After
	public void tearDown() {
		this.client.shutdown();
	}

	@Test
	public void test() throws ClassNotFoundException {
		thrown.expect((Class<? extends Throwable>) Class.forName(expException));
		thrown.expectMessage(expMessage);

		// 查询短信详情
		QueryMessageDetailResponse qResponse = this.client
				.queryMessageDetail(new QueryMessageDetailRequest()
						.withMessageId(inMessageId));
	}
}
