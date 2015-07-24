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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import com.baidubce.Protocol;
import com.baidubce.http.Headers;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class HttpUtils {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static BitSet URI_UNRESERVED_CHARACTERS = new BitSet();
    private static String[] PERCENT_ENCODED_STRINGS = new String[256];

    private static final Joiner queryStringJoiner = Joiner.on('&');
    private static boolean HTTP_VERBOSE = Boolean.parseBoolean(System.getProperty("bce.sdk.http", "false"));
//    private static boolean HTTP_VERBOSE = true;

    /**
     * Regex which matches any of the sequences that we need to fix up after URLEncoder.encode().
     */
    // private static final Pattern ENCODED_CHARACTERS_PATTERN;
    static {
        /*
         * StringBuilder pattern = new StringBuilder();
         *
         * pattern .append(Pattern.quote("+")) .append("|") .append(Pattern.quote("*")) .append("|")
         * .append(Pattern.quote("%7E")) .append("|") .append(Pattern.quote("%2F"));
         *
         * ENCODED_CHARACTERS_PATTERN = Pattern.compile(pattern.toString());
         */
        for (int i = 'a'; i <= 'z'; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        for (int i = '0'; i <= '9'; i++) {
            URI_UNRESERVED_CHARACTERS.set(i);
        }
        URI_UNRESERVED_CHARACTERS.set('-');
        URI_UNRESERVED_CHARACTERS.set('.');
        URI_UNRESERVED_CHARACTERS.set('_');
        URI_UNRESERVED_CHARACTERS.set('~');

        for (int i = 0; i < PERCENT_ENCODED_STRINGS.length; ++i) {
            PERCENT_ENCODED_STRINGS[i] = String.format("%%%02X", i);
        }
    }

    /**
     * Normalize a string for use in url path. The algorithm is:
     * <p>
     *
     * <ol>
     *   <li>Normalize the string</li>
     *   <li>replace all "%2F" with "/"</li>
     *   <li>replace all "//" with "/%2F"</li>
     * </ol>
     *
     * <p>
     * Bos object key can contain arbitrary characters, which may result double slash in the url path. Apache http
     * client will replace "//" in the path with a single '/', which makes the object key incorrect. Thus we replace
     * "//" with "/%2F" here.
     *
     * @param path the path string to normalize.
     * @return the normalized path string.
     * @see #normalize(String)
     */
    public static String normalizePath(String path) {
        return normalize(path).replace("%2F", "/");
    }

    /**
     * Normalize a string for use in BCE web service APIs. The normalization algorithm is:
     * <p>
     * <ol>
     *   <li>Convert the string into a UTF-8 byte array.</li>
     *   <li>Encode all octets into percent-encoding, except all URI unreserved characters per the RFC 3986.</li>
     * </ol>
     *
     * <p>
     * All letters used in the percent-encoding are in uppercase.
     *
     * @param value the string to normalize.
     * @return the normalized string.
     * @throws UnsupportedEncodingException
     */
    public static String normalize(String value) {
        try {
            StringBuilder builder = new StringBuilder();
            for (byte b : value.getBytes(DEFAULT_ENCODING)) {
                if (URI_UNRESERVED_CHARACTERS.get(b & 0xFF)) {
                    builder.append((char) b);
                } else {
                    builder.append(PERCENT_ENCODED_STRINGS[b & 0xFF]);
                }
            }
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encode a string for use in the path of a URL; uses URLEncoder.encode, (which encodes a string for use in the
     * query portion of a URL), then applies some postfilters to fix things up per the RFC. Can optionally handle
     * strings which are meant to encode a path (ie include '/'es which should NOT be escaped).
     *
     * @param value the value to encode
     * @param path true if the value is intended to represent a path
     * @return the encoded value
     */
    /*
     * public static String urlEncode(String value) { if (value == null) { return ""; }
     *
     * try { String encoded = URLEncoder.encode(value, DEFAULT_ENCODING);
     *
     * Matcher matcher = ENCODED_CHARACTERS_PATTERN.matcher(encoded); StringBuffer buffer = new
     * StringBuffer(encoded.length());
     *
     * while (matcher.find()) { String replacement = matcher.group(0);
     *
     * if ("+".equals(replacement)) { replacement = "%20"; } else if ("*".equals(replacement)) { replacement = "%2A"; }
     * else if ("%7E".equals(replacement)) { replacement = "~"; } else if (path && "%2F".equals(replacement)) {
     * replacement = "/"; }
     *
     * matcher.appendReplacement(buffer, replacement); }
     *
     * matcher.appendTail(buffer); return buffer.toString();
     *
     * } catch (UnsupportedEncodingException ex) { throw new RuntimeException(ex); } }
     */

    /**
     * Returns a host header according to the specified URI. The host header is generated with the same logic used by
     * apache http client, that is, append the port to hostname only if it is not the default port.
     *
     * @param uri the URI
     * @return a host header according to the specified URI.
     */
    public static String generateHostHeader(URI uri) {
        String host = uri.getHost();
        if (isUsingNonDefaultPort(uri)) {
            host += ":" + uri.getPort();
        }
        return host;
    }

    /**
     * Returns true if the specified URI is using a non-standard port (i.e. any port other than 80 for HTTP URIs or any
     * port other than 443 for HTTPS URIs).
     *
     * @param uri the URI
     * @return True if the specified URI is using a non-standard port, otherwise false.
     */
    public static boolean isUsingNonDefaultPort(URI uri) {
        String scheme = uri.getScheme().toLowerCase();
        int port = uri.getPort();
        if (port <= 0) {
            return false;
        }
        if (scheme.equals(Protocol.HTTP.toString())) {
            return port != Protocol.HTTP.getDefaultPort();
        }
        if (scheme.equals(Protocol.HTTPS.toString())) {
            return port != Protocol.HTTPS.getDefaultPort();
        }
        return false;
    }

    public static String getCanonicalQueryString(Map<String, String> parameters, boolean forSignature) {
        if (parameters.isEmpty()) {
            return "";
        }

        List<String> parameterStrings = Lists.newArrayList();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (forSignature && Headers.AUTHORIZATION.equalsIgnoreCase(entry.getKey())) {
                continue;
            }
            String key = entry.getKey();
            checkNotNull(key, "parameter key should not be null");
            String value = entry.getValue();
            if (value == null) {
                if (forSignature) {
                    parameterStrings.add(normalize(key) + '=');
                } else {
                    parameterStrings.add(normalize(key));
                }
            } else {
                parameterStrings.add(normalize(key) + '=' + normalize(value));
            }
        }
        Collections.sort(parameterStrings);

        return queryStringJoiner.join(parameterStrings);
    }

    /**
     * Append the given path to the given baseUri.
     *
     * <p>
     * This method will encode the given path but not the given baseUri.
     *
     * @param baseUri
     * @param pathComponents
     */
    public static URI appendUri(URI baseUri, String... pathComponents) {
        StringBuilder builder = new StringBuilder(baseUri.toASCIIString());
        for (String path : pathComponents) {
            if (path != null && path.length() > 0) {
                path = normalizePath(path);
                if (path.startsWith("/")) {
                    if (builder.charAt(builder.length() - 1) == '/') {
                        builder.setLength(builder.length() - 1);
                    }
                } else {
                    if (builder.charAt(builder.length() - 1) != '/') {
                        builder.append('/');
                    }
                }
                builder.append(path);
            }
        }
        try {
            return new URI(builder.toString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unexpected error", e);
        }
    }
    
    public static void printRequest(HttpRequestBase request) {
        if (!HTTP_VERBOSE) {
            return;
        }
        System.out.println("\n-------------> ");
        System.out.println(request.getRequestLine());
        for (Header h : request.getAllHeaders()) {
            System.out.println(h.getName() + " : " + h.getValue());
        }
        RequestConfig config = request.getConfig();
        if (config != null) {
            System.out.println("getConnectionRequestTimeout: "
                    + config.getConnectionRequestTimeout());
            System.out.println("getConnectTimeout: "
                    + config.getConnectTimeout());
            System.out.println("getCookieSpec: " + config.getCookieSpec());
            System.out.println("getLocalAddress: " + config.getLocalAddress());

        }
    }

    public static void printResponse(CloseableHttpResponse response) {
        if (!HTTP_VERBOSE) {
            return;
        }
        System.out.println("\n<------------- ");
        StatusLine status = response.getStatusLine();
        System.out.println(status.getStatusCode() + " - "
                + status.getReasonPhrase());
        Header[] heads = response.getAllHeaders();
        for (Header h : heads) {
            System.out.println(h.getName() + " : " + h.getValue());
        }
    }

}