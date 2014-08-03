package com.mlnotes.web.router.tag;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import ognl.Ognl;
import ognl.OgnlException;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class IfTag extends BaseTagSupport {
    private String test;
    
    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    
    @Override
    public int doStartTag(){
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doEndTag(){
        Object root = new Object();
        Map<String, Object> context = this.getValueStack();
        Ognl.addDefaultContext(root, context);
        Boolean value = false;
        try {
            value = (Boolean)TextExpression.getValue(test, context, root);
        } catch (OgnlException ex) {
            ex.printStackTrace();
        }
        
        if(value){
            JspWriter writer = pageContext.getOut();
            try {
                writer.print(bodyContent.getString().trim());
            } catch (IOException ex) {
                Logger.getLogger(IfTag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return EVAL_PAGE;
    }
}
