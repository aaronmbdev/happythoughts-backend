package com.kernelpanic.happythoughts.business.comoestas;

import com.kernelpanic.happythoughts.business.comoestas.AnalisisResponse;
import lombok.Getter;

@Getter
public class ErrorResponse extends AnalisisResponse {
    public final String reason;
    public ErrorResponse(String result, String reason) {
        super(result);
        this.reason = reason;
    }

}
