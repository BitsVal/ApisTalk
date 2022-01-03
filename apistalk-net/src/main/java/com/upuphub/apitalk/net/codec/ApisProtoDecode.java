package com.upuphub.apitalk.net.codec;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ApisProtoDecode extends ReplayingDecoder<ApisProtoDecode.DecoderState> {

    /**
     * This State Like MqttDecode.DecoderState
     * States of the decoder.
     * We start at READ_FIXED_HEADER, followed by
     * READ_VARIABLE_HEADER and finally READ_PAYLOAD.
     */
    enum DecoderState {
        READ_FIXED_HEADER,
        READ_VARIABLE_HEADER,
        READ_PAYLOAD,
        BAD_MESSAGE,
    }


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
