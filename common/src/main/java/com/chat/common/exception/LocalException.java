package com.chat.common.exception;

/**
 * @author mengw
 *  本地异常处理
 */
public class LocalException {
    public void error(String message) {
        throw new ServiceException(message);
    }
}
