package com.cyrus822.basic;

public class MyObjects {
    private String usrName;
    private String pwd;
    private String url;

    public String Conn(){
        if(usrName.equals("Cyrus") &&
           pwd.equals("123456")){
            return String.format("Connect to %s successfully", url);
        } else {
            return String.format("Faield to connect to %s", url);
        }
    }

    public String getUsrName() {
        return usrName;
    }
    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString(){
        return String.format("user name : %s%npassword : %s%nurl : %s%n", usrName, pwd, url); 
    }
}
