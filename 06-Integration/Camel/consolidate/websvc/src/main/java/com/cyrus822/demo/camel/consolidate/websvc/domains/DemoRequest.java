package com.cyrus822.demo.camel.consolidate.websvc.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DemoRequest {
    private String cusId;
    private String cusData;
}
