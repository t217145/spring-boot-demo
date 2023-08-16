package com.cyrus822.demo.camel.consolidate.mainsvc.feigns;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "demows03", url = "http://localhost:8083/svc03")
public interface DemoFeignAPI03 extends DemoFeignAPI {

}