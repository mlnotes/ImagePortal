package com.mlnotes.web.router;

import com.mlnotes.web.factory.ObjectFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class RouterFilter implements Filter{
    private final String DefaultConfig = "struts.xml";
    
    private Map<String, Action> actionMap;
    private ObjectFactory objectFactory;
    
    public void init(FilterConfig fc) throws ServletException {
        System.out.println("Route Filter Init");
        String config = fc.getInitParameter("config");
        
        if(config == null){
            config = DefaultConfig;
        }
        actionMap = readActionMap(config);
        objectFactory = ObjectFactory.getInstance();
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        System.out.println("Route Filter do Filter");
        
        HttpServletRequest request = (HttpServletRequest)req;
        String path = request.getServletPath();
        // remove .action
        path = path.substring(0, path.length()-7);
        
        Action action = actionMap.get(path);
        if(action != null){
            Object obj = objectFactory.getObject(action.type);
            if(obj != null){
                Class<?> clazz = obj.getClass();
                try {
                    Method method = clazz.getMethod("execute");
                    String result = (String)method.invoke(obj);
                    String resource = action.getResource(result);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(resource);
                    dispatcher.forward(req, res);
                    return;
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                } catch (SecurityException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalArgumentException ex) {
                    throw new RuntimeException(ex);
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                }
                
            }
        }
        
        fc.doFilter(req, res);
    }

    public void destroy() {
        System.out.println("Route Filter Destroy");
    }

    private Map<String, Action> readActionMap(String fileName){
        Map<String, Action> map = new HashMap<String, Action>();
        fileName = Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName)
                .getPath()
                .substring(1);
        
        System.out.println("Action Map Path: " + fileName);
        
        SAXReader reader = new SAXReader();
        Document doc;
        
        try {
            doc = reader.read(new File(fileName));
        } catch (DocumentException ex) {
            throw new RuntimeException(ex);
        }
        
        // find all packages
        Iterator<Element> packageIter = doc.getRootElement().elementIterator("package");
        while(packageIter.hasNext()){
            Element packageEle = packageIter.next();
            String namespace = packageEle.attributeValue("namespace");
            if(!namespace.endsWith("/")){
                namespace += "/";
            }
            
            Iterator<Element> actionIter = packageEle.elementIterator("action");
            while(actionIter.hasNext()){
                Element actionEle = actionIter.next();
                String name = actionEle.attributeValue("name");
                String type = actionEle.attributeValue("class");
                
                Action action = new Action(name, type);
                Iterator<Element> resultIter = actionEle.elementIterator("result");
                while(resultIter.hasNext()){
                    Element resultEle = resultIter.next();
                    String resultName = resultEle.attributeValue("name");
                    String resultResource = resultEle.getTextTrim();
                    action.addResult(resultName, resultResource);
                }
                map.put(namespace + name, action);
            }
        }
        
        return map;
    }
    
    class Action{
        private String name;
        private String type;
        private Map<String, String> results;
        
        public Action(){
            results = new HashMap<String, String>();
        }
        
        public Action(String name, String type){
            this.name = name;
            this.type = type;
            results = new HashMap<String, String>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Map<String, String> getResults() {
            return results;
        }

        public void setResults(Map<String, String> results) {
            this.results = results;
        }
        
        public void addResult(String result, String resource){
            results.put(result, resource);
        }
        
        public String getResource(String result){
            return results.get(result);
        }
    }
}
