/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

/**
 * Encryption algorithm of the certificate key pair.
 */
public enum EncryptionType {
    RSA,
    // elliptic curve
    ECDSA,
    // SM2, Chinese national cryptographic algorithm
    SM2
}
