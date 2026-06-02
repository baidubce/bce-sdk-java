package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GetPrepaidPackageResponse extends SmsResponse {
    /**
     * Result list
     */
    private List<PrepaidPackage> prepaidPackages;

    /**
     * Total count of prepaid packages
     */
    int totalCount;

    public List<PrepaidPackage> getPrepaidPackages() {
        return prepaidPackages;
    }

    public void setPrepaidPackages(List<PrepaidPackage> prepaidPackages) {
        this.prepaidPackages = prepaidPackages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public static class PrepaidPackage {
        /**
         * Package ID of prepaid package
         */
        private String packageId;

        /**
         * Name of prepaid package
         */
        private String name;

        /**
         * The countryType indicates the countries or regions in which the prepaid package can be used.<br/>
         * The value of countryType could be "domestic", "international", "global".<br/>
         * "domestic" means the prepaid package used in Mainland China.<br/>
         * "international" means the prepaid package used outside Mainland China.<br/>
         * "global" means the prepaid package used in all countries.
         */
        private String countryType;

        /**
         * The capacity of prepaid package
         */
        private BigDecimal capacity;

        /**
         * The remaining capacity of prepaid package
         */
        private BigDecimal remainingCapacity;

        /**
         * The Status of prepaid package.<br/>
         * The value of packageStatus could be "RUNNING", "EXPIRED", "USED_UP", "DESTROYED".<br/>
         * "RUNNING" means the prepaid package is running.<br/>
         * "EXPIRED" means the prepaid package is expired.<br/>
         * "USED_UP" means the prepaid package is used up.<br/>
         * "DESTROYED" means the prepaid package is destroyed.
         */
        private String packageStatus;

        /**
         * The purchase date of prepaid package
         */
        private Date purchaseDate;

        /**
         * The expiry date of prepaid package
         */
        private Date expiryDate;

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountryType() {
            return countryType;
        }

        public void setCountryType(String countryType) {
            this.countryType = countryType;
        }

        public BigDecimal getCapacity() {
            return capacity;
        }

        public void setCapacity(BigDecimal capacity) {
            this.capacity = capacity;
        }

        public BigDecimal getRemainingCapacity() {
            return remainingCapacity;
        }

        public void setRemainingCapacity(BigDecimal remainingCapacity) {
            this.remainingCapacity = remainingCapacity;
        }

        public String getPackageStatus() {
            return packageStatus;
        }

        public void setPackageStatus(String packageStatus) {
            this.packageStatus = packageStatus;
        }

        public Date getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(Date purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
        }
    }
}
