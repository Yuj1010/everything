package com.repair.exception;

/**
 * auther   kangwang
 */
public class ArgumentExceptionCheck {
    private Object value;
    public static void check(Object value) throws Exception {
        if(value==null)
        {
            throw new Exception();
        }
    }
}
