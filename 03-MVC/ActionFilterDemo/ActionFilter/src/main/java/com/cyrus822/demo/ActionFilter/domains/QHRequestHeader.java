package com.cyrus822.demo.ActionFilter.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QHRequestHeader {
    private String sysCode;
    private String clientId;
    private String transNo;
    private String transDate;
    private String contentType;

    public String checkNull(){
        StringBuilder rtn = new StringBuilder();
        if(sysCode == null || sysCode.isBlank()){
            rtn.append("sysCode,");
        }
        if(clientId == null || clientId.isBlank()){
            rtn.append("clientId,");
        }
        if(transNo == null || transNo.isBlank()){
            rtn.append("transNo,");
        }
        if(transDate == null || transDate.isBlank()){
            rtn.append("transDate,");
        }
        if(contentType == null || contentType.isBlank()){
            rtn.append("contentType,");
        }                
        return rtn.length() > 0 ? rtn.insert(0, "Empty value in : ").toString() : "";
    }
}
