package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import org.example.supermercado.domain.Factura;
import org.example.supermercado.domain.events.TotalCalculado;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Valor;

public class CalcularPuntosUseCase extends UseCase<TriggeredEvent<TotalCalculado>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<TotalCalculado> totalCalculadoTriggeredEvent) {

        var event = totalCalculadoTriggeredEvent.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());

        int calculoPuntos = event.getTotal().value()/100000;
        var puntos = new Valor(calculoPuntos * 10);

        if(factura.getTotal().value() == 0){
            throw new BusinessException(factura.identity().value(), "No se puede asignar puntos a un total de 0");
        }

        try{
            factura.asignarPuntos(puntos);
            emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
        }catch(RuntimeException e){
            emit().onError(new BusinessException(factura.identity().value(), e.getMessage()));
        }


    }
}
