package com.cyrus822.demo.ActionFilter.utils;

import com.cyrus822.demo.ActionFilter.domains.QHRequestBody;

public class EncryptDecryptHandler {

    public String encrypt(String content){
        //dummy encrypt logic
        return content + " encrypted";
    }

    public String decrypt(QHRequestBody body){
        return body.getDataSecret().replace(body.getSalt(), "");
    }

    public String generateSignature(String salt, String dataSecret){
        return salt + dataSecret;
    }

    public boolean verify(QHRequestBody body){
        boolean isValid = false;
        //dummy logic
        if(body.getSignature().contains(body.getSalt())){
            isValid = true;
        }
        return isValid;
    }
}