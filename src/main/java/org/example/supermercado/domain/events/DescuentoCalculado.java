package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Valor;

public class DescuentoCalculado extends DomainEvent {

    private final Valor descuento;

    public DescuentoCalculado(Valor descuento) {
        super("descuento.calculado");
        this.descuento = descuento;
    }

    public Valor getDescuento() {
        return descuento;
    }
}
