package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Valor;

public class SubtotalCalculado extends DomainEvent {

    private final Valor subtotal;

    public SubtotalCalculado(Valor subtotal) {
        super("supermercado.factura.subtotalCalculado");
        this.subtotal = subtotal;
    }

    public Valor getSubtotal(){
        return subtotal;
    }
}
