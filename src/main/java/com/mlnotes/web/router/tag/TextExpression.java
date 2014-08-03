package com.mlnotes.web.router.tag;

import java.util.Map;
import ognl.Ognl;
import ognl.OgnlException;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class TextExpression {
    private static final String START = "%";
    
    public static String getExpression(String expr){
        int start = expr.indexOf(START+"{");
        if(start != -1){
            int end = expr.indexOf("}", start);
            if(end != -1){
                return expr.substring(start+2, end);
            }
        }
        return "";
    }
    
    public static Object getValue(String expr, Map<String, Object> context, Object root) throws OgnlException{
        expr = getExpression(expr);
        Object e = Ognl.parseExpression(expr);
        return Ognl.getValue(e, context, root);
    }
}
