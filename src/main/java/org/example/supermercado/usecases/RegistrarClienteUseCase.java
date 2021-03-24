package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
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

        if(factura.getEstaGenerada()){
            throw new BusinessException(factura.identity().value(), "No puede agregar un cliente a una factura generada");
        }

        try{
            factura.agregarDatosCliente(command.getClienteId(),command.getNombre(),command.getCedula(), command.getTelefono());
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch (RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }

    }
}
