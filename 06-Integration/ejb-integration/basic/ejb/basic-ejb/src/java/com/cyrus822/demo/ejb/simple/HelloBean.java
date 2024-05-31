/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyrus822.demo.ejb.simple;

import javax.ejb.Stateless;

@Stateless(name = "HelloBean")
public class HelloBean implements HelloBeanRemote {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
    
}
