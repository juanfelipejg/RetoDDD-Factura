package org.example.supermercado.domain.commands;

import co.com.sofka.domain.generic.Command;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Valor;

public class CalcularDescuento implements Command {

    private final FacturaId facturaId;

    public CalcularDescuento(FacturaId facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }
}
