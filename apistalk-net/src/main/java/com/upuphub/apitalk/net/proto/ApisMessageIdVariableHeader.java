package com.upuphub.apitalk.net.proto;

/**
 * todo MessageId的可变头
 *
 * @author Leo
 * @since ApisTalk:1.0 (2022-01-04)
 **/
public class ApisMessageIdVariableHeader extends AbstractApisMessageVariableHeader {
    private static final String MESSAGE_ID = "msgId";

    public String getMessageId() {
        return this.get(MESSAGE_ID);
    }

    public void setMessageId(String messageId) {
        this.put(MESSAGE_ID, messageId);
    }

}
