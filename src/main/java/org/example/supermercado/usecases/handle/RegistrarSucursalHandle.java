package org.example.supermercado.usecases.handle;

import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.AgregarSucursal;
import org.example.supermercado.domain.values.*;
import org.example.supermercado.usecases.RegistrarSucursalUseCase;

import java.util.Map;
import java.util.Objects;

public class RegistrarSucursalHandle extends UseCaseExecutor {

    private RequestCommand<AgregarSucursal> request;

    @Override
    public void run() {
        runUseCase(new RegistrarSucursalUseCase(),request);
    }

    @Override
    public void accept(Map<String, String> args) {

        var sucursalId = Objects.requireNonNull(SucursalId.of(args.get("sucursalId")));
        var ciudad = Objects.requireNonNull(new Ciudad(args.get("ciudad")));
        var telefono = Objects.requireNonNull(new Telefono(args.get("telefono")));
        var direccion = Objects.requireNonNull(new Direccion(args.get("direccion")));
        request = new RequestCommand<>(new AgregarSucursal(FacturaId.of(aggregateId()),sucursalId,
                ciudad,telefono,direccion));

    }
}
