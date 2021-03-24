package org.example.supermercado.domain;

import co.com.sofka.domain.generic.EventChange;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.events.ClienteRegistrado;
import org.example.supermercado.domain.events.FacturaCreada;

public class FacturaChange extends EventChange {

    public FacturaChange(Factura factura){

        apply((FacturaCreada event) -> {
            factura.fecha = event.getFecha();
        });

        apply((ClienteRegistrado event) -> {
            factura.cliente = new Cliente(event.getClienteId(), event.getNombre(), event.getCedula(), event.getTelefono());
        });
    }
}