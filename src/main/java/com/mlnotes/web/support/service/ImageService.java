package com.mlnotes.web.support.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
@Service
public class ImageService {
    private Integer count = 0;
    
    public Integer getCount(){
        count++;
        return count;
    }
}
