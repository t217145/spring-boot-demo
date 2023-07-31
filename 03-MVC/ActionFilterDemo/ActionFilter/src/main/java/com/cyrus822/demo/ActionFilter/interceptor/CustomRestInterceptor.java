package com.cyrus822.demo.ActionFilter.interceptor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import com.cyrus822.demo.ActionFilter.domains.QHRequestBody;
import com.cyrus822.demo.ActionFilter.utils.EncryptDecryptHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomRestInterceptor implements ClientHttpRequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(CustomRestInterceptor.class);
    private EncryptDecryptHandler encDecHandler = new EncryptDecryptHandler();  

    private static final String SALT = "MFSalt";
    private static final String SYSCODE = "MFCGBAConn";    

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        //prepare header
        prepareHeader(request);

        //encrypt the request
        String dataSecret = encryptDataSecret(new String(body));
        String signature = encDecHandler.generateSignature(SALT, dataSecret);
        QHRequestBody reqBody = new QHRequestBody(SALT, signature, dataSecret);

        ClientHttpResponse response = execution.execute(request, new ObjectMapper().writeValueAsString(reqBody).getBytes());

        //response also need to decrypt and extract header
        return response;
    }

    private void prepareHeader(HttpRequest request){
        request.getHeaders().add("sysCode", SYSCODE);
        request.getHeaders().add("clientId", "ClientId123");
        request.getHeaders().add("transNo", Long.toString(logToDBForTransaction()));
        request.getHeaders().add("transDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        request.getHeaders().add("Content-Type", "application/json");
    }
    
    private long logToDBForTransaction(){
        //dummy save to DB and then get the transaction id
        return new Random().nextLong();
    }

    private String encryptDataSecret(String content){
        logger.info("plain text content::{}", content);
        //encrypt
        String encryptedContent = encDecHandler.encrypt(content);
        logger.info("encrypted content::{}", encryptedContent);
        return encryptedContent;
    }//end of encryptDataSecret()
}
