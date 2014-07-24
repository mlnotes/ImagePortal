package com.mlnotes.web.ioc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class MLContextLoaderListener implements ServletContextListener{
    private static ApplicationContext applicationContext;
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Create Application Context Listener!");
        applicationContext = new FileSystemXmlApplicationContext("applicationContext.xml");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        
    }

}
