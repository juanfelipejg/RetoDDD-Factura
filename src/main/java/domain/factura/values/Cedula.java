package domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Cedula implements ValueObject<String> {

    private final String value;

    public Cedula(String cedula) {
        this.value = Objects.requireNonNull(cedula);
        if(cedula.isBlank()){
            throw new IllegalArgumentException("La cedula no puede estar vacia");
        }
    }

    @Override
    public String value() {
        return value;
    }
}
