/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.util;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Utilities for parsing and formatting dates.
 *
 * <p>
 * Note that this class doesn't use static methods because of the synchronization issues with SimpleDateFormat. This
 * lets synchronization be done on a per-object level, instead of on a per-class level.
 */
public class DateUtils {

    /**
     * ISO 8601 format
     */
    private static final DateTimeFormatter iso8601DateFormat = ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC);

    /**
     * Alternate ISO 8601 format without fractional seconds
     */
    private static final DateTimeFormatter alternateIso8601DateFormat =
            ISODateTimeFormat.dateTimeNoMillis().withZone(DateTimeZone.UTC);

    /**
     * RFC 822 format
     */
    private static final DateTimeFormatter rfc822DateFormat =
            DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'").withLocale(Locale.US).withZone(DateTimeZone.UTC);

    /**
     * This is another ISO 8601 format that's used in clock skew error response
     */
    private static final DateTimeFormatter compressedIso8601DateFormat =
            ISODateTimeFormat.basicDateTimeNoMillis().withZone(DateTimeZone.UTC);

    /**
     * Parses the specified date string as an ISO 8601 date and returns the Date object.
     *
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws IllegalArgumentException If the date string could not be parsed.
     */
    public static Date parseIso8601Date(String dateString) {
        try {
            return DateUtils.iso8601DateFormat.parseDateTime(dateString).toDate();
        } catch (IllegalArgumentException e) {
            // If the first ISO 8601 parser didn't work, try the alternate
            // version which doesn't include fractional seconds
            return DateUtils.alternateIso8601DateFormat.parseDateTime(dateString).toDate();
        }
    }

    /**
     * Formats the specified date as an ISO 8601 string.
     *
     * @param date The date to format.
     * @return The ISO 8601 string representing the specified date.
     */
    public static String formatIso8601Date(Date date) {
        return DateUtils.iso8601DateFormat.print(new DateTime(date));
    }

    /**
     * Parses the specified date string as an ISO 8601 date and returns the Date object.
     *
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws IllegalArgumentException If the date string could not be parsed.
     */
    public static Date parseAlternateIso8601Date(String dateString) {
        return DateUtils.alternateIso8601DateFormat.parseDateTime(dateString).toDate();
    }

    /**
     * Formats the specified date as an ISO 8601 string.
     *
     * @param date The date to format.
     * @return The ISO 8601 string representing the specified date.
     */
    public static String formatAlternateIso8601Date(Date date) {
        return DateUtils.alternateIso8601DateFormat.print(new DateTime(date));
    }

    /**
     * Parses the specified date string as an RFC 822 date and returns the Date object.
     *
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws IllegalArgumentException If the date string could not be parsed.
     */
    public static Date parseRfc822Date(String dateString) {
        return DateUtils.rfc822DateFormat.parseDateTime(dateString).toDate();
    }

    /**
     * Formats the specified date as an RFC 822 string.
     *
     * @param date The date to format.
     * @return The RFC 822 string representing the specified date.
     */
    public static String formatRfc822Date(Date date) {
        return DateUtils.rfc822DateFormat.print(new DateTime(date));
    }

    /**
     * Parses the specified date string as a compressedIso8601DateFormat ("yyyyMMdd'T'HHmmss'Z'") and returns the Date
     * object.
     *
     * @param dateString The date string to parse.
     * @return The parsed Date object.
     * @throws IllegalArgumentException If the date string could not be parsed.
     */
    public static Date parseCompressedIso8601Date(String dateString) {
        return DateUtils.compressedIso8601DateFormat.parseDateTime(dateString).toDate();
    }
}
