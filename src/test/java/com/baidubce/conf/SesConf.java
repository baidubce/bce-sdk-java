package com.baidubce.conf;

public class SesConf {
	//nmg09
	public static final String SES_ENDPOINT = "http://nmg02-bce-test9.nmg02.baidu.com:8886";
	public static final String SES_AK = "";
	public static final String SES_SK = "";
	public static final String SES_REDIS_HOST="10.107.37.45";
	public static final int SES_REDIS_PORT=8885;
	public static final String SES_ACCOUNT_ID="6060ca9dce5c48dd98e53ccca6c4eb58";
	public static final String MAIL_CERT_INFO_KEY="ses_bes:account:"+SES_ACCOUNT_ID+":mailAddrCert";
	public static final String MAIL_CERT_STATUS_KEY="ses_bes:account:"+SES_ACCOUNT_ID+":mailCertStatus";
	public static final String DOMAIN_CERT_INFO_KEY="ses_bes:account:"+SES_ACCOUNT_ID+":mailDomainCert";
	public static final String DKIM_CERT_INFO_KEY="ses_bes:account:"+SES_ACCOUNT_ID+":mailDkimCert";
	public static final String DKIM_ENABLE_KEY="ses_bes:account:"+SES_ACCOUNT_ID+":dkimEnable";
	public static final String MAIL_QUOTA_KEY="ses_qos:"+SES_ACCOUNT_ID+":mailQuota:production";
	public static final String MAIL_RATE_LIMIT_KEY="ses_qos:"+SES_ACCOUNT_ID+":rateLimit:production";
	public static final String MAIL_SEND_COUNT_KEY="ses_qos:"+SES_ACCOUNT_ID+":mailSendCounter:production";
	public static final String RECP_BLACKLIST_KEY="ses_qos:"+SES_ACCOUNT_ID+":recipientBlacklist";
//	public static final String SES_REDIS = "./redis-cli -h 10.58.140.31 -p 5001 -n 0";
}
