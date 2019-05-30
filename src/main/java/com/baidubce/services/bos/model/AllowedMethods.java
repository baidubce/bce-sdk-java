/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos.model;

/**
 * For Bucket CORS(Cross-Origin Resource Sharing).
 * Specifying a method for allowing cross-domain requests.
 * Type: Enumeration,with values GET, PUT, DELETE,POST,HEAD.
 */
public enum AllowedMethods {
    GET,
    PUT,
    DELETE,
    POST,
    HEAD;
}
