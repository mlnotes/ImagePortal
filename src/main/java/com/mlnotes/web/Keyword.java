/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public class Keyword {
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