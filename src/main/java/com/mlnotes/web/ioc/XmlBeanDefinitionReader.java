package com.mlnotes.web.ioc;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class XmlBeanDefinitionReader {
    public static Map<String, Bean> readXmlFile(String fileName){
        fileName = Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName)
                .getPath()
                .substring(1);
        System.out.println("FileName In Reader: " + fileName);
        
        Map<String, Bean> beansMap = new HashMap<String, Bean>();
        
        SAXReader reader = new SAXReader();
        Document doc;
        try {
            doc = reader.read(new File(fileName));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        
        // find all beans
        Iterator<?> beanIterator = doc.getRootElement().elementIterator("bean");
        while(beanIterator.hasNext()){
            Element beanEle = (Element)beanIterator.next();
            
            Bean bean = new Bean();
            String beanId = beanEle.attributeValue("id");
            String beanType = beanEle.attributeValue("class");
            
            bean.setId(beanId);
            bean.setType(beanType);
            
            Iterator<?> propertyIterator = beanEle.elementIterator();
            while(propertyIterator.hasNext()){
                Element propertyEle = (Element)propertyIterator.next();
                String name = propertyEle.attributeValue("name");
                
                if(propertyEle.attribute("value") != null){
                    String value = propertyEle.attributeValue("value");
                    bean.getProperties().put(name, value);
                }else if(propertyEle.attribute("ref") != null){
                    String[] refString = new String[]{
                        propertyEle.attributeValue("ref")
                    };
                    bean.getProperties().put(name, refString);
                }
            }
            beansMap.put(beanId, bean);
        }
        return beansMap;
    }
}
