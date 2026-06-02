package com.baidubce.services.eiptp.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * The request for getting eipTp price.
 */
@Setter
@Getter
public class GetEipTpPriceRequest extends AbstractBceRequest {
    /**
     * If the clientToken is not specified by the user, a random String generated 
     * by default algorithm will be used.
     */
    @JsonIgnore
    private String clientToken;
    
    /**
     * The reservation length of the eiptp including 1, 6 and 12 months.
     */
    private Integer reservationLength;
    
    /**
     * The capacity of the eiptp.
     * When reservationLength = 1 => capacity: {"10G"/"50G"/"100G"/"500G"/"1T"/"5T"/"10T"/"50T"}
     * When reservationLength = 6 => capacity: {"60G"/"300G"/"600G"/"3T"/"6T"/"30T"/"60T"/"300T"}
     * When reservationLength = 12 => capacity: {"1T"/"10T"/"50T"/"100T"/"500T"/"1P"}
     */
    private String capacity;
    
    /**
     * The deduct policy of the eiptp including "FullTimeDurationPackage" and "TimeDurationPackage".
     * The default deduct policy is "FullTimeDurationPackage", the optional parameter.
     */
    private String deductPolicy;
    
    /**
     * The eiptp package type.
     * The default package type is "WebOutBytes", the optional parameter.
     */
    private String packageType;
    
    @Override
    public GetEipTpPriceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}