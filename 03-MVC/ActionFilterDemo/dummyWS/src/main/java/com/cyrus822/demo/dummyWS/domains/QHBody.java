package com.cyrus822.demo.dummyWS.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QHBody {
    private String salt;
    private String signature;
    private String dataSecret;
}