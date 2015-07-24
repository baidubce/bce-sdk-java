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
package com.baidubce.services.ses.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request object for sending email.
 */
public class SendEmailRequest extends SesRequest {
    /**
     * The mail body of send email request.
     * 
     * @see com.baidubce.services.ses.model.SendEmailRequest.Mail
     */
    private Mail mail;

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    /**
     * The class of mail body
     */
    public static class Mail {
        /**
         * The sender object of email.
         */
        private Source source;
        /**
         * The recipient object of email.
         */
        private Destination destination;
        /**
         * The title object of email.
         */
        private Subject subject;
        /**
         * The priority of email which is optional
         */
        private Integer priority;
        /**
         * The content of email.
         */
        private Message message;
        /**
         * list of {@link Attachment}
         */
        private List<Attachment> attachments;

        public Mail() {
        }

        public Mail(Source source, Destination destination, Subject subject, Integer priority, Message message) {
            this.source = source;
            this.destination = destination;
            this.subject = subject;
            this.priority = priority;
            this.message = message;
            attachments = new ArrayList<Attachment>();
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Destination getDestination() {
            return destination;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        public Subject getSubject() {
            return subject;
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public List<Attachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
        }

        /**
         * The sender object of email.
         */
        public static class Source {
            /**
             * The sender.
             */
            private String from;
            /**
             * The display name of sender, which can be custom by the users themselves
             */
            @JsonProperty("name")
            private String displayName;
            /**
             * The return path.
             */
            @JsonProperty("return_path")
            private String returnPath;
            /**
             * The reply to.
             */
            @JsonProperty("reply_to")
            private String replyTo;

            public Source() {
            }

            public Source(String from, String returnPath, String replyTo) {
                this.from = from;
                this.returnPath = returnPath;
                this.replyTo = replyTo;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }

            public String getReturnPath() {
                return returnPath;
            }

            public void setReturnPath(String returnPath) {
                this.returnPath = returnPath;
            }

            public String getReplyTo() {
                return replyTo;
            }

            public void setReplyTo(String replyTo) {
                this.replyTo = replyTo;
            }

        }

        /**
         * The recipient object.
         */
        public static class Destination {
            /**
             * List of recipient.
             */
            @JsonProperty("to_addr")
            private List<Addr> toAddr;
            /**
             * List of CC.
             */
            @JsonProperty("cc_addr")
            private List<Addr> ccAddr;
            /**
             * List of BCC.
             */
            @JsonProperty("bcc_addr")
            private List<Addr> bccAddr;

            public Destination() {
            }

            public Destination(List<Addr> toAddr, List<Addr> ccAddr, List<Addr> bccAddr) {
                this.toAddr = toAddr;
                this.ccAddr = ccAddr;
                this.bccAddr = bccAddr;
            }

            public List<Addr> getToAddr() {
                return toAddr;
            }

            public void setToAddr(List<Addr> toAddr) {
                this.toAddr = toAddr;
            }

            public List<Addr> getCcAddr() {
                return ccAddr;
            }

            public void setCcAddr(List<Addr> ccAddr) {
                this.ccAddr = ccAddr;
            }

            public List<Addr> getBccAddr() {
                return bccAddr;
            }

            public void setBccAddr(List<Addr> bccAddr) {
                this.bccAddr = bccAddr;
            }

            /**
             * The address object, which contains one string parameter.
             */
            public static class Addr {
                /**
                 * The string of address.
                 */
                private String addr;

                public Addr() {
                }

                public Addr(String addr) {
                    this.addr = addr;
                }

                public Addr withAddr(String addr) {
                    setAddr(addr);
                    return this;
                }

                public String getAddr() {
                    return addr;
                }

                public void setAddr(String addr) {
                    this.addr = addr;
                }

                // Get object list from string array
                @JsonIgnore
                public static List<Addr> asAddrList(String[] addrs) {
                    if (addrs == null) {
                        return null;
                    }

                    List<Addr> addrList = new ArrayList<Addr>(addrs.length);
                    for (String addr : addrs) {
                        addrList.add(new Addr().withAddr(addr));
                    }

                    return addrList;
                }
            }
        }

        public static class Subject {
            private Integer charset;
            private String data;

            public Subject withCharset(Integer charset) {
                setCharset(charset);
                return this;
            }

            public Subject withData(String data) {
                setData(data);
                return this;
            }

            public Integer getCharset() {
                return charset;
            }

            public void setCharset(Integer charset) {
                this.charset = charset;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }

        public static class Message {
            private Subject html;

            public Message withHtml(Subject html) {
                setHtml(html);
                return this;
            }

            public Subject getHtml() {
                return html;
            }

            public void setHtml(Subject html) {
                this.html = html;
            }
        }

        public static class Attachment {
            @JsonProperty("file_name")
            private String filename;
            @JsonProperty("file_data")
            private FileData filedata;

            public Attachment() {
            }

            public Attachment(String filename, String filedata) {
                this.filename = filename;
                this.filedata = new FileData().withData(filedata);
            }

            public Attachment(String filename, FileData filedata) {
                this.filename = filename;
                this.filedata = filedata;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public FileData getFiledata() {
                return filedata;
            }

            public void setFiledata(FileData filedata) {
                this.filedata = filedata;
            }

            public static class FileData {
                private String data;

                public FileData withData(String data) {
                    setData(data);
                    return this;
                }

                public String getData() {
                    return data;
                }

                public void setData(String data) {
                    this.data = data;
                }
            }
        }
    }

}
