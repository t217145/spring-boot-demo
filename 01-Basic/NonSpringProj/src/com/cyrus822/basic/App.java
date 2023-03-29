package com.cyrus822.basic;

public class App {
    public static void main(String[] args) throws Exception {
        Method1();
        Method2();
        Method3();
        Method4();
    }

    private static void Method1(){
        MyObjects obj = new MyObjects();
        obj.setPwd("123456");
        obj.setUsrName("Cyrus");
        obj.setUrl("jdbc:mysql://localhost:3306/testDB");
        System.out.println(obj.Conn());
        //do somethong with obj
    }

    private static void Method2(){
        MyObjects obj = new MyObjects();
        obj.setPwd("123456");
        obj.setUsrName("Cyrus");
        obj.setUrl("jdbc:mysql://localhost:3306/testDB");
        System.out.println(obj.Conn());
        //do somethong with obj
    }
    
    private static void Method3(){
        MyObjects obj = new MyObjects();
        obj.setPwd("123456");
        obj.setUsrName("Cyrus");
        obj.setUrl("jdbc:mysql://localhost:3306/testDB");
        System.out.println(obj.Conn());
        //do somethong with obj
    }
    
    private static void Method4(){
        MyObjects obj = new MyObjects();
        obj.setPwd("123456");
        obj.setUsrName("Cyrus");
        obj.setUrl("jdbc:mysql://localhost:3306/testDB");
        System.out.println(obj.Conn());
        //do somethong with obj
    }    
}