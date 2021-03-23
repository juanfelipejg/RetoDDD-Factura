package domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

public class Direccion implements ValueObject<String> {

    private final String value;

    public Direccion(String direccion) {
        this.value = direccion;
    }

    @Override
    public String value() {
        return value;
    }
}
