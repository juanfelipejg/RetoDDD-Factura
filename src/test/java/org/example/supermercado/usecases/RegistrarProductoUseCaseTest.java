package org.example.supermercado.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.commands.AgregarProducto;
import org.example.supermercado.domain.events.ClienteRegistrado;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.events.ProductoAgregado;
import org.example.supermercado.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrarProductoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void registrarProducto(){

        var facturaId = FacturaId.of("xxx");
        var fecha = new Fecha("24/03/2021");
        var productoId = ProductoId.of("yyy");
        var nombreProducto = new Nombre("Arroz");
        var precio = new Valor(7500);
        var descripcion = new Descripcion("Arroz ROA x 2kg");
        var command = new AgregarProducto(facturaId,productoId,nombreProducto,precio,descripcion);
        var useCase = new RegistrarProductoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId,fecha));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ProductoAgregado) events.get(0);

        Assertions.assertEquals("xxx", event.aggregateRootId());
        Assertions.assertEquals("Arroz", event.getNombre().value());
        Assertions.assertEquals(7500, event.getPrecio().value());
        Assertions.assertEquals("Arroz ROA x 2kg", event.getDescripcion().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {

        return List.of(
                new FacturaCreada(facturaId,fecha)
        );
    }

}