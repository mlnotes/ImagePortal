package com.mlnotes.web.ioc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class BeanProcessor {

    public static Object newInstance(String className) {
        Class<?> cls;
        Object obj;
        try {
            cls = Class.forName(className);
            obj = cls.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
    
    public static void setProperty(Object obj, String name, Object value) {
        Class<? extends Object> cls = obj.getClass();
        try {
            String methodName = getSetMethodName(name);
            Method[] methods = cls.getMethods();
            for(Method m : methods){
                if(m.getName().equals(methodName) && m.getParameterCount() == 1){
                    m.invoke(obj, value);
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BeanProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String getSetMethodName(String fieldName){
        return "set" + fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1);
    }
}
