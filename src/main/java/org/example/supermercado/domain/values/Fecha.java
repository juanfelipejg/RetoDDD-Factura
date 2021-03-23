package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Fecha implements ValueObject<String> {

    private final String value;

    public Fecha(String value) {
        this.value = Objects.requireNonNull(value);
        if(value.isBlank()){
            throw new IllegalArgumentException("La fecha no debe de estar vacía");
        }
    }

    @Override
    public String value() {
        return value;
    }
}
