package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Cedula;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Nombre;
import org.example.supermercado.domain.values.Telefono;

public class ClienteRegistrado extends DomainEvent {

    private Nombre nombre;
    private Cedula cedula;
    private Telefono telefono;


    public ClienteRegistrado(Nombre nombre, Cedula cedula, Telefono telefono ) {
        super("cliente.registrado");
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Cedula getCedula() {
        return cedula;
    }

    public Telefono getTelefono() {
        return telefono;
    }
}
