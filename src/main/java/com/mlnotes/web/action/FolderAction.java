/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web.action;

import com.mlnotes.web.support.service.ImageService;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */

@Results({
    @Result(name="SUCCESS", location="/folder.jsp")
})
public class FolderAction {
    private String folder;
    private Integer imageCount = 0;
    @Autowired
    private ImageService imageService;

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
    
    public String getFolder() {
        return folder;
    }

    public Integer getImageCount() {
        return imageCount;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
    
    public String execute(){
        imageCount = imageService.getCount();
        return "SUCCESS";
    }
}
