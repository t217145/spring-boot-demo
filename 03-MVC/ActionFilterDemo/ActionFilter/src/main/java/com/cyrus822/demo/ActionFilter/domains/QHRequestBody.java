package com.cyrus822.demo.ActionFilter.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QHRequestBody {
    @JsonProperty
    private String salt;
    @JsonProperty
    private String signature;
    @JsonProperty
    private String dataSecret;
}