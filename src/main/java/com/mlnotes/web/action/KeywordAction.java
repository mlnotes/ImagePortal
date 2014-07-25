/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web.action;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
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
