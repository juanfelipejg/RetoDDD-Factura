package domain.factura.values;

import co.com.sofka.domain.generic.Entity;

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
