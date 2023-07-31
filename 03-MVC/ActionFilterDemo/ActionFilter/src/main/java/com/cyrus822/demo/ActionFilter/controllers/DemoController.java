package com.cyrus822.demo.ActionFilter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.cyrus822.demo.ActionFilter.annotations.QHRequest;
import com.cyrus822.demo.ActionFilter.domains.QHRequestBody;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class DemoController {

    @Autowired
    private RestTemplate qhRestTemplate;
    
    private Logger logger = LoggerFactory.getLogger(getClass()); 
    
    @PostMapping({"", "/", "/dummy"})
    /* Cyrus : If added this this annotation, it will apply the filter, if no, it will bypass filter */
    @QHRequest 
    public String dummy(final HttpServletRequest request){
        Object objBody = request.getAttribute("decryptedContent");
        String body = "";
        if(objBody != null){
            body = (String)objBody;
        }
        return body;
    }

    @PostMapping("/normal")
    public String normal(@RequestBody QHRequestBody body){
        return body.getDataSecret();
    }

    @PostMapping("/callQhServer")
    public String callQhServer(@RequestBody String message){
        logger.info("Message received::", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(message, headers);
        QHRequestBody respBody = qhRestTemplate.postForObject("http://localhost:8081/dummy", request, QHRequestBody.class);

        return respBody == null ? "Error" : respBody.getDataSecret();//dummy
    }
}