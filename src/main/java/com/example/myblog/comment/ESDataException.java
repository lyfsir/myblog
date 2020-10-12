package com.example.myblog.comment;

/**
 * @author lyf
 * @date 2020/6/23-4:07
 */
public class ESDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ESDataException() {
        super();
    }

    public ESDataException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ESDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public ESDataException(String message) {
        super(message);
    }

    public ESDataException(Throwable cause) {
        super(cause);
    }

}
