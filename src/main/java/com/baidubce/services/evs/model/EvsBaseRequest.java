package com.baidubce.services.evs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.io.Serializable;

public class EvsBaseRequest extends AbstractBceRequest implements Serializable {

    private static final long serialVersionUID = -7831667901482061296L;

    public EvsBaseRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

}