package org.example.supermercado.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.supermercado.domain.commands.CrearFactura;
import org.example.supermercado.domain.values.Factura;

public class CrearFacturaUseCase extends UseCase<RequestCommand<CrearFactura>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CrearFactura> crearFacturaRequestCommand) {
        var command = crearFacturaRequestCommand.getCommand();

        var factura = new Factura(command.getFacturaId());

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
