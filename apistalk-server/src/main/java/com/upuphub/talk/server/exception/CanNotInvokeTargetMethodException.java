package com.upuphub.talk.server.exception;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-03-12 13:39
 **/
public class CanNotInvokeTargetMethodException extends RuntimeException{
    public CanNotInvokeTargetMethodException() {
    }

    public CanNotInvokeTargetMethodException(String message) {
        super(message);
    }
}
