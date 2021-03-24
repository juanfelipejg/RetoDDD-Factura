package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.*;

public class EliminarProducto implements Command {

    private final FacturaId facturaId;
    private final ProductoId productoId;

    public EliminarProducto(FacturaId facturaId, ProductoId productoId) {
        this.facturaId = facturaId;
        this.productoId = productoId;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public ProductoId getProductoId() {
        return productoId;
    }
}
