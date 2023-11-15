package com.baidubce.services.evs.model;

public class GbPresetAddRequest extends EvsBaseRequest {

    private static final long serialVersionUID = 1958582712147318940L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GbPresetAddRequest that = (GbPresetAddRequest) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GbPresetAddRequest{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}