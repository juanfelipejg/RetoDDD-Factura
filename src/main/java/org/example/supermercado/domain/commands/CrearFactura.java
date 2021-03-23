package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.FacturaId;

public class CrearFactura implements Command {

    private final FacturaId facturaId;

    public CrearFactura(FacturaId facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }
}
