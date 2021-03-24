package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.Cedula;
import org.example.supermercado.domain.values.Nombre;
import org.example.supermercado.domain.values.Telefono;

public class RegistrarCliente implements Command {

    private Nombre nombre;
    private Cedula cedula;
    private Telefono telefono;

    public RegistrarCliente(Nombre nombre, Cedula cedula, Telefono telefono) {
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
