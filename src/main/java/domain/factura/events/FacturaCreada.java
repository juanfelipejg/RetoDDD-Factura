package domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;
import domain.factura.values.FacturaId;

public class FacturaCreada extends DomainEvent {

    private final FacturaId facturaId;


    public FacturaCreada(FacturaId facturaId) {
        super("factura.creada");
        this.facturaId = facturaId;
    }

    public FacturaId getFacturaId(){
        return facturaId;
    }
}
