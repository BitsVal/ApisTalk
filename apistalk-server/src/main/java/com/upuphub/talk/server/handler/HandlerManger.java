package com.upuphub.talk.server.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-14 10:54
 **/
public final class HandlerManger {
    private final Map<HandlerType,Class<? extends ProtocolHandler>> APIS_HANDLER = new HashMap<>();
    private final Map<ProtocolHandler,Integer> INSTANCES_CONFIG = new HashMap<>();

}
