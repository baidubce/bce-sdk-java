/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.iotdmp.model.handler;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.iotdmp.model.anno.BceListResponse;
import com.baidubce.services.iotdmp.model.anno.BceJsonStringResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BceDmpHandler extends BceJsonResponseHandler {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();

        if (content != null) {
            if (response.getMetadata().getContentLength() > 0
                    || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
                try {
                    if (response.getClass().isAnnotationPresent(BceListResponse.class)) {
                        // 获取类对象
                        Class<? extends AbstractBceResponse> aClass = response.getClass();
                        // 获取放置list的字段
                        Field[] fields = aClass.getDeclaredFields();
                        String field = fields[0].getName();

                        // 获取泛型类型
                        ParameterizedType type = (ParameterizedType) fields[0].getGenericType();
                        Type[] types = type.getActualTypeArguments();
                        Class type1 = (Class) types[0];
                        JavaType javaType = getCollectionType(ArrayList.class, Class.forName(type1.getName()));
                        // 获取响应内容
                        String value = EntityUtils.toString(httpResponse.getHttpResponse().getEntity());
                        List list = OBJECT_MAPPER.readValue(value, javaType);
                        // 执行set方法
                        PropertyDescriptor descriptor = new PropertyDescriptor(field, aClass);
                        Method setMethod = descriptor.getWriteMethod();
                        setMethod.invoke(response, list);
                    }

                    if (response.getClass().isAnnotationPresent(BceJsonStringResponse.class)) {
                        // 获取类对象
                        Class<? extends AbstractBceResponse> aClass = response.getClass();
                        // 获取响应内容
                        String value = EntityUtils.toString(httpResponse.getHttpResponse().getEntity());
                        // 解析JSON数据copy到response中
                        copyFields(JsonUtils.fromJsonString(value, aClass), response);
                    }
                    JsonUtils.load(content, response);
                } catch (Exception e) {}
            }
            content.close();
        }

        return true;
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    private void copyFields(Object source, Object target) throws IllegalAccessException {
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);
            Field targetField = null;
            try {
                targetField = target.getClass().getDeclaredField(field.getName());
            } catch (NoSuchFieldException e) {
                continue;
            }
            targetField.setAccessible(true);
            targetField.set(target, value);
        }
    }
}
