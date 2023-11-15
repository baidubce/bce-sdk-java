package com.baidubce.services.evs.model;

import java.io.Serializable;

/**
 * Use of device，Space not need
 */
public class SameAsSpace implements Serializable {

    private static final long serialVersionUID = 5832450552028344214L;

    private boolean sameAsSpace;

    public boolean isSameAsSpace() {
        return sameAsSpace;
    }

    public void setSameAsSpace(boolean sameAsSpace) {
        this.sameAsSpace = sameAsSpace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SameAsSpace that = (SameAsSpace) o;

        return sameAsSpace == that.sameAsSpace;
    }

    @Override
    public int hashCode() {
        return (sameAsSpace ? 1 : 0);
    }

    @Override
    public String toString() {
        return "SameAsSpace{" +
                "sameAsSpace=" + sameAsSpace +
                '}';
    }
}
