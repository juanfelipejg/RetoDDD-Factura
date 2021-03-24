package org.example.supermercado.usecases;


import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.commands.AgregarSucursal;
import org.example.supermercado.domain.events.ClienteRegistrado;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.events.ProductoAgregado;
import org.example.supermercado.domain.events.SucursalAgregada;
import org.example.supermercado.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegistrarSucursalUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void registrarSucursal(){

        var facturaId = FacturaId.of("xxx");
        var fecha = new Fecha("24/03/2021");
        var sucursalId = SucursalId.of("yyy");
        var ciudad = new Ciudad("Medellín");
        var telefono = new Telefono("281");
        var direccion = new Direccion("CR 70 CL 50 -37");

        var command = new AgregarSucursal(facturaId,sucursalId,ciudad,telefono,direccion);
        var useCase = new RegistrarSucursalUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId,fecha));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (SucursalAgregada)events.get(0);

        Assertions.assertEquals("yyy", event.getSucursalId().value());
        Assertions.assertEquals("Medellín", event.getCiudad().value());
        Assertions.assertEquals("281", event.getTelefono().value());
        Assertions.assertEquals("CR 70 CL 50 -37", event.getDireccion().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {

        return List.of(
                new FacturaCreada(facturaId,fecha)
        );
    }

}