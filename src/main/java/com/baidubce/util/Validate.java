/*
 * Copyright 2015 Baidu, Inc.
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

package com.baidubce.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;

import com.baidubce.BceClientException;
import org.apache.commons.lang3.StringUtils;

public class Validate {
    public static void checkStringNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static void checkNotNull(Object value, String errorMessage) {
        if (value == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    public static void checkIsTrue(boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkPattern(String value, String pattern, String errorMessage) {
        if (!Pattern.matches(pattern, value)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkValidValue(String value, Class<?> enumCls, String errorMessage) {
        try {
            Object[] objects = enumCls.getEnumConstants();
            Method name = enumCls.getMethod("name");
            for (Object obj : objects) {
                if (name.invoke(obj).equals(value)) {
                    return;
                }
            }
            throw new IllegalArgumentException(errorMessage);
        } catch (NoSuchMethodException e) {
            throw new BceClientException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new BceClientException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new BceClientException(e.getMessage());
        }
    }

    public static void checkMultyParamsNotBothEmpty(List<String> values, String errorMessage) {
        for (String value : values) {
            if (StringUtils.isNotBlank(value)) {
                return;
            }
        }
        throw new IllegalArgumentException(errorMessage);

    }

}
