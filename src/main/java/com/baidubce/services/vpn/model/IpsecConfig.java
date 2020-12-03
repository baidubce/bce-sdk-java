package com.baidubce.services.vpn.model;

import lombok.Data;

/**
 * IPSec Configuration example
 */
@Data
public class IpsecConfig {
    /** Encryption algorithm, value range ：aes/aes192/aes256/3des*/
    private String ipsecEncAlg;
    /** Authentication algorithm, value range ：sha1/md5*/
    private String ipsecAuthAlg;
    /**DH Grouping, value range ：group2/group5/group14/group24*/
    private String ipsecPfs;
    /**SA Life cycle, value range ：180-86400*/
    private String ipsecLifetime;
}
