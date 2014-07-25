/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web.action;

import com.mlnotes.web.support.service.ImageService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */

@Result(name="SUCCESS", location="/home.jsp")
public class HomeAction{
    private Integer count = 0;
    private Integer imageCount = 0;
    @Resource
    private ImageService imageService;

    public Integer getCount() {
        return count;
    }

    public Integer getImageCount() {
        return imageCount;
    }
    
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
    
    public String execute(){
        count += 1;
        imageCount = imageService.getCount();
        return "SUCCESS";
    }
}
