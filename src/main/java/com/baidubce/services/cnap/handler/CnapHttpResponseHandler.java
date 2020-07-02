package com.baidubce.services.cnap.handler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.cnap.model.access.AccessModel;
import com.baidubce.services.cnap.model.access.CreateAccessResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The cnap http response handler.
 */
public class CnapHttpResponseHandler implements HttpResponseHandler {

    public static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (!response.getClass().equals(CreateAccessResponse.class)) {
            return false;
        }
        InputStream content = httpResponse.getContent();
        if (content == null) {
            return false;
        }
        if (response.getMetadata().getContentLength() > 0
                || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
            String value = EntityUtils.toString(httpResponse.getHttpResponse().getEntity());
            JavaType javaType = getCollectionType(ArrayList.class, AccessModel.class);
            List<AccessModel> list =  (List<AccessModel>) mapper.readValue(value, javaType);
            CreateAccessResponse createAccessResponse = (CreateAccessResponse) response;
            createAccessResponse.setResult(list);
            return true;
        }
        content.close();
        return false;
    }


    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
