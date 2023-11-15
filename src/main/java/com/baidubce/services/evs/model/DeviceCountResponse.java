package com.baidubce.services.evs.model;

import java.io.Serializable;

public class DeviceCountResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 2776564010045866821L;

    private Count data;

    public Count getData() {
        return data;
    }

    public void setData(Count data) {
        this.data = data;
    }

    public static class Count implements Serializable {

        private static final long serialVersionUID = 5571959442221427504L;

        private Long count;

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "Count{" +
                    "count=" + count +
                    '}';
        }
    }

}
