package com.chat.common.exception;

/**
 * @author Administrator
 */
public class ServiceException extends RuntimeException{

    private String msg;
    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
