package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Valor implements ValueObject<Integer> {

    private final Integer value;

    public Valor(Integer valor) {
        this.value = Objects.requireNonNull(valor);
    }

    @Override
    public Integer value() {
        return value;
    }
}
