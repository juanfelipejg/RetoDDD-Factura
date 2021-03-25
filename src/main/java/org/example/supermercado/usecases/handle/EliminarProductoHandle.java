package org.example.supermercado.usecases.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.EliminarProducto;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.ProductoId;
import org.example.supermercado.usecases.EliminarProductoUseCase;

import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "supermercado.factura.eliminarProducto", aggregate = "factura")
public class EliminarProductoHandle extends UseCaseExecutor {

    private RequestCommand<EliminarProducto> request;

    @Override
    public void run() {
        runUseCase(new EliminarProductoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {

        var productoId = Objects.requireNonNull(ProductoId.of(args.get("productoId")));
        request = new RequestCommand<>(new EliminarProducto(FacturaId.of(aggregateId()),productoId));

    }
}
