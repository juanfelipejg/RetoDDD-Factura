package org.example.supermercado.usecases.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import org.example.supermercado.domain.commands.CalcularDescuento;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.usecases.CalcularDescuentoUseCase;

import java.util.Map;

@CommandHandles
@CommandType(name = "supermercado.factura.calcularDescuento", aggregate = "factura")
public class CalcularDescuentoHandle extends UseCaseExecutor {

    private RequestCommand<CalcularDescuento> request;

    @Override
    public void run() {
        runUseCase(new CalcularDescuentoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {

        request = new RequestCommand<>(new CalcularDescuento(FacturaId.of(aggregateId())));

    }
}
