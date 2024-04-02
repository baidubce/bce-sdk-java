package com.baidubce.services.sms;

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

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.SmsConf;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.QueryQuotaResponse;
import com.baidubce.services.sms.model.UpdateQuotaRequest;

@RunWith(Parameterized.class)
public class SmsClientQueryQuotaNorQaTest {

	private SmsClient client;
	private SmsInternalClient internalClient;

	@Parameter(0)
	public int inMaxSend;
	@Parameter(1)
	public int inMaxReceive;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 0, 0 }, { 1, 2 },
				{1111111111,222222222},

		});
	}

	@Before
	public void setUp() throws InterruptedException {
		this.client = new SmsClient(
				new SmsClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SmsConf.SMS_AK,
										SmsConf.SMS_SK)).withEndpoint(
								SmsConf.SMS_ENDPOINT));
		this.internalClient = new SmsInternalClient(
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
		this.internalClient.shutdown();
	}

	@Test
	public void test() {
		int beforeMaxSend, beforeMaxReceive;
		int afterMaxSend, afterMaxReceive;
		UpdateQuotaRequest uRequest = new UpdateQuotaRequest();
		QueryQuotaResponse qResponse = null;

		//获取当前配额值，便于测试后恢复环境
		qResponse = this.client.queryQuota(new SmsRequest());
		beforeMaxSend = qResponse.getMaxSendPerDay();
		beforeMaxReceive = qResponse.getMaxReceivePerPhoneNumberDay();

		//将配额更新为预期值
		uRequest.setMaxSendPerDay(inMaxSend);
		uRequest.setMaxReceivePerPhoneNumberDay(inMaxReceive);
		this.internalClient.updateQuota(uRequest);
		//获取更新后的配额值
		qResponse = this.client.queryQuota(new SmsRequest());
		afterMaxSend = qResponse.getMaxSendPerDay();
		afterMaxReceive = qResponse.getMaxReceivePerPhoneNumberDay();

		//将配额恢复为测试开始前的值
		uRequest.setMaxSendPerDay(beforeMaxSend);
		uRequest.setMaxReceivePerPhoneNumberDay(beforeMaxReceive);
		this.internalClient.updateQuota(uRequest);
		
		//比较预期值与更新后实际获取到的配额值
		Assert.assertEquals(inMaxSend, afterMaxSend);
		Assert.assertEquals(inMaxReceive, afterMaxReceive);
	}
}
