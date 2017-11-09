package com.zsc.csg.web.common;

import com.zsc.csg.web.common.exception.CsgAppException;

/**
 * 封装返回值信息
 * 
 */
public class ResultEntity {

    private static final int OK = 200;
    private static final int ERROR = 500;
    private static final String OK_MESSAGE = "ok";
    private static final String ERROR_MESSAGE = "error";

    /**
     * {code:"200", message:"{body}"}
     * 
     * @param body
     * @return
     */
    public static Meta ok(Object body) {
        return new Meta(OK, body);
    }

    /**
     * {code:"200", message:"ok"}
     * 
     * @param body
     * @return
     */
    public static Meta ok() {
        return new Meta(OK, OK_MESSAGE);
    }

    /**
     * {code:"500", message:"{body}"}
     * 
     * @param body
     * @return
     */
    public static Meta error(Object body) {
        return new Meta(ERROR, body);
    }

    /**
     * {code:"500", message:"error"}
     * 
     * @param body
     * @return
     */
    public static Meta error() {
        return new Meta(ERROR, ERROR_MESSAGE);
    }

    /**
     * 封装异常信息
     * 
     * @param e
     * @return
     */
    public static Meta error(Exception e) {
        if (e instanceof CsgAppException) {
            int code = ((CsgAppException) e).getCode();
            String message = ((CsgAppException) e).getMessage();
            return new Meta(code, message);
        }
        return new Meta(ERROR, e.getMessage());
    }

    /**
     * 封装异常信息
     * 
     * @param e
     * @return
     */
    public static Meta error(int errorCode, Exception e) {
        if (e instanceof CsgAppException) {
            int code = ((CsgAppException) e).getCode();
            String message = ((CsgAppException) e).getMessage();
            return new Meta(code, message);
        }
        return new Meta(errorCode, e.getMessage());
    }

    public static class Meta {

        private int code;
        private Object message;

        public Meta(int code, Object message) {
            this.code = code;
            this.message = message;
        }

        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * @return the message
         */
        public Object getMessage() {
            return message;
        }

        /**
         * @param message the message to set
         */
        public void setMessage(Object message) {
            this.message = message;
        }
    }
}
