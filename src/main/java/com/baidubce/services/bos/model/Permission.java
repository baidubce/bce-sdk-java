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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Specifies constants defining an access permission,. Only a limited set of permission are available;
 * each one is represented as a value in this enumeration.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum Permission {
    FULL_CONTROL,
    READ,
    WRITE,
    LIST,
    MODIFY,

    // Object
    GetObject,
    GetObjectMeta,
    PutObject,
    DeleteObject,
    RenameObject,
    RestoreObject,
    PutObjectAcl,
    GetObjectAcl,
    PutObjectTagging,
    GetObjectTagging,
    DeleteObjectTagging,
    ListParts,

    // Bucket
    GetBucket,
    PutBucket,
    ListBucket,
    GetBucketAcl,
    PutBucketAcl,
    GetBucketCors,
    PutBucketCors,
    GetBucketStyle,
    PutBucketStyle,
    GetBucketMirroring,
    PutBucketMirroring,
    GetCopyRightProtection,
    PutCopyRightProtection,
    GetObjectVersion,
    DeleteObjectVersion,
    ListObjectVersions,
    GetObjectVersionAcl,
    PutObjectVersionAcl,
    PutBucketVersioning,
    GetBucketVersioning,
    PutBucketLifecycle,
    GetBucketLifecycle,
    PutBucketReplication,
    GetBucketReplication,
    PutBucketEncryption,
    GetBucketEncryption,
    PutBucketStaticWebsite,
    GetBucketStaticWebsite,
    PutBucketLogging,
    GetBucketLogging,
    PutBucketRequestPayment,
    GetBucketRequestPayment,
    PutBucketTagging,
    GetBucketTagging,
    PutNotification,
    GetNotification,
    PutBucketObjectLock,
    GetBucketObjectLock,
    PutBucketInventory,
    GetBucketInventory,
    GetBucketStorageAnalysis,
    PutBucketStorageAnalysis,
    GetBucketStorageClass,
    PutBucketStorageClass,
    PutBucketTrash,
    GetBucketTrash,
    PutBucketQuota,
    GetBucketQuota;
}

