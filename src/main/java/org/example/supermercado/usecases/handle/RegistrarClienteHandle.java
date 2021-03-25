package org.example.supermercado.usecases.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.CrearFactura;
import org.example.supermercado.domain.commands.RegistrarCliente;
import org.example.supermercado.domain.values.*;
import org.example.supermercado.usecases.RegistrarClienteUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "supermercado.factura.registrarCliente", aggregate = "factura")
public class RegistrarClienteHandle extends UseCaseExecutor {

    private RequestCommand<RegistrarCliente> request;

    @Override
    public void run() {
        runUseCase(new RegistrarClienteUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {

        var clienteId = ClienteId.of(args.get("clienteId"));
        var nombre = Objects.requireNonNull(new Nombre(args.get("nombre")));
        var cedula = Objects.requireNonNull(new Cedula(args.get("cedula")));
        var telefono = Objects.requireNonNull(new Telefono(args.get("telefono")));
        request = new RequestCommand<>(new RegistrarCliente(FacturaId.of(aggregateId()),clienteId,
                nombre,cedula,telefono));

    }

}
