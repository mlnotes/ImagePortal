package com.mlnotes.web.factory;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class ObjectFactory {
    private static ObjectFactory instance;
    
    public static ObjectFactory getInstance(){
        return instance;
    }
    
    private ObjectFactory(){
        
    }
    
    static void create(String config){
        instance = new ObjectFactory();
    }

    
    public Object getObject(String type){
        System.out.println("Get Object:" + type);
        Class<?> clazz;
        Object obj;
        
        try {
            clazz = Class.forName(type);
            obj = clazz.newInstance();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (InstantiationException ex) {
            throw new RuntimeException(ex);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        return obj;
    }
}
