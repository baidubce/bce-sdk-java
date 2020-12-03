package com.baidubce.services.vpn.model;

import lombok.Data;

/**
 * IKE Configuration example
 */
@Data
public class IkeConfig {
    /** Version, value range ：v1/v2*/
    private String ikeVersion;
    /** Negotiation mode, value range ：main/aggressive*/
    private String ikeMode;
    /** Encryption algorithm, value range ：aes/aes192/aes256/3des*/
    private String ikeEncAlg;
    /** Authentication algorithm, value range ：sha1/md5*/
    private String ikeAuthAlg;
    /**DH Grouping, value range ：group2/group5/group14/group24*/
    private String ikePfs;
    /**SA Life cycle, value range ：60-86400*/
    private String ikeLifeTime;
}