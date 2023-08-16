package com.cyrus822.demo.camel.consolidate.mainsvc.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DemoResponse {
    private int responseId;
    private String responseData;

    @Override
    public boolean equals(final Object obj) {
        if (obj.getClass() != getClass()) {
            return false;
        }
        DemoResponse otherResp = (DemoResponse) obj;

        return otherResp.responseId == this.responseId;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
