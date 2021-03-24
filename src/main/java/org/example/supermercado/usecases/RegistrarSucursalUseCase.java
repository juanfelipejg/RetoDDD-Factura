package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.commands.AgregarSucursal;

public class RegistrarSucursalUseCase extends UseCase<RequestCommand<AgregarSucursal>,ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AgregarSucursal> agregarSucursalRequestCommand) {

        var command = agregarSucursalRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        if(factura.getEstaGenerada()){
            throw new BusinessException(factura.identity().value(), "No puede agregar una Sucursal a una factura generada");
        }

        try{
            factura.agregarSucursal(command.getSucursalId(),command.getCiudad(),command.getTelefono(),command.getDireccion());
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch (RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }

    }
}
