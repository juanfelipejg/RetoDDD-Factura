package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.commands.CalcularDescuento;
import org.example.supermercado.domain.values.Valor;

public class CalcularDescuentoUseCase extends UseCase<RequestCommand<CalcularDescuento>, ResponseEvents> {

    @Override
    public void executeUseCase(RequestCommand<CalcularDescuento> calcularDescuentoRequestCommand) {

        var command = calcularDescuentoRequestCommand.getCommand();
        var factura = Factura.from(command.getFacturaId(), retrieveEvents());

        var subtotal = factura.getSubtotal().value();

        if(subtotal == 0){
            throw new BusinessException(factura.identity().value(), "No se puede aplicar descuento a un subtotal de 0");
        }

        var descuento = new Valor(3 * (subtotal/200000));
        try{
            if(descuento.value() >= 15){
                factura.calcularDescuento(new Valor(0));
            }
            else{
                factura.calcularDescuento(descuento);
            }
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }


    }
}
