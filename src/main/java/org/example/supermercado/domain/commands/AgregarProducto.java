package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.*;

public class AgregarProducto implements Command {

    private final FacturaId facturaId;
    private final ProductoId productoId;
    private final Nombre nombre;
    private final Valor precio;
    private final Descripcion descripcion;

    public AgregarProducto(FacturaId facturaId, ProductoId productoId, Nombre nombre, Valor precio, Descripcion descripcion) {
        this.productoId = productoId;
        this.facturaId = facturaId;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public ProductoId getProductoId() {
        return productoId;
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
