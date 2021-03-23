package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Fecha;

public class FacturaCreada extends DomainEvent {

    private final FacturaId facturaId;
    private final Fecha fecha;


    public FacturaCreada(FacturaId facturaId, Fecha fecha) {
        super("factura.creada");
        this.facturaId = facturaId;
        this.fecha = fecha;
    }

    public FacturaId getFacturaId(){
        return facturaId;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
