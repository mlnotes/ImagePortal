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
public class HomeAction{
    private String name = "XXX";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String execute(){
        return "SUCCESS";
    }
}
