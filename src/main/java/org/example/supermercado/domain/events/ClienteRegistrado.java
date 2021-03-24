package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.*;

public class ClienteRegistrado extends DomainEvent {

    private ClienteId clienteId;
    private Nombre nombre;
    private Cedula cedula;
    private Telefono telefono;


    public ClienteRegistrado(ClienteId clienteId, Nombre nombre, Cedula cedula, Telefono telefono ) {
        super("cliente.registrado");
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public ClienteId getClienteId() {
        return clienteId;
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
