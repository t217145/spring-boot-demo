package com.cyrus822.demo.camel.consolidate.mainsvc.feigns;

import org.springframework.web.bind.annotation.PostMapping;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoRequest;
import com.cyrus822.demo.camel.consolidate.mainsvc.domains.DemoResponse;

public interface DemoFeignAPI {
    @PostMapping(value = "/", produces = "application/json")
    DemoResponse index(DemoRequest request);
}