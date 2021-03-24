package org.example.supermercado.domain;

import co.com.sofka.domain.generic.EventChange;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.entities.Producto;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.*;

import java.util.HashMap;

public class FacturaChange extends EventChange {

    public FacturaChange(Factura factura){

        apply((FacturaCreada event) -> {
            factura.fecha = event.getFecha();
            factura.estaGenerada = Boolean.FALSE;
            factura.productos = new HashMap<>();
        });

        apply((ClienteRegistrado event) -> {
            factura.cliente = new Cliente(event.getClienteId(), event.getNombre(), event.getCedula(), event.getTelefono());
        });

        apply((ProductoAgregado event) -> {
            var producto = new Producto(event.getProductoId(), event.getNombre(),
                    event.getPrecio(), event.getDescripcion());
            factura.productos.put(event.getProductoId(),producto);
        });

        apply((SucursalAgregada event) -> {
            factura.sucursal = new Sucursal(event.getSucursalId(), event.getCiudad(), event.getTelefono(), event.getDireccion());
        });

        apply((ProductoEliminado event) -> {
            factura.productos.remove(event.getProductoId());
        });
    }
}
