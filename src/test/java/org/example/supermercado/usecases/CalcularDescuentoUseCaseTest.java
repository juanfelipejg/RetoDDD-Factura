package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.commands.CalcularDescuento;
import org.example.supermercado.domain.events.DescuentoCalculado;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.events.ProductoAgregado;
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
class CalcularDescuentoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void calcularDescuento(){

        var facturaId = FacturaId.of("xxx");
        var fecha = new Fecha("24/03/2021");
        var command = new CalcularDescuento(facturaId);
        var useCase = new CalcularDescuentoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId,fecha));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (DescuentoCalculado) events.get(0);

        Assertions.assertEquals(6,event.getDescuento().value());
    }

    @Test
    void errorCalcularDescuento(){

        var facturaId = FacturaId.of("xxx");
        var fecha = new Fecha("24/03/2021");
        var command = new CalcularDescuento(facturaId);
        var useCase = new CalcularDescuentoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStoredAlt(facturaId,fecha));
        useCase.addRepository(repository);

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .setIdentifyExecutor(facturaId.value())
                    .syncExecutor(useCase, new RequestCommand<>(command))
                    .orElseThrow();
        });

    }

    private List<DomainEvent> eventStoredAlt(FacturaId facturaId, Fecha fecha) {

        return List.of(
                new FacturaCreada(facturaId,fecha)
        );
    }


    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {

        var productoId = ProductoId.of("xxx");
        var nombreProducto = new Nombre("Arroz");
        var precio = new Valor(400000);
        var descripcion = new Descripcion("Arroz ROA x 2kg");

        return List.of(
                new FacturaCreada(facturaId,fecha),
                new ProductoAgregado(productoId,nombreProducto,precio,descripcion)
        );
    }

}