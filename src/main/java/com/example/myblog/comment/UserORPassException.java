package com.example.myblog.comment;

/**
 * @author lyf
 * @date 2020/6/23-4:07
 */
public class UserORPassException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserORPassException() {
        super();
    }

    public UserORPassException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserORPassException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserORPassException(String message) {
        super(message);
    }

    public UserORPassException(Throwable cause) {
        super(cause);
    }

}
