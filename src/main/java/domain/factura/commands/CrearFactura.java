package domain.factura.commands;

import co.com.sofka.domain.generic.Command;
import domain.factura.values.FacturaId;

public class CrearFactura implements Command {

    private final FacturaId facturaId;

    public CrearFactura(FacturaId facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaId getFacturaId() {
        return facturaId;
    }
}
