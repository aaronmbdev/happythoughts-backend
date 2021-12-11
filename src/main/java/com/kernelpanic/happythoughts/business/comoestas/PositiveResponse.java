package com.kernelpanic.happythoughts.business.comoestas;

import com.kernelpanic.happythoughts.business.comoestas.AnalisisResponse;

public class PositiveResponse extends AnalisisResponse {
    public final String docId;
    public PositiveResponse(String result, String docId) {
        super(result);
        this.docId = docId;
    }
}
