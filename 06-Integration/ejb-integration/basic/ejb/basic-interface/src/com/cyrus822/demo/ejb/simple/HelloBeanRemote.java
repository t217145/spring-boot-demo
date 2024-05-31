/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyrus822.demo.ejb.simple;

import javax.ejb.Remote;

/**
 *
 * @author Cyrus Cheng
 */
@Remote
public interface HelloBeanRemote {

    String sayHello(String name);
    
}
