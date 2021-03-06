package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.Identity;

public class FacturaId extends Identity {

    private FacturaId(String uid){
        super(uid);
    }

    public FacturaId(){
    }

    public static FacturaId of(String uid){
        return new FacturaId(uid);
    }
}
