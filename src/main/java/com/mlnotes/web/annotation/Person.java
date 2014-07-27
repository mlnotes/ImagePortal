/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mlnotes.web.annotation;

/**
 *
 * @author Zhu Hanfeng <me@mlnotes.com>
 */

@DBTable
public class Person {
    @SQLInteger
    private int age;
    @SQLString(constraints=@Constraints(primaryKey=true))
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
