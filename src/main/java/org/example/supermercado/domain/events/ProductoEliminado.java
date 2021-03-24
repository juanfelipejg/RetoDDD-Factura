package org.example.supermercado.domain.events;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.values.ProductoId;

public class ProductoEliminado extends DomainEvent {

    private final ProductoId productoId;

    public ProductoEliminado(ProductoId productoId) {
        super("producto.eliminado");
        this.productoId = productoId;
    }
    
    public ProductoId getProductoId() {
        return productoId;
    }
}
