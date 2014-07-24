/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web.ioc;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */
public interface BeanFactory {
    public Object getBean(String name);
}
