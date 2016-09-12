package com.moment.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/** 
 * Json与javaBean之间的转换工具类 
 *  
 */  
public class JsonOperator {  
  
    /** 
     * 将json字符串转换成bean对象
     */  
    public static <T> T jsonToBean(String jsonString, Class<T> cls) {  
        T t = null;  
        try {  
            Gson gson = new Gson();  
            t = gson.fromJson(jsonString, cls);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return t;  
    }  
  
    /** 
     * 将对象转换成json数据 
     *  
     * @param o 
     * @return 
     */  
    public static <T> String toJson(Object o) {  
        Gson gson = new Gson();  
        String jsonStr = gson.toJson(o);  
        return jsonStr;  
    }  
  
    /** 
     * 将json数据转换成List列表返回 
     *  
     * @param jsonString 
     * @param type 
     * @return 
     */  
    public static <T> List<T> jsonToList(String jsonString, Type type) {  
        List<T> list = new ArrayList<T>();  
        try {  
            Gson gson = new Gson();  
            list = gson.fromJson(jsonString, type);  
        } catch (Exception e) {  
        }  
        return list;  
    }   
} 