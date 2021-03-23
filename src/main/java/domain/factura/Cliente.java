package domain.factura;

import co.com.sofka.domain.generic.Entity;
import domain.factura.values.Cedula;
import domain.factura.values.ClienteId;
import domain.factura.values.Nombre;
import domain.factura.values.Telefono;

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
