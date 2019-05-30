/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.tablestorage.model.transform;

import com.baidubce.BceClientException;
import com.baidubce.services.tablestorage.TableStorageConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Provide the static function to read the content of input stream.
 */
public class Unmarshallers {
    public static String readStreamContents(final InputStream in) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in,
                    TableStorageConstants.DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[8192];
            int read = -1;
            while ((read = br.read(buf)) != -1) {
                sb.append(buf, 0, read);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            throw new BceClientException("Unable to read error responseContent: " + e, e);
        }
    }
}