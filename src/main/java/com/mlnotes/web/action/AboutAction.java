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
public class AboutAction {
    private Integer count = 0;

    public Integer getCount() {
        return count;
    }
    
    public String execute(){
        count += 1;
        return "SUCCESS";
    }
}
