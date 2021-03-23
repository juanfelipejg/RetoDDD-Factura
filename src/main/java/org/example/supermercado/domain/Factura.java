package org.example.supermercado.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.entities.Producto;
import org.example.supermercado.domain.values.FacturaId;
import org.example.supermercado.domain.values.Fecha;
import org.example.supermercado.domain.values.Iva;
import org.example.supermercado.domain.values.Valor;

import java.util.List;

public class Factura extends AggregateEvent<FacturaId> {

    protected Cliente cliente;
    protected List<Producto> productos;
    protected Sucursal sucursal;
    protected Iva iva;
    protected Fecha fecha;
    protected Valor total;
    protected Valor subtotal;
    protected Valor descuento;

    public Factura(FacturaId facturaId, Fecha fecha){
        super(facturaId);
        appendChange(new FacturaCreada(facturaId,fecha)).apply();
    }

    public void agregarDatosCliente(){

    }

    public void agregarProducto(){

    }

    public void agregarSucursal(){

    }

    public void calcularSubtotal(){

    }

    public void calcularTotal(){

    }

    public void calcularDescuento(){

    }

    public void eliminarProducto(){

    }

    public void generarFactura(){

    }

}
