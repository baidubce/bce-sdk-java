/*
 *  Copyright 2014-2019 Baidu, Inc.  All Rights Reserved.
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
package com.baidubce.http;

/**
 * Common BOS HTTP header values used throughout the BCE BOS Java client.
 */
public interface Headers {

    /*
     * Standard HTTP Headers
     */

    public static final String AUTHORIZATION = "Authorization";

    public static final String CACHE_CONTROL = "Cache-Control";

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String CONTENT_ENCODING = "Content-Encoding";

    public static final String CONTENT_LENGTH = "Content-Length";

    public static final String CONTENT_MD5 = "Content-MD5";

    public static final String CONTENT_RANGE = "Content-Range";

    public static final String CONTENT_TYPE = "Content-Type";

    public static final String DATE = "Date";

    public static final String ETAG = "ETag";

    public static final String EXPIRES = "Expires";

    public static final String HOST = "Host";

    public static final String LAST_MODIFIED = "Last-Modified";

    public static final String LOCATION = "Location";

    public static final String RANGE = "Range";

    public static final String SERVER = "Server";

    public static final String TRANSFER_ENCODING = "Transfer-Encoding";

    public static final String USER_AGENT = "User-Agent";


    /*
     * BCE Common HTTP Headers
     */

    public static final String BCE_ACL = "x-bce-acl";

    public static final String BCE_ACL_GRANT_READ = "x-bce-grant-read";

    public static final String BCE_ACL_GRANT_FULL_CONTROL = "x-bce-grant-full-control";

    public static final String BCE_CONTENT_SHA256 = "x-bce-content-sha256";

    public static final String BCE_COPY_METADATA_DIRECTIVE = "x-bce-metadata-directive";

    public static final String BCE_COPY_SOURCE_IF_MATCH = "x-bce-copy-source-if-match";

    public static final String BCE_DATE = "x-bce-date";

    public static final String BCE_PREFIX = "x-bce-";

    public static final String BCE_REQUEST_ID = "x-bce-request-id";

    public static final String BCE_SECURITY_TOKEN = "x-bce-security-token";

    public static final String BCE_USER_METADATA_PREFIX = "x-bce-meta-";

    public static final String BCE_CONTENT_CRC32 = "x-bce-content-crc32";

    /*
     * BOS HTTP Headers
     */

    public static final String BCE_COPY_SOURCE = "x-bce-copy-source";

    public static final String BCE_COPY_SOURCE_RANGE = "x-bce-copy-source-range";

    public static final String BCE_COPY_SOURCE_IF_MODIFIED_SINCE = "x-bce-copy-source-if-modified-since";

    public static final String BCE_COPY_SOURCE_IF_NONE_MATCH = "x-bce-copy-source-if-none-match";

    public static final String BCE_COPY_SOURCE_IF_UNMODIFIED_SINCE = "x-bce-copy-source-if-unmodified-since";

    public static final String BCE_FETCH_SOURCE = "x-bce-fetch-source";

    public static final String BCE_FETCH_MODE = "x-bce-fetch-mode";

    public static final String BCE_DEBUG_ID = "x-bce-debug-id";

    public static final String BCE_NEXT_APPEND_OFFSET = "x-bce-next-append-offset";

    public static final String BCE_OBJECT_TYPE = "x-bce-object-type";

    public static final String BCE_STORAGE_CLASS = "x-bce-storage-class";

    public static final String BCE_RESTORE_TIER = "x-bce-restore-tier";

    public static final String BCE_RESTORE_DAYS = "x-bce-restore-days";

    public static final String BCE_SYMLINK_TARGET = "x-bce-symlink-target";

    public static final String BCE_FORBID_OVERWRITE = "x-bce-forbid-overwrite";

    public static final String BCE_RESTORE = "x-bce-restore";

    public static final String BOS_TRAFFIC_LIMIT = "x-bce-traffic-limit";

    public static final String BOS_PROCESS = "x-bce-process";

    public static final String BCE_TAG_LIST = "x-bce-tag-list";

    public static final String BCE_REFERER = "referer";

    public static final String BCE_FETCH_USER_AGENT = "x-bce-fetch-user-agent";

    public static final String BCE_FETCH_CALLBACK_ADDRESS = "x-bce-callback-address";

    public static final  String BCE_BUCKET_TYPE = "x-bce-bucket-type";

    public static final  String BCE_DELETE_RECURSIVE = "x-bce-delete-recursive";

    public static final  String BCE_DELETE_TOKEN = "x-bce-delete-token";

    public static final String BCE_CONTENT_CRC32C = "x-bce-content-crc32c";

    public static final String BCE_CONTENT_CRC32C_FLAG = "x-bce-content-crc32c-flag";

    public static final String BCE_CONSISTENCY_VIEW = "x-bce-consistency-view";

    public static final String BCE_LIST_WITH_EXT_META = "x-bce-list-with-ext-meta";

    public static final String BCE_VERSIONING = "x-bce-versioning";

    public static final String BCE_VERSION_ID = "x-bce-version-id";

    /*
     * CFC HTTP Headers
     */

    public static final String BCE_LOG_RESULT = "X-Bce-Log-Result";

}
