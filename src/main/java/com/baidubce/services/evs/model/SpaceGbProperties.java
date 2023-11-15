package com.baidubce.services.evs.model;

public class SpaceGbProperties extends DeviceGbProperties {

    private static final long serialVersionUID = -6564985872992248270L;

    private boolean inviteImmediate;

    public boolean isInviteImmediate() {
        return inviteImmediate;
    }

    public void setInviteImmediate(boolean inviteImmediate) {
        this.inviteImmediate = inviteImmediate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceGbProperties that = (SpaceGbProperties) o;

        return inviteImmediate == that.inviteImmediate;
    }

    @Override
    public int hashCode() {
        return (inviteImmediate ? 1 : 0);
    }

    @Override
    public String toString() {
        return "GbPropertiesRequest{" +
                "inviteImmediate=" + inviteImmediate +
                '}';
    }
}
