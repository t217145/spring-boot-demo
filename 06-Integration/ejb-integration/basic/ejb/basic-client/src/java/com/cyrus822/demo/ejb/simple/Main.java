/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyrus822.demo.ejb.simple;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Main {

    public static void main(String[] args) throws Exception{    
        Context ctx = new InitialContext();
        HelloBeanRemote svc = (HelloBeanRemote)ctx.lookup("java:global/basic-ejb/HelloBean");
        System.out.println(svc.sayHello("Cyrus"));
    }
    
}
