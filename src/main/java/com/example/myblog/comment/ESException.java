package com.example.myblog.comment;

/**
 * @author lyf
 * @date 2020/6/23-4:07
 */
public class ESException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ESException() {
        super();
    }

    public ESException(String message, Throwable cause, boolean enableSuppression,
                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ESException(String message, Throwable cause) {
        super(message, cause);
    }

    public ESException(String message) {
        super(message);
    }

    public ESException(Throwable cause) {
        super(cause);
    }

}
