package org.example.supermercado.usecases;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.CrearFactura;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Fecha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CrearFacturaUseCaseTest {

    @Test
    void crearFactura(){
        var facturaId = FacturaId.of("xxx");
        var fecha = new Fecha("23/03/2021");
        var command = new CrearFactura(facturaId, fecha);
        var useCase = new CrearFacturaUseCase();

        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var event = (FacturaCreada) events.get(0);

        Assertions.assertTrue(Objects.nonNull(event.getFacturaId().value()));
        Assertions.assertEquals("23/03/2021", event.getFecha().value());
        

    }

}