package com.kernelpanic.happythoughts.business.motivate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MotivateResponseError extends Exception {
    private final String reason;

    public MotivateResponseError(String reason) {
        this.reason = reason;
    }
}
