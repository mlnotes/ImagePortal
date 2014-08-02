package com.mlnotes.web.router.tag;

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
        return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doEndTag(){
        return EVAL_PAGE;
    }
}
