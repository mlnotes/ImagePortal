package com.mlnotes.web.router.tag;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class SetTag extends BaseTagSupport {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public int doStartTag(){
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doEndTag(){
        value = bodyContent.getString().trim();
        this.saveVariable(name, value);
        
        return EVAL_PAGE;
    }
}
