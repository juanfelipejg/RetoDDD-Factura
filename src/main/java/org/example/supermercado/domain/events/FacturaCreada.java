package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.FacturaId;

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
