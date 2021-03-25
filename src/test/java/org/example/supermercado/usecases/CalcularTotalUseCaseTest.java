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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalcularTotalUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void calcularTotal(){

        var facturaId = FacturaId.of("ddd");
        var fecha = new Fecha("24/03/2021");
        var descuento = new Valor(6);
        var event = new DescuentoCalculado(descuento);
        event.setAggregateRootId("xxx");

        when(repository.getEventsBy(event.aggregateRootId())).thenReturn(eventStored(facturaId,fecha,descuento));

        var useCase = new CalcularTotalUseCase();
        useCase.addRepository(repository);


        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(event.aggregateRootId())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var totalCalculado = (TotalCalculado) events.get(0);

        Assertions.assertEquals(451200, totalCalculado.getTotal().value());

    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha, Valor descuento) {

        var productoId = ProductoId.of("xxx");
        var nombreProducto = new Nombre("Arroz");
        var precio = new Valor(400000);
        var descripcion = new Descripcion("Arroz ROA x 2kg");

        return List.of(
                new FacturaCreada(facturaId,fecha),
                new ProductoAgregado(productoId,nombreProducto,precio,descripcion),
                new SubtotalCalculado(new Valor(400000)),
                new DescuentoCalculado(descuento)
        );
    }

}