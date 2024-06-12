package com.econnect.econnect.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResult {
    @Builder.Default
    private boolean success = false;

    @Builder.Default
    private int code = -1;

    @Builder.Default
    private String msg = "";

}
