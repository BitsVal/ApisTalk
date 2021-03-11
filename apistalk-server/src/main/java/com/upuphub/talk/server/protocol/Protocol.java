package com.upuphub.talk.server.protocol;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ApisTalk Message Protocol
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-08 18:08
 **/
public class Protocol {
    @JsonProperty("header")
    protected Header header;
    @JsonProperty("data")
    protected String data;

    public Protocol() {
    }

    public static class R {
        public static final String HEADER = "header";
    }

    public static class Header{
        @JsonProperty("from")
        private String from = null;

        @JsonProperty("to")
        private String to = null;

        @JsonProperty("type")
        private byte type = 0;

        @JsonProperty("QoS")
        private byte QoS = 0;

        @JsonProperty("rc")
        private transient int rc = 0;

        @JsonProperty("fp")
        private String fp = null;

        @JsonProperty("dataType")
        private String dataType = null;

        @JsonIgnore
        private String socketId;

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

        public String getSocketId() {
            return socketId;
        }

        public void setSocketId(String socketId) {
            this.socketId = socketId;
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
