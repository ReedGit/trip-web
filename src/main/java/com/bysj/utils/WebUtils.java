package com.bysj.utils;

public class WebUtils {

    public static boolean isEmpty(String param){
        if(param == null || "".equals(param.trim()))
            return false;
        return true;
    }
    
    public static String parseString(Object object){
        if(object == null || "".equals(object))
            return "";
        return object.toString();
    }
    
    public static Long parseLong(Object object){
        if(object == null)
            return null;
        return (Long)object;
    }
    
}
