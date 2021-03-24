package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Ciudad implements ValueObject<String> {

    private final String value;

    public Ciudad(String ciudad) {
        this.value = ciudad;
    }

    @Override
    public String value() {
        return value;
    }
}
