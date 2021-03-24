package org.example.supermercado.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.commands.RegistrarCliente;
import org.example.supermercado.domain.events.ClienteRegistrado;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class RegistrarClienteUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void registrarCliente(){

        var facturaId = FacturaId.of("xxx");
        var fecha = new Fecha("23/03/2021");
        var clienteId = ClienteId.of("yyy");
        var nombre = new Nombre("Juan");
        var cedula = new Cedula("123");
        var telefono = new Telefono("2819354");
        var command = new RegistrarCliente(facturaId,clienteId,nombre,cedula,telefono);
        var useCase = new RegistrarClienteUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId,fecha));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (ClienteRegistrado) events.get(0);

        Assertions.assertEquals("yyy", event.getClienteId().value());
        Assertions.assertEquals("Juan", event.getNombre().value());
        Assertions.assertEquals("123", event.getCedula().value());
        Assertions.assertEquals("2819354", event.getTelefono().value());

    }

    private List<DomainEvent> eventStored(FacturaId facturaId, Fecha fecha) {
        return List.of(
            new FacturaCreada(facturaId,fecha)
        );
    }

}