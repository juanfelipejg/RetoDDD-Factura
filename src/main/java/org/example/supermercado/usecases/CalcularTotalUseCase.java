package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.events.DescuentoCalculado;
import org.example.supermercado.domain.values.FacturaId;

public class CalcularTotalUseCase extends UseCase<TriggeredEvent<DescuentoCalculado>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<DescuentoCalculado> descuentoCalculadoTriggeredEvent) {

        var event = descuentoCalculadoTriggeredEvent.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());

        try{
            factura.calcularTotal(event.getDescuento());
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }


    }
}
