package com.baidubce.services.scs;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Http response handler for test,print the original json response
 */
public class ScsResponseTestHandler extends BceJsonResponseHandler {

    protected static final Logger logger = LoggerFactory.getLogger(BaseScsClientTest.class);

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();
        if (content != null) {
            if (response.getMetadata().getContentLength() > 0
                    || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
                String string = StreamUtils.copyToString(content, Charset.forName("utf-8"));
                ObjectMapper mapper = new ObjectMapper();
                Object json = mapper.readValue(string, Object.class);
                logger.info("Original response is :\n{}",
                        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
                JsonUtils.load(new ByteArrayInputStream(string.getBytes()), response);
            }
        }
        return true;
    }
}
