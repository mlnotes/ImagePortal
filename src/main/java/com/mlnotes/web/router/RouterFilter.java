package com.mlnotes.web.router;

import com.mlnotes.web.factory.ObjectFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class RouterFilter implements Filter {

    private static final String DEFAULT_CONFIG = "struts.xml";
    public static final String ACTION_OBJECT = "action.Object";

    private Map<String, Action> actionMap;
    private ObjectFactory objectFactory;

    public void init(FilterConfig fc) throws ServletException {
        System.out.println("Route Filter Init");
        String config = fc.getInitParameter("config");

        if (config == null) {
            config = DEFAULT_CONFIG;
        }
        actionMap = readActionMap(config);
        objectFactory = ObjectFactory.getInstance();
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        System.out.println("Route Filter do Filter");
        System.out.println(req.getClass());
        System.out.println(res.getClass());

        HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getServletPath();
        // remove .action
        path = path.substring(0, path.length() - 7);

        Action action = actionMap.get(path);
        if (action != null) {
            Object obj = objectFactory.getObject(action.type);
            if (obj != null) {
                Class<?> clazz = obj.getClass();
                try {
                    // set properties of this object
                    Map<String, String[]> paramMap = request.getParameterMap();
                    for (Entry<String, String[]> entry : paramMap.entrySet()) {
                        setProperty(clazz, obj, entry.getKey(), entry.getValue()[0]);
                    }

                    Method method = clazz.getMethod("execute");
                    String result = (String) method.invoke(obj);
                    String resource = action.getResource(result);
                    RequestDispatcher dispatcher = req.getRequestDispatcher(resource);
                    req.setAttribute(ACTION_OBJECT, obj);
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

    private Map<String, Action> readActionMap(String fileName) {
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
        while (packageIter.hasNext()) {
            Element packageEle = packageIter.next();
            String namespace = packageEle.attributeValue("namespace");
            if (!namespace.endsWith("/")) {
                namespace += "/";
            }

            Iterator<Element> actionIter = packageEle.elementIterator("action");
            while (actionIter.hasNext()) {
                Element actionEle = actionIter.next();
                String name = actionEle.attributeValue("name");
                String type = actionEle.attributeValue("class");

                Action action = new Action(name, type);
                Iterator<Element> resultIter = actionEle.elementIterator("result");
                while (resultIter.hasNext()) {
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

    private void setProperty(Class<?> clazz, Object obj, String name, String value) {
        String setterName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (m.getName().equals(setterName) && m.getParameterCount() == 1) {
                Class<?> type = m.getParameterTypes()[0];
                Object param = value;
                if(type.equals(boolean.class) || type.equals(Boolean.class)){
                    param = Boolean.valueOf(value);
                }else if(type.equals(byte.class) || type.equals(Byte.class)){
                    param = Byte.valueOf(value);
                }else if(type.equals(short.class) || type.equals(Short.class)){
                    param = Short.valueOf(value);
                }else if(type.equals(int.class) || type.equals(Integer.class)){
                    param = Integer.valueOf(value);
                }else if(type.equals(long.class) || type.equals(Long.class)){
                    param = Long.valueOf(value);
                }else if(type.equals(float.class) || type.equals(Float.class)){
                    param = Float.valueOf(value);
                }else if(type.equals(double.class) || type.equals(Double.class)){
                    param = Double.valueOf(value);
                }
                
                try {
                    m.invoke(obj, param);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RouterFilter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(RouterFilter.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(RouterFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
    }

    class Action {

        private String name;
        private String type;
        private Map<String, String> results;

        public Action() {
            results = new HashMap<String, String>();
        }

        public Action(String name, String type) {
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

        public void addResult(String result, String resource) {
            results.put(result, resource);
        }

        public String getResource(String result) {
            return results.get(result);
        }
    }
}
