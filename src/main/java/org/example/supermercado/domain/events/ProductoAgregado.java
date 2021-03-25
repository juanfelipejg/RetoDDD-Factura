package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.Descripcion;
import org.example.supermercado.domain.values.Nombre;
import org.example.supermercado.domain.values.ProductoId;
import org.example.supermercado.domain.values.Valor;

public class ProductoAgregado extends DomainEvent {

    private final ProductoId productoId;
    private final Nombre nombre;
    private final Valor precio;
    private final Descripcion descripcion;


    public ProductoAgregado(ProductoId productoId, Nombre nombre, Valor precio, Descripcion descripcion) {
        super("supermercado.factura.productoAgregado");
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public ProductoId getProductoId() {
        return productoId;
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
