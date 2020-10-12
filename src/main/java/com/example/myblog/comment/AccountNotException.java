package com.example.myblog.comment;

/**
 * @author lyf
 * @date 2020/6/23-4:07
 */
public class AccountNotException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountNotException() {
        super();
    }

    public AccountNotException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AccountNotException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountNotException(String message) {
        super(message);
    }

    public AccountNotException(Throwable cause) {
        super(cause);
    }

}
