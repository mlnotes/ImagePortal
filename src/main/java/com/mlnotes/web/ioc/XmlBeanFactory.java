package com.mlnotes.web.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class XmlBeanFactory implements BeanFactory {
    private static Map<String, Bean> beansProsMap;
    private static Map<String, Object> beansMap;
    
    public XmlBeanFactory(String filename){
        beansProsMap = XmlBeanDefinitionReader.readXmlFile(filename);
        beansMap = new HashMap<String, Object>();
        for(Entry<String, Bean> entry : beansProsMap.entrySet()){
            String beanName = entry.getKey();
            if(!beansMap.containsKey(beanName)){
                beansMap.put(beanName, createObject(beanName));
            }
        }
    }
    
    public Object getBean(String name) {
        return beansMap.get(name);
    }
    
    private Object getObject(String name){
        if(!beansMap.containsKey(name)){
            beansMap.put(name, createObject(name));
        }
        return beansMap.get(name);
    }
    
    private void setProperty(Bean bean, Object obj){
        Map<String, Object> propertiesMap = bean.getProperties();
        for(Entry<String, Object> entry : propertiesMap.entrySet()){
            String property = entry.getKey();
            Object value = entry.getValue();
            
            if(value instanceof String){ // value
                BeanProcessor.setProperty(obj, property, value);
            }else if(value instanceof String[]){ // ref
                String[] strValue = (String[])value;
                String beanId = strValue[0];
                BeanProcessor.setProperty(obj, property, getObject(beanId));
            }
        }
    }
    
    private Object createObject(String name){
        Bean bean = beansProsMap.get(name);
        Object obj = BeanProcessor.newInstance(bean.getType());
        setProperty(bean, obj);
        return obj;
    }
}
