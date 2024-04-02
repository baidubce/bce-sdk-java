package com.baidubce.services.bcm.handler;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.EmptyResponse;
import com.baidubce.services.bcm.model.ListResponse;
import com.baidubce.services.bcm.model.MapListResponse;
import com.baidubce.services.bcm.model.application.ApplicationMonitorResponse;
import com.baidubce.services.bcm.model.custom.CustomMonitorResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * HTTP body json response handler for Baidu BCM responses.
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:42
 */
public class BcmJsonResponseHandler extends BceJsonResponseHandler {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        // 在定义监控中，成功的返回没有body，不需要反序列化，否则报错
        if (response instanceof CustomMonitorResponse) {
            CustomMonitorResponse customMonitorResponse = (CustomMonitorResponse) response;
            customMonitorResponse.setCode("OK");
            customMonitorResponse.setMessage("");
            if (null != response.getMetadata()) {
                customMonitorResponse.setRequestId(response.getMetadata().getBceRequestId());
            }

            // 如果为空，可以支持重复读，从而不会由于空流导致报错
            ByteArrayOutputStream bos = toByteArrayOutputStream(httpResponse.getContent());
            if (bos.size() > 0) {
                OBJECT_MAPPER.readerForUpdating(customMonitorResponse).readValue(bos.toByteArray());
            }
            return true;
        }
        // 某些response返回序列化需要特殊处理
        if (response instanceof EmptyResponse || response instanceof ListResponse
                || response instanceof  MapListResponse) {
            InputStream content = httpResponse.getContent();
            if (content != null) {
                if (response.getMetadata().getContentLength() > 0
                        || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
                    if (response instanceof ListResponse) {
                        JsonUtils.load(content, ((ListResponse<?>) response).getResult());
                    } else if (response instanceof MapListResponse) {
                        JsonUtils.load(content, ((MapListResponse) response).getResult());
                    }
                }
                content.close();
            }
            return true;
        }

        if (response instanceof ApplicationMonitorResponse) {
            ApplicationMonitorResponse applicationMonitorResponse = (ApplicationMonitorResponse) response;
            applicationMonitorResponse.setCode("OK");
            applicationMonitorResponse.setMessage("");
            if (null != response.getMetadata()) {
                applicationMonitorResponse.setRequestId(response.getMetadata().getBceRequestId());
            }
            ByteArrayOutputStream bos = toByteArrayOutputStream(httpResponse.getContent());
            if (bos.size() > 0) {
                OBJECT_MAPPER.readerForUpdating(applicationMonitorResponse).readValue(bos.toByteArray());
            }
            return true;
        }

        return super.handle(httpResponse, response);
    }

    /**
     * 转换为字节数组流
     */
    private static ByteArrayOutputStream toByteArrayOutputStream(InputStream input) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) > -1) {
            bos.write(buffer, 0, len);
        }
        bos.flush();
        input.close();
        return bos;
    }
}
