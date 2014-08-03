package com.mlnotes.web.router.tag;

import com.mlnotes.web.router.RouterFilter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class BaseTagSupport extends BodyTagSupport {

    protected static final String ROUTER_VALUESTACK_KEY = "router.valueStack";

    protected void saveVariable(String name, Object value) {
        Map<String, Object> stack = getValueStack();
        stack.put(name, value);
    }

    protected Object getVariable(String name) {
        ServletRequest req = pageContext.getRequest();
        Object actionObj = req.getAttribute(RouterFilter.ACTION_OBJECT);
        String getterName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        Class<?> clazz = actionObj.getClass();
        try {
            Method getter = clazz.getMethod(getterName);
            return getter.invoke(actionObj);
        } catch (Exception ex) {
            Map<String, Object> stack = getValueStack();
            return stack.get(name);
        }
    }

    public Map<String, Object> getValueStack() {
        Object objStack = pageContext.getAttribute(ROUTER_VALUESTACK_KEY);
        if (objStack != null) {
            return (Map<String, Object>) objStack;
        } else {
            Map<String, Object> stack = new HashMap<String, Object>();
            pageContext.setAttribute(ROUTER_VALUESTACK_KEY, stack);
            return stack;
        }
    }
}
