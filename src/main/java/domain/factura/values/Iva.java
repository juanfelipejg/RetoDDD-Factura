package domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Iva implements ValueObject<Float> {

    private final Float iva;

    public Iva (Float iva){
        this.iva = Objects.requireNonNull(iva);
    }

    @Override
    public Float value() {
        return null;
    }
}
