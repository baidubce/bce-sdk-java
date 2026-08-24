/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

/**
 * Digest algorithm of the certificate.
 */
public enum HashAlgo {
    SHA1,
    SHA256,
    SHA384,
    SHA512,
    // SM3, Chinese national cryptographic digest
    SM3,
    MD5
}
