package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Telefono implements ValueObject<String> {

    private final String value;

    public Telefono(String telefono) {
        this.value = Objects.requireNonNull(telefono);
        if(telefono.isBlank()){
            throw new IllegalArgumentException("El telefono no debe estar vacio");
        }
    }

    @Override
    public String value() {
        return value;
    }
}
