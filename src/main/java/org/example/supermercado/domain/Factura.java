package org.example.supermercado.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.ClienteRegistrado;
import org.example.supermercado.domain.events.FacturaCreada;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.entities.Producto;
import org.example.supermercado.domain.values.*;

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

    private Factura(FacturaId facturaId){
        super(facturaId);
        subscribe(new FacturaChange(this));
    }

    public static Factura from(FacturaId facturaId, List<DomainEvent> events) {
        var factura = new Factura(facturaId);
        events.forEach(factura::applyEvent);
        return factura;
    }

    public void agregarDatosCliente(ClienteId clienteId, Nombre nombre, Cedula cedula,Telefono telefono){
        appendChange(new ClienteRegistrado(clienteId, nombre, cedula, telefono));
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