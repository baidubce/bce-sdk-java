package com.baidubce.services.evs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AiConfigRequest implements Serializable {

    private static final long serialVersionUID = 6798206332963466508L;

    private boolean enabled;

    private List<Configuration> configuration;

    private FtpConfig ftpConfig;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Configuration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<Configuration> configuration) {
        this.configuration = configuration;
    }

    public FtpConfig getFtpConfig() {
        return ftpConfig;
    }

    public void setFtpConfig(FtpConfig ftpConfig) {
        this.ftpConfig = ftpConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AiConfigRequest that = (AiConfigRequest) o;

        if (enabled != that.enabled) {
            return false;
        }
        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null) {
            return false;
        }
        return ftpConfig != null ? ftpConfig.equals(that.ftpConfig) : that.ftpConfig == null;
    }

    @Override
    public int hashCode() {
        int result = (enabled ? 1 : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (ftpConfig != null ? ftpConfig.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AiConfigRequest{" +
                "enabled=" + enabled +
                ", configuration=" + configuration +
                ", ftpConfig=" + ftpConfig +
                '}';
    }

    public static class Configuration implements Serializable {

        private static final long serialVersionUID = -4968362421982646857L;

        /**
         * Support：
         * FACE、BODY、ELECTRIC、CAR_ATTRIBUTE、CAR_MODEL、CAR_PLATE，STATIC_HUMAN_TRAFFIC、STATIC_CAR_TRAFFIC、
         * QUALITY_BRIGHT、QUALITY_COLOR、QUALITY_COVER、QUALITY_BLUR、QUALITY_NOISE
         */
        private String type;

        private Long interval;

        /**
         * Support：OPTIMAL、FASTEST、 REGULAR
         */
        private String strategy;

        private List<EffectiveTime> effectiveTimes;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Long getInterval() {
            return interval;
        }

        public void setInterval(Long interval) {
            this.interval = interval;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public List<EffectiveTime> getEffectiveTimes() {
            return effectiveTimes;
        }

        public void setEffectiveTimes(List<EffectiveTime> effectiveTimes) {
            this.effectiveTimes = effectiveTimes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Configuration that = (Configuration) o;

            if (type != null ? !type.equals(that.type) : that.type != null) {
                return false;
            }
            if (interval != null ? !interval.equals(that.interval) : that.interval != null) {
                return false;
            }
            if (strategy != null ? !strategy.equals(that.strategy) : that.strategy != null) {
                return false;
            }
            return effectiveTimes != null ? effectiveTimes.equals(that.effectiveTimes) : that.effectiveTimes == null;
        }

        @Override
        public int hashCode() {
            int result = type != null ? type.hashCode() : 0;
            result = 31 * result + (interval != null ? interval.hashCode() : 0);
            result = 31 * result + (strategy != null ? strategy.hashCode() : 0);
            result = 31 * result + (effectiveTimes != null ? effectiveTimes.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Configuration{" +
                    "type='" + type + '\'' +
                    ", interval=" + interval +
                    ", strategy='" + strategy + '\'' +
                    ", effectiveTimes=" + effectiveTimes +
                    '}';
        }
    }

    public static class EffectiveTime implements Serializable {

        private static final long serialVersionUID = -6956309119562350698L;

        private Date effectiveTimeStart;

        private Date effectiveTimeEnd;

        public Date getEffectiveTimeStart() {
            return effectiveTimeStart;
        }

        public void setEffectiveTimeStart(Date effectiveTimeStart) {
            this.effectiveTimeStart = effectiveTimeStart;
        }

        public Date getEffectiveTimeEnd() {
            return effectiveTimeEnd;
        }

        public void setEffectiveTimeEnd(Date effectiveTimeEnd) {
            this.effectiveTimeEnd = effectiveTimeEnd;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            EffectiveTime that = (EffectiveTime) o;

            if (effectiveTimeStart != null ?
                    !effectiveTimeStart.equals(that.effectiveTimeStart) :
                    that.effectiveTimeStart != null) {
                return false;
            }
            return effectiveTimeEnd != null ?
                    effectiveTimeEnd.equals(that.effectiveTimeEnd) :
                    that.effectiveTimeEnd == null;
        }

        @Override
        public int hashCode() {
            int result = effectiveTimeStart != null ? effectiveTimeStart.hashCode() : 0;
            result = 31 * result + (effectiveTimeEnd != null ? effectiveTimeEnd.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "EffectiveTime{" +
                    "effectiveTimeStart=" + effectiveTimeStart +
                    ", effectiveTimeEnd=" + effectiveTimeEnd +
                    '}';
        }
    }

    public static class FtpConfig implements Serializable {

        private static final long serialVersionUID = 8945714201933343920L;

        private boolean enabled;

        private String password;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            FtpConfig ftpConfig = (FtpConfig) o;

            if (enabled != ftpConfig.enabled) {
                return false;
            }
            return password != null ? password.equals(ftpConfig.password) : ftpConfig.password == null;
        }

        @Override
        public int hashCode() {
            int result = (enabled ? 1 : 0);
            result = 31 * result + (password != null ? password.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "FtpConfig{" +
                    "enabled=" + enabled +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}