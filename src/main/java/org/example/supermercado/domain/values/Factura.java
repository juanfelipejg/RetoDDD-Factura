package org.example.supermercado.domain.values;

import co.com.sofka.domain.generic.AggregateEvent;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.entities.Producto;

import java.util.List;

public class Factura extends AggregateEvent<FacturaId> {

    private Cliente cliente;
    private List<Producto> productos;
    private Sucursal sucursal;
    private Iva iva;
    private Fecha fecha;
    private Valor total;
    private Valor subtotal;
    private Valor descuento;

    public Factura(FacturaId facturaId){
        super(facturaId);
        appendChange(new FacturaCreada(facturaId)).apply();
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
