package org.example.supermercado.domain.entities;

import co.com.sofka.domain.generic.Entity;
import org.example.supermercado.domain.values.Cedula;
import org.example.supermercado.domain.values.ClienteId;
import org.example.supermercado.domain.values.Nombre;
import org.example.supermercado.domain.values.Telefono;

public class Cliente extends Entity<ClienteId> {

    private final Nombre nombre;
    private final Cedula cedula;
    private final Telefono telefono;


    public Cliente(ClienteId clienteId, Nombre nombre, Cedula cedula, Telefono telefono) {
        super(clienteId);
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
