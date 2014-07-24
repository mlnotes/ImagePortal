package com.mlnotes.web.ioc;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import java.util.Map;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class MLObjectFactory extends ObjectFactory {
    private final ApplicationContext applicationContext;
    public MLObjectFactory(){
        System.out.println("Create Mlnotes Object Factory!");
        applicationContext = MLContextLoaderListener.getApplicationContext();
    } 
    
    @Override
    public Object buildAction(String actionName, String namespace, ActionConfig config, Map extraContext) throws Exception 
    {
        System.out.println("AAAAAAAAAAAAABuild Action :" + actionName + " Namespace: " + namespace);
        String beanName = namespace + actionName;
        Object obj = applicationContext.getBean(beanName);
        if(obj != null){
            return obj;
        }else{
            return super.buildAction(actionName, namespace, config, extraContext);
        }
    }
    
    @Override
    public Object buildBean(Class clazz, Map extraContext) throws Exception
    {
        System.out.println("AAAAAAAAAABuildBean: " + clazz);
        return super.buildBean(clazz, extraContext);
    }
    
    @Override
    public Object buildBean(String className, Map extraContext) throws Exception{
        System.out.println("BBBBBBBBuildBean: " + className);
        
        
        return super.buildBean(className, extraContext);
    }
}
