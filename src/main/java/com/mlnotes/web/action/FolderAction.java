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
    @Result(name="SUCCESS", location="/folder.jsp")
})
public class FolderAction {
    private String folder;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
    
    public String execute(){
        return "SUCCESS";
    }
}
