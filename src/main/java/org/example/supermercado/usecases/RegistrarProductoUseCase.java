package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.commands.AgregarProducto;

public class RegistrarProductoUseCase extends UseCase<RequestCommand<AgregarProducto>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<AgregarProducto> agregarProductoRequestCommand) {

        var command = agregarProductoRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        if(factura.getEstaGenerada()){
            throw new BusinessException(factura.identity().value(), "No puede agregar un producto a una factura generada");
        }

        try{
            factura.agregarProducto(command.getProductoId(), command.getNombre(), command.getPrecio(), command.getDescripcion());
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }

    }
}
