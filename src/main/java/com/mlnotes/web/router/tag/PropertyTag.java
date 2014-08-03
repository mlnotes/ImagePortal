package com.mlnotes.web.router.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class PropertyTag extends BaseTagSupport {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public int doStartTag(){
        return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag(){
        String result = (String)this.getVariable(value);
        JspWriter writer = pageContext.getOut();
        try {
            writer.print(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
