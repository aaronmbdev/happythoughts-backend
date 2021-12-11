package com.kernelpanic.happythoughts.business.comoestas;

public class PositiveResponse extends AnalisisResponse {
    public final String docId;
    public final String[] entities;
    public PositiveResponse(String result, String docId, String[] entities) {
        super(result);
        this.docId = docId;
        this.entities = entities;
    }
}
