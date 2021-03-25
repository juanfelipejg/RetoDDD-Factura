package org.example.supermercado.usecases;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.commands.EliminarProducto;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.events.ProductoAgregado;
import org.example.supermercado.domain.events.ProductoEliminado;
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
class EliminarProductoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void eliminarProducto(){

        var facturaId = FacturaId.of("iii");
        var fecha = new Fecha("24/03/2021");
        var productId = ProductoId.of("vvv");
        var command = new EliminarProducto(facturaId, productId);
        var useCase = new EliminarProductoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId,fecha,productId));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ProductoEliminado) events.get(0);

        Assertions.assertEquals("vvv",event.getProductoId().value());

    }



    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha,ProductoId productoId) {

        var nombreProducto = new Nombre("Arroz");
        var precio = new Valor(7500);
        var descripcion = new Descripcion("Arroz ROA x 2kg");

        return List.of(
                new FacturaCreada(facturaId,fecha),
                new ProductoAgregado(productoId,nombreProducto,precio,descripcion)
        );
    }



}