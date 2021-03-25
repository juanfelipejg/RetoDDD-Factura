package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;

public class SubtotalCalculado extends DomainEvent {

     public SubtotalCalculado() {
        super("subtotal.calculado");
    }
}
