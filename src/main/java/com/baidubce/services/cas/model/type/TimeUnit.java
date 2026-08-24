/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.type;

/**
 * Time unit of the purchase duration.
 */
public enum TimeUnit {
    @Deprecated
    DAYS {
        @Override
        public double toDays(int d) {
            return d;
        }

        @Override
        public double toMonths(int d) {
            return (double) d / 31;
        }

        @Override
        public double toYears(int d) {
            return (double) d / 366;
        }
    },
    @Deprecated
    MONTHS {
        @Override
        public double toDays(int d) {
            return d * 31;
        }

        @Override
        public double toMonths(int d) {
            return d;
        }

        @Override
        public double toYears(int d) {
            return (double) d / 12;
        }
    },
    @Deprecated
    YEARS {
        @Override
        public double toDays(int d) {
            return 366 * d;
        }

        @Override
        public double toMonths(int d) {
            return 12 * d;
        }

        @Override
        public double toYears(int d) {
            return d;
        }
    },
    DAY {
        @Override
        public double toDays(int d) {
            return d;
        }

        @Override
        public double toMonths(int d) {
            return (double) d / 31;
        }

        @Override
        public double toYears(int d) {
            return (double) d / 366;
        }
    },
    MONTH {
        @Override
        public double toDays(int d) {
            return (double) d * 30.5;
        }

        @Override
        public double toMonths(int d) {
            return d;
        }

        @Override
        public double toYears(int d) {
            return (double) d / 12;
        }
    },
    YEAR {
        @Override
        public double toDays(int d) {
            return 366 * d;
        }

        @Override
        public double toMonths(int d) {
            return 12 * d;
        }

        @Override
        public double toYears(int d) {
            return d;
        }
    };

    public abstract double toDays(int d);

    public abstract double toMonths(int d);

    public abstract double toYears(int d);
}
