package com.upuphub.apitalk.net.proto;

/**
 * ApisMessage
 *
 * @author Leo
 * @since ApisTalk:1.0 (2022-01-04)
 **/
public record ApisMessageFixedHeader(ApisCommand command,
                                     ApisPackStatus packStatus,
                                     ApisProtoVersion version) {
}
