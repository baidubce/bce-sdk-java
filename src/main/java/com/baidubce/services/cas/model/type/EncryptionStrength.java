/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

import static com.baidubce.services.cas.model.type.EncryptionType.ECDSA;
import static com.baidubce.services.cas.model.type.EncryptionType.RSA;
import static com.baidubce.services.cas.model.type.EncryptionType.SM2;

import lombok.Getter;

/**
 * Key strength (RSA key size or ECC curve) of the certificate.
 */
@Getter
public enum EncryptionStrength {
    ECDSA_PRIME256V1("prime256v1", ECDSA, 256),
    ECDSA_PRIME384V1("secp384r1", ECDSA, 384),
    ECDSA_PRIME224V1("secp224v1", ECDSA, 224),
    ECDSA_PRIME521V1("secp521r1", ECDSA, 521),
    RSA_2048("2048", RSA, 2048),
    RSA_3072("3072", RSA, 3072),
    RSA_4096("4096", RSA, 4096),
    RSA_8192("8192", RSA, 8192),
    SM2_SM2P256V1("sm2p256v1", SM2, 256);

    private final String value;
    private final EncryptionType algorithm;
    private final Integer intValue;

    EncryptionStrength(String value, EncryptionType algorithm, Integer intValue) {
        this.value = value;
        this.algorithm = algorithm;
        this.intValue = intValue;
    }
}
