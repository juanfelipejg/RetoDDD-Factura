package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.*;

public class RegistrarCliente implements Command {

    private FacturaId facturaId;
    private ClienteId clienteId;
    private Nombre nombre;
    private Cedula cedula;
    private Telefono telefono;

    public RegistrarCliente(FacturaId facturaId, ClienteId clienteId, Nombre nombre, Cedula cedula, Telefono telefono) {
        this.facturaId = facturaId;
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public FacturaId getFacturaId() {
        return facturaId;
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
