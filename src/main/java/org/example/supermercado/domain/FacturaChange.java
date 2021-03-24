package org.example.supermercado.domain;

import co.com.sofka.domain.generic.EventChange;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.entities.Producto;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.ClienteRegistrado;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.events.ProductoAgregado;
import org.example.supermercado.domain.events.SucursalAgregada;

public class FacturaChange extends EventChange {

    public FacturaChange(Factura factura){

        apply((FacturaCreada event) -> {
            factura.fecha = event.getFecha();
        });

        apply((ClienteRegistrado event) -> {
            factura.cliente = new Cliente(event.getClienteId(), event.getNombre(), event.getCedula(), event.getTelefono());
        });

        apply((ProductoAgregado event) -> {
            factura.productos.add(new Producto(event.getProductoId(), event.getNombre(),
                    event.getPrecio(), event.getDescripcion()));
        });

        apply((SucursalAgregada event) -> {
            factura.sucursal = new Sucursal(event.getSucursalId(), event.getCiudad(), event.getTelefono(), event.getDireccion());
        });
    }
}
