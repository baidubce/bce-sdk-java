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
import com.baidubce.services.sms.model.StatReceiverRequest;

@RunWith(Parameterized.class)
public class SmsClientStatReceiverAbnorQaTest {
	private SmsClient client;

	@Parameter(0)
	public String inPhoneNumber;
	@Parameter(1)
	public String expException;
	@Parameter(2)
	public String expMessage;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] {
						{ null, "java.lang.IllegalArgumentException",
								"object phoneNumber should not be null or empty" },
						{ "", "java.lang.IllegalArgumentException",
								"object phoneNumber should not be null or empty" },
						{ " ", "java.lang.IllegalArgumentException",
								"object phoneNumber should not be null or empty" },
						{ "1", "com.baidubce.BceServiceException",
								"parameter validator error: phoneNumber=1 is not a valid phone number" },
						{
								"1381193940a",
								"com.baidubce.BceServiceException",
								"parameter validator error: phoneNumber=1381193940a is not a valid phone number" },
						{
								"11111111111,13811939406",
								"com.baidubce.BceServiceException",
								"parameter validator error: phoneNumber=11111111111,13811939406 is not a valid phone number" }, });
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

		client.statReceiver(new StatReceiverRequest()
				.withPhoneNumber(inPhoneNumber));
	}

}
