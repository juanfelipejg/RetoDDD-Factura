package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Valor;

public class PuntosAsignados extends DomainEvent {

    private final Valor puntos;

    public PuntosAsignados(Valor puntos) {
        super("puntos.asignados");
        this.puntos = puntos;
    }

    public Valor getPuntos() {
        return puntos;
    }
}
