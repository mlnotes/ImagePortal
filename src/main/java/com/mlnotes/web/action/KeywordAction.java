/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */

@Results({
    @Result(name="SUCCESS", location="/keyword.jsp")
})
public class KeywordAction {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public String execute(){
        return "SUCCESS";
    }
}
