package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Fecha;

public class CrearFactura implements Command {

    private final FacturaId facturaId;
    private final Fecha fecha;

    public CrearFactura(FacturaId facturaId, Fecha fecha) {
        this.facturaId = facturaId;
        this.fecha = fecha;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
