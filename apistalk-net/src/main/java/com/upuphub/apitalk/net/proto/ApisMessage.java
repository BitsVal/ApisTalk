package com.upuphub.apitalk.net.proto;

/**
 * ApisMessage
 *
 * @author Leo
 * @since ApisTalk:1.0 (2022-01-04)
 **/
public record ApisMessage(ApisMessageFixedHeader fixedHeader,
                          AbstractApisMessageVariableHeader variableHeader,
                          ApisMessagePayload payload) {

}
