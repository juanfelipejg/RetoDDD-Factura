package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Valor;

public class TotalCalculado extends DomainEvent {

    private final Valor total;

    public TotalCalculado(Valor descuento) {
        super("total.calculado");
        this.total = descuento;
    }

    public Valor getTotal() {
        return total;
    }
}
