package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.*;

public class AgregarSucursal implements Command {

    private final FacturaId facturaId;
    private final SucursalId sucursalId;
    private final Ciudad ciudad;
    private final Telefono telefono;
    private final Direccion direccion;

    public AgregarSucursal(FacturaId facturaId, SucursalId sucursalId, Ciudad ciudad, Telefono telefono, Direccion direccion) {
        this.facturaId = facturaId;
        this.sucursalId = sucursalId;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public FacturaId getFacturaId() {
        return facturaId;
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
