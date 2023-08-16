package com.cyrus822.demo.camel.consolidate.mainsvc.feigns;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "demows02", url = "http://localhost:8082/svc02")
public interface DemoFeignAPI02 extends DemoFeignAPI {

}