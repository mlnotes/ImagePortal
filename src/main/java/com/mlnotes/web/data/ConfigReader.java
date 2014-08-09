package com.mlnotes.web.data;

import com.mlnotes.web.data.TableMapping.Table;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class ConfigReader {
    public static final String DRIVER = "hibernate.connection.driver_class";
    public static final String URL = "hibernate.connnection.url";
    public static final String USERNAME = "hibernate.connection.username";
    public static final String PASSWORD = "hibernate.connection.password";

    public Configuration read(String fileName){
        fileName = Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName)
                .getPath()
                .substring(1);

        System.out.println("Hibernate Config Path: " + fileName);

        SAXReader reader = new SAXReader();
        Document doc;
        try {
            doc = reader.read(new File(fileName));
        } catch (DocumentException ex) {
            throw new RuntimeException(ex);
        }
        
        Element sessionFactory = doc.getRootElement().element("session-factory");
        List<Element> propertyElements = sessionFactory.elements("property");
        Map<String, String> properties = new HashMap<String, String>();
        for(Element propEle : propertyElements){
            String name = propEle.attributeValue("name");
            String prop = propEle.getTextTrim();
            properties.put(name, prop);
        }
        
        Configuration config = new Configuration();
        config.setDriver(properties.get(DRIVER));
        config.setUrl(properties.get(URL));
        config.setUsername(properties.get(USERNAME));
        config.setPassword(properties.get(PASSWORD));
        
        TableMapping tables = new TableMapping();
        List<Element> mappingElements = sessionFactory.elements("mapping");
        for(Element ele : mappingElements){
            String source = ele.attributeValue("resource");
            Table table = readTableMapping(source);
            tables.addTable(table.getClassName(), table);
        }
        
        config.setTables(tables);
        return config;
    }
    
    private Table readTableMapping(String fileName){
        fileName = Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName)
                .getPath()
                .substring(1);
        
        System.out.println("Source File: " + fileName);
        
        // TODO read mapping files
        
        return new Table();
    }
}
