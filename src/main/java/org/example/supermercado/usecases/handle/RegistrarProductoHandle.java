package org.example.supermercado.usecases.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.AgregarProducto;
import org.example.supermercado.domain.values.*;
import org.example.supermercado.usecases.RegistrarProductoUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "supermercado.factura.registrarProducto", aggregate = "factura")
public class RegistrarProductoHandle extends UseCaseExecutor {

    private RequestCommand<AgregarProducto> request;


    @Override
    public void run() {
        runUseCase(new RegistrarProductoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {

        var productoId = Objects.requireNonNull(ProductoId.of(args.get("productoId")));
        var nombre = Objects.requireNonNull(new Nombre(args.get("nombre")));
        var precio = Objects.requireNonNull(new Valor(Integer.parseInt(args.get("precio"))));
        var descripcion = Objects.requireNonNull(new Descripcion(args.get("descripcion")));


        request = new RequestCommand<>(new AgregarProducto(FacturaId.of(aggregateId()),productoId,
                nombre,precio,descripcion));

    }
}
