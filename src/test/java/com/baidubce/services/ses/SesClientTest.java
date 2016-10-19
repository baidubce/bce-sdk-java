package com.baidubce.services.ses;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ses.SesClient;
import com.baidubce.services.ses.SesClientConfiguration;
import com.baidubce.services.ses.model.GetFailedReasonResponse;
import com.baidubce.services.ses.model.GetFeedbackResponse;
import com.baidubce.services.ses.model.GetQuotaResponse;
import com.baidubce.services.ses.model.GetVerifiedDomainResponse;
import com.baidubce.services.ses.model.GetVerifiedEmailResponse;
import com.baidubce.services.ses.model.IsInRecipientBlacklistResponse;
import com.baidubce.services.ses.model.ListRecipientBlacklistResponse;
import com.baidubce.services.ses.model.ListVerifiedDomainResponse;
import com.baidubce.services.ses.model.ListVerifiedEmailResponse;
import com.baidubce.services.ses.model.SendEmailRequest;
import com.baidubce.services.ses.model.SendEmailResponse;
import com.baidubce.services.ses.model.SetFeedbackRequest;
import com.baidubce.services.ses.model.SetQuotaRequest;
import com.baidubce.services.ses.model.VerifyDKIMResponse;
import com.baidubce.services.ses.model.VerifyDomainResponse;
import com.baidubce.services.ses.model.SendEmailRequest.Mail;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Message;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Source;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Subject;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination.Addr;
import com.google.common.base.Objects;

@RunWith(Enclosed.class)
public class SesClientTest {

	@Ignore
	public static class Base {
		protected static final String ENDPOINT = "http://nmg02-bce-test9.nmg02.baidu.com:8886";
		protected static final String ACCESSKEY_ID = "46bd9968a6194b4bbdf0341f2286ccce";
		protected static final String SECRET_KEY = "ec7f4e0174254f6f9020f9680fb1da9f";	

		@Rule
		public ExpectedException thrown = ExpectedException.none();

		protected SesClient client;

		@Before
		public void setUp() {
			this.client = new SesClient(new SesClientConfiguration().withCredentials(
					new DefaultBceCredentials(ACCESSKEY_ID, SECRET_KEY))
					.withEndpoint(ENDPOINT));
		}

		@After
		public void tearDown() {
			this.client.shutdown();
		}

		protected void expectBceServiceException(final int statusCode, final String errorCode) {
			this.thrown.expect(new CustomMatcher<Throwable>(
					"BceServiceException [ statusCode=" + statusCode + ", errorCode="
							+ errorCode + "]") {
				@Override
				public boolean matches(Object item) {
					return (item instanceof BceServiceException)
							&& ((BceServiceException) item).getStatusCode() == statusCode
							&& Objects.equal(((BceServiceException) item).getErrorCode(),
									errorCode);
				}
			});
		}
	}

//	public static class VerifiedEmailTest extends Base {
//		@Test
//		public void testVerifyEmail() {
//			this.client.verifyEmail("h.h_07@163.com");
//		}
//
//		@Test
//		public void testListVerifiedEmail() {
//			testVerifyEmail();
//
//			ListVerifiedEmailResponse response = this.client.listVerifiedEmail();
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getDetails().size() >= 1);
//		}
//
//		@Test
//		public void testGetEmailAddress() {
//			testVerifyEmail();
//
//			GetVerifiedEmailResponse response = this.client.getVerifiedEmail("h.h_07@163.com");
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getDetail().getAddress().equals("h.h_07@163.com"));
//		}
//
//		@Test
//		public void testDeleteEmailAddress() {
//			testVerifyEmail();
//
//			this.client.deleteVerifiedEmail("h.h_07@163.com");
//		}
//
//	}
//
//	public static class VerifiedDomainTest extends Base {
//		@Test
//		public void testVerifyDomain() {
//			VerifyDomainResponse response = this.client.verifyDomain("163.com");
//			Assert.assertTrue(response != null && response.getToken() != null
//					&& !response.getToken().isEmpty());
//		}
//
//		@Test
//		public void testListVerifiedDomain() {
//			testVerifyDomain();
//
//			ListVerifiedDomainResponse response = this.client.listVerifiedDomain();
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getDetails().size() >= 1);
//		}
//
//		@Test
//		public void testGetVerifiedDomain() {
//			testVerifyDomain();
//
//			GetVerifiedDomainResponse response = this.client.getVerifiedDomain("163.com");
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getDetail().getDomain().equals("163.com"));
//		}
//
//		@Test
//		public void testDeletedDomain() {
//			testVerifyDomain();
//
//			this.client.deleteVerifiedDomain("163.com");
//		}
//
//		@Test
//		public void testVerifyDKIM() {
//			VerifyDKIMResponse response = this.client.verifyDKIM("163.com");
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getToken().getKeys().size() >= 1);
//		}
//
//		@Test
//		public void testEnableDKIM() {
//			this.client.enableDKIM("163.com");
//		}
//	}
//
//	public static class EmailSendTest extends Base {
//		@Ignore
//		public void testSimpleSendEmail() throws URISyntaxException {
//			// build attachment files
//			File attachment1 = new File(EmailSendTest.class.getResource(
//					"/attachment-test1.txt").toURI());
//			File attachment2 = new File(EmailSendTest.class
//					.getResource("/attachment-test2.md").toURI());
//			File[] attachments = new File[] { attachment1, attachment2 };
//
//			// send email
//			SendEmailResponse response = this.client.sendEmail("zhangzheyuan@qq.com",
//					new String[] { "harlen1017@163.com" }, "test", "<B>TEST</B>", attachments);
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//		}
//
//		@Ignore
//		public void testSendEmail() {
//			// build source object
//			Source source = new Source("zhangzheyuan@qq.com", "zhangzheyuan@qq.com",
//					"zhangzheyuan@qq.com");
//
//			// build destination object
//			List<Addr> toAddr = new ArrayList<Addr>();
//			toAddr.add(new Addr().withAddr("h.h_07@163.com"));
//			Destination destination = new Destination(toAddr, toAddr, toAddr);
//
//			// build subject object
//			Subject subject = new Subject();
//			subject.setData("test");
//			subject.setCharset(1);
//
//			// build message object
//			Message message = new Message();
//			message.setHtml(subject);
//
//			// build mail object
//			Mail mail = new Mail(source, destination, subject, 1, message);
//
//			// build request object
//			SendEmailRequest request = new SendEmailRequest();
//			request.setMail(mail);
//
//			// send email
//			SendEmailResponse response = this.client.sendEmail(request);
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//		}
//	}
//
//	public static class FeedbackTest extends Base {
//		@Test
//		public void testSetFeedback() {
//			SetFeedbackRequest request = new SetFeedbackRequest();
//			request.setType(2);
//			request.setEmail("h.h_07@163.com");
//			request.setEnabled(true);
//
//			this.client.setFeedback(request);
//		}
//
//		@Test
//		public void testGetFeedback() {
//			testSetFeedback();
//
//			GetFeedbackResponse response = this.client.getFeedback();
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getEmail().equals("h.h_07@163.com")
//					&& response.isEnabled() == true);
//		}
//	}
//
//	public static class QuotaTest extends Base {
//		@Test
//		public void testSetQuota() {
//			SetQuotaRequest request = new SetQuotaRequest();
//			request.setMaxPerDay("100000");
//			request.setMaxPerSecond("200");
//
//			this.client.setQuota(request);
//		}
//
//		@Test
//		public void testGetQuota() {
//			testSetQuota();
//
//			GetQuotaResponse response = this.client.getQuota();
//
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//			Assert.assertTrue(response.getMaxPerDay() == 100000
//					&& response.getMaxPerSecond() == 200);
//		}
//	}
//
//	public static class BlacklistTest extends Base {
//		@Test
//		public void testListRecipientBlacklist() {
//			ListRecipientBlacklistResponse response = this.client.listRecipientBlacklist();
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//		}
//
//		@Test
//		public void testIsInRecipientBlacklist() {
//			IsInRecipientBlacklistResponse response = this.client
//					.isInRecipientBlacklist(String.format("%s.com", UUID.randomUUID()
//							.toString()));
//			Assert.assertTrue(!response.isExist());
//		}
//	}
//
//
//	public static class StatTest extends Base {
//		@Test
//		public void testGetFailedReason() {
//			GetFailedReasonResponse response = this.client.getFailedReason();
//			Assert.assertTrue(response.getMetadata().getBceRequestId() != null
//					&& response.getMetadata().getBceRequestId().length() > 0);
//		}
//	}
}
