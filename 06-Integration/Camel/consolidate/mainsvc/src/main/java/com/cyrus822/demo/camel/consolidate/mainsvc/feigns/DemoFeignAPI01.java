package com.cyrus822.demo.camel.consolidate.mainsvc.feigns;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "demows01", url = "http://localhost:8081/svc01")
public interface DemoFeignAPI01 extends DemoFeignAPI {

}