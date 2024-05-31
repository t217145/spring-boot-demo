package com.cyrus822.demo.ActionFilter.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.cyrus822.demo.ActionFilter.annotations.QHRequest;
import com.cyrus822.demo.ActionFilter.domains.QHRequestBody;
import com.cyrus822.demo.ActionFilter.domains.QHRequestHeader;
import com.cyrus822.demo.ActionFilter.utils.EncryptDecryptHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    private EncryptDecryptHandler encDecHandler = new EncryptDecryptHandler();

    private Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Is annotated logic
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // Test if the controller-method is annotated with @CustomFilter
            QHRequest filter = handlerMethod.getMethod().getAnnotation(QHRequest.class);
            if (filter != null) {
                QHRequestHeader hdr = extractHeader(request);

                String chkNullRtn = hdr.checkNull();
                if (chkNullRtn.length() != 0) {
                    logger.error(chkNullRtn);
                } else {
                    logger.info("header content::{}", hdr);
                    String decryptedContent = decryptDataSecret(request);
                    request.setAttribute("decryptedContent", decryptedContent);
                } // end of validating header
            }
        } // end of checking whether the method has annotation
        return true;
    }

    private QHRequestHeader extractHeader(HttpServletRequest request) {
        return new QHRequestHeader(
                request.getHeader("sysCode"),
                request.getHeader("clientId"),
                request.getHeader("transNo"),
                request.getHeader("transDate"),
                request.getHeader("Content-Type"));
    }// end of extractHeader()

    private String decryptDataSecret(HttpServletRequest request) {
        String decryptedContent = "";
        try {
            // read the HTTP payload
            BufferedReader br = request.getReader();
            String content = "";
            StringBuilder sbBody = new StringBuilder();
            while ((content = br.readLine()) != null) {
                sbBody.append(content);
            }
            content = sbBody.toString();
            logger.info("encrypted content::{}", content);

            // decrypt
            ObjectMapper objectMapper = new ObjectMapper();
            QHRequestBody body = objectMapper.readValue(content, QHRequestBody.class);
            if (encDecHandler.verify(body)) {
                decryptedContent = encDecHandler.decrypt(body);
                logger.info("decrypted content::{}", decryptedContent);
            } else {
                logger.error("invalid payload");
            }
        } catch (IOException ioe) {
            logger.error("Error in decryptDataSecret()", ioe);
        }
        return decryptedContent;
    }// end of decryptDataSecret()
}