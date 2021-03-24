package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.Descripcion;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Nombre;
import org.example.supermercado.domain.values.Valor;

public class AgregarProducto implements Command {

    private final FacturaId facturaId;
    private final Nombre nombre;
    private final Valor precio;
    private final Descripcion descripcion;

    public AgregarProducto(FacturaId facturaId, Nombre nombre, Valor precio, Descripcion descripcion) {
        this.facturaId = facturaId;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Valor getPrecio() {
        return precio;
    }

    public Descripcion getDescripcion() {
        return descripcion;
    }
}
