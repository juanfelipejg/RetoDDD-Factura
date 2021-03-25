package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Valor;

public class DescuentoCalculado extends DomainEvent {

    private final Valor descuento;

    public DescuentoCalculado(Valor descuento) {
        super("supermercado.factura.descuentoCalculado");
        this.descuento = descuento;
    }

    public Valor getDescuento() {
        return descuento;
    }
}
