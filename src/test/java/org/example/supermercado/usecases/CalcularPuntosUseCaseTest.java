package org.example.supermercado.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.events.*;
import org.example.supermercado.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalcularPuntosUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void calcularPuntos(){

        var facturaId = FacturaId.of("eee");
        var fecha = new Fecha("25/03/2021");
        var descuento = new Valor(4);
        var total = new Valor(400000);
        var event = new TotalCalculado(total);
        var useCase = new CalcularPuntosUseCase();

        event.setAggregateRootId("xxx");

        when(repository.getEventsBy(event.aggregateRootId())).thenReturn(eventStored(facturaId,fecha,descuento,total));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var puntosAsignados = (PuntosAsignados) events.get(0);

        Assertions.assertEquals(40,puntosAsignados.getPuntos().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha, Valor descuento, Valor total) {

        var productoId = ProductoId.of("xxx");
        var nombreProducto = new Nombre("Arroz");
        var precio = new Valor(400000);
        var descripcion = new Descripcion("Arroz ROA x 2kg");

        return List.of(
                new FacturaCreada(facturaId,fecha),
                new ProductoAgregado(productoId,nombreProducto,precio,descripcion),
                new DescuentoCalculado(descuento),
                new TotalCalculado(total)
        );
    }

}