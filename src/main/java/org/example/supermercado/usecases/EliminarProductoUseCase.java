package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.commands.EliminarProducto;


public class EliminarProductoUseCase extends UseCase<RequestCommand<EliminarProducto>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<EliminarProducto> eliminarProductoRequestCommand) {

        var command = eliminarProductoRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        if(factura.getProductos().size() < 1) {
            throw new BusinessException(factura.identity().value(), "Debe de existir al menos un producto");
        }

        try{
            factura.eliminarProducto(command.getProductoId());
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }

    }
}
