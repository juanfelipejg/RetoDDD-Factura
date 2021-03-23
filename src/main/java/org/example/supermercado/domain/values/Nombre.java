package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Nombre implements ValueObject<String> {

    private final String value;

    public Nombre(String nombre) {
        this.value = Objects.requireNonNull(nombre);
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede esta vacio");
        }
    }

    @Override
    public String value() {
        return value;
    }
}
