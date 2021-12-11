package com.kernelpanic.happythoughts.business.comoestas;

public class NegativeResponse extends AnalisisResponse {
    public final String frase;
    public final String recuerdo;
    public NegativeResponse(String result, String frase, String recuerdo) {
        super(result);
        this.frase = frase;
        this.recuerdo = recuerdo;
    }
}
