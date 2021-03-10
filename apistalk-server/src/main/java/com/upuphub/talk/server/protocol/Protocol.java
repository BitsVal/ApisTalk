package com.upuphub.talk.server.protocol;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ApisTalk Message Protocol
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-08 18:08
 **/
public class Protocol {
    protected Header header;
    protected String data;

    public Protocol() {
    }

    public static class R {
        public static final String HEADER = "header";
    }

    public static class Header{
        private String from = null;
        private String to = null;
        private byte type = 0;
        @JsonProperty("QoS")
        private byte QoS = 0;
        private transient int rc = 0;
        private String fp = null;
        private String dataType = null;

        public Header() {
        }

        public Header(String from, String to, byte type, byte QoS, int retryCount, String fingerPrint, String dataType) {
            this.from = from;
            this.to = to;
            this.type = type;
            this.QoS = QoS;
            this.rc = retryCount;
            this.fp = fingerPrint;
            this.dataType = dataType;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public byte getType() {
            return type;
        }

        public void setType(byte type) {
            this.type = type;
        }

        public byte getQoS() {
            return QoS;
        }

        public void setQoS(byte qoS) {
            QoS = qoS;
        }

        public int getRc() {
            return rc;
        }

        public void setRc(int rc) {
            this.rc = rc;
        }

        public String getFp() {
            return fp;
        }

        public void setFp(String fp) {
            this.fp = fp;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }
    }

    public Protocol(Header header, String data) {
        this.header = header;
        this.data = data;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
