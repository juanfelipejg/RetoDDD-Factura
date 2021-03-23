package org.example.supermercado.domain.entities;

import co.com.sofka.domain.generic.Entity;
import org.example.supermercado.domain.values.Ciudad;
import org.example.supermercado.domain.values.Direccion;
import org.example.supermercado.domain.values.SucursalId;
import org.example.supermercado.domain.values.Telefono;

public class Sucursal extends Entity<SucursalId> {

    private final Ciudad ciudad;
    private final Telefono telefono;
    private final Direccion direccion;


    public Sucursal(SucursalId sucursalId, Ciudad ciudad, Telefono telefono, Direccion direccion) {
        super(sucursalId);
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.direccion = direccion;
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
