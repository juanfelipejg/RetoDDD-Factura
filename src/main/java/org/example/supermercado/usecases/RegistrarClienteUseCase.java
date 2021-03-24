package org.example.supermercado.usecases;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.commands.RegistrarCliente;

public class RegistrarClienteUseCase extends UseCase<RequestCommand<RegistrarCliente>, ResponseEvents> {


    @Override
    public void executeUseCase(RequestCommand<RegistrarCliente> registrarClienteRequestCommand) {

        var command = registrarClienteRequestCommand.getCommand();

        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        factura.agregarDatosCliente(command.getClienteId(),command.getNombre(),command.getCedula(), command.getTelefono());

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
