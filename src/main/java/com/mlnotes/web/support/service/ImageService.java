package com.mlnotes.web.support.service;

import com.mlnotes.web.support.dao.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
@Service
public class ImageService {
    @Autowired
    private ImageDAO imageDAO;
    private Integer count = 0;

    public void setImageDAO(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    public Integer getCount(){
        count++;
        return count;
    }
}
