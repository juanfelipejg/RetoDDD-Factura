package org.example.supermercado.usecases.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.CrearFactura;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Fecha;
import org.example.supermercado.usecases.CrearFacturaUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "supermercado.factura.crear", aggregate = "factura")
public class CrearFacturaHandle extends UseCaseExecutor {

    private RequestCommand<CrearFactura> request;


    @Override
    public void run() {
        runUseCase(new CrearFacturaUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {

        var fecha = Objects.requireNonNull(new Fecha(args.get("fecha")));
        request = new RequestCommand<>(new CrearFactura(FacturaId.of(aggregateId()),fecha));

    }
}
