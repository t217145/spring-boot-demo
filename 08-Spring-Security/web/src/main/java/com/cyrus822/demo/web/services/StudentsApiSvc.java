package com.cyrus822.demo.web.services;

import com.cyrus822.demo.common.apis.StudentsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "StudentsApiSvc", url = "${svc.url.students}")
public interface StudentsApiSvc extends StudentsApi {
    
}
