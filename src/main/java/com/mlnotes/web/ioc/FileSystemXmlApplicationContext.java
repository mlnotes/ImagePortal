package com.mlnotes.web.ioc;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {
    private final BeanFactory beanFactory;
    
    public FileSystemXmlApplicationContext(String fileName){
        System.out.println("Filename:" + fileName);
        beanFactory = new XmlBeanFactory(fileName);
    }
    
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
}
