package com.example.myblog.comment;

/**
 * @author lyf
 * @date 2020/6/23-4:07
 */
public class NotAuthorDel extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NotAuthorDel() {
        super();
    }

    public NotAuthorDel(String message, Throwable cause, boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotAuthorDel(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorDel(String message) {
        super(message);
    }

    public NotAuthorDel(Throwable cause) {
        super(cause);
    }

}
