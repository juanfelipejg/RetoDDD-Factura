package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Ciudad;
import org.example.supermercado.domain.values.Direccion;
import org.example.supermercado.domain.values.SucursalId;
import org.example.supermercado.domain.values.Telefono;

public class SucursalAgregada extends DomainEvent {

    private final SucursalId sucursalId;
    private final Ciudad ciudad;
    private final Telefono telefono;
    private final Direccion direccion;


    public SucursalAgregada(SucursalId sucursalId, Ciudad ciudad, Telefono telefono, Direccion direccion) {
        super("sucursal.agregada");
        this.sucursalId = sucursalId;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public SucursalId getSucursalId() {
        return sucursalId;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
