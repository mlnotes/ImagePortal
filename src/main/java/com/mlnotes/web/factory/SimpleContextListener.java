package com.mlnotes.web.factory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class SimpleContextListener implements ServletContextListener {
    private final String DEFAULT_CONFIG = "applicationContext.xml";
    
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Object Factory Init");
        ServletContext context = sce.getServletContext();
        String config = context.getInitParameter("config");
        if(config == null){
            config = DEFAULT_CONFIG;
        }
        ObjectFactory.create(config);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Object Factory Destory");
    }
}
