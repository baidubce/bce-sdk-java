package com.baidubce.services.ses;

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
import com.baidubce.conf.SesConf;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.SesRequest;
import com.baidubce.services.ses.model.GetFeedbackResponse;
import com.baidubce.services.ses.model.SetFeedbackRequest;

@RunWith(Parameterized.class)
public class SesClientSetGetFeedbackNorQaTest {
	private SesClient client;

	@Parameter(0)
	public int tag;
	@Parameter(1)
	public int inType;
	@Parameter(2)
	public boolean inEnabled;
	@Parameter(3)
	public String inEmailAddr;

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ 0, 1, false, "wanglinqing01@baidu.com" },
				{ 1, 2, true, "wanglinqing01@baidu.com" },
				{ 2, 3, true, "wanglinqing01@baidu.com" },
				// 不传入任何参数，则设置为默认值
				{ 3, 1, false, "wanglinqing01@baidu.com" }, });
	}

	@Before
	public void setUp() {
		this.client = new SesClient(
				new SesClientConfiguration()
						.withCredentials(
								new DefaultBceCredentials(SesConf.SES_AK,
										SesConf.SES_SK)).withEndpoint(
								SesConf.SES_ENDPOINT));
	}

	@After
	public void tearDown() {
		this.client.shutdown();
	}

	@Test
	public void test() throws InterruptedException {

		SetFeedbackRequest sRequest = new SetFeedbackRequest();
		switch (tag) {
		case 3:
			break;
		default: {
			sRequest.setType(inType);
			sRequest.setEnabled(inEnabled);
			sRequest.setEmail(inEmailAddr);
		}
		}
		this.client.setFeedback(sRequest);
		Thread.sleep(1000);
		GetFeedbackResponse gResponse = this.client.getFeedback();
		Thread.sleep(1000);

		Assert.assertEquals(inType, gResponse.getType().intValue());
		Assert.assertEquals(inEnabled, gResponse.isEnabled());
		Assert.assertEquals(inEmailAddr, gResponse.getEmail());
	}

	@Test
	public void testRequest() throws InterruptedException {

		SetFeedbackRequest sRequest = new SetFeedbackRequest();
		switch (tag) {
		case 3:
			break;
		default: {
			sRequest.setType(inType);
			sRequest.setEnabled(inEnabled);
			sRequest.setEmail(inEmailAddr);
		}
		}
		this.client.setFeedback(sRequest);
		Thread.sleep(1000);
		GetFeedbackResponse gResponse = this.client.getFeedback(new SesRequest());
		Thread.sleep(1000);

		Assert.assertEquals(inType, gResponse.getType().intValue());
		Assert.assertEquals(inEnabled, gResponse.isEnabled());
		Assert.assertEquals(inEmailAddr, gResponse.getEmail());
	}

}
