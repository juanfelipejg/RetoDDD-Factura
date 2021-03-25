package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.events.DescuentoCalculado;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Valor;

public class CalcularTotalUseCase extends UseCase<TriggeredEvent<DescuentoCalculado>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<DescuentoCalculado> descuentoCalculadoTriggeredEvent) {

        var event = descuentoCalculadoTriggeredEvent.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());

        float calculo = factura.getSubtotal().value() + (factura.getSubtotal().value() * factura.getIva().value());
        var total = new Valor((int) calculo) ;

        try{
            factura.calcularTotal(total);
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }


    }
}
