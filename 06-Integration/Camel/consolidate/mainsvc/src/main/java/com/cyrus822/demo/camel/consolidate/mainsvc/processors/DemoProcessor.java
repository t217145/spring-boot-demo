package com.cyrus822.demo.camel.consolidate.mainsvc.processors;

import org.apache.camel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoRequest;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoResponse;
import com.cyrus822.demo.camel.consolidate.mainsvc.feigns.DemoFeignAPI01;
import com.cyrus822.demo.camel.consolidate.mainsvc.feigns.DemoFeignAPI02;
import com.cyrus822.demo.camel.consolidate.mainsvc.feigns.DemoFeignAPI03;
import org.springframework.stereotype.Component;

@Component
public class DemoProcessor {

    @Autowired
    private DemoFeignAPI01 svc01;

    @Autowired
    private DemoFeignAPI02 svc02;

    @Autowired
    private DemoFeignAPI03 svc03;

    public DemoResponse callSvc01(@Header("svc01") DemoRequest request){
        return svc01.index(request);
    }

    public DemoResponse callSvc02(@Header("svc02") DemoRequest request){
        return svc02.index(request);
    }
    
    public DemoResponse callSvc03(@Header("svc03") DemoRequest request){
        return svc03.index(request);
    }    
    
}
