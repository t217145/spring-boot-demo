package com.cyrus822.demo.dummyWS.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.dummyWS.domains.QHBody;

@RestController
public class DummyController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @PostMapping({"","/","/dummy"})
    public @ResponseBody QHBody dummy(@RequestBody QHBody body,  @RequestHeader HttpHeaders headers){
        logger.info("Header::SysCode::{}", headers.getFirst("sysCode"));//Just proof of concept

        logger.info("Received::{}", body);

        QHBody rtn = new QHBody();
        rtn.setSalt("QHServer Salt");
        rtn.setSignature("QHServer Signature");
        rtn.setDataSecret("echo:" + body.getDataSecret());

        logger.info("Response::{}", rtn);
        return rtn;
    }

}
