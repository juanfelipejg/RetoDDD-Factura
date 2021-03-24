package org.example.supermercado.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.*;
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
    protected Boolean estaGenerada;

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

    public void agregarProducto(ProductoId productoId, Nombre nombre, Valor precio, Descripcion descripcion){
        appendChange(new ProductoAgregado(productoId, nombre, precio, descripcion));

    }

    public void agregarSucursal(SucursalId sucursalId, Ciudad ciudad, Telefono telefono, Direccion direccion ){
        appendChange(new SucursalAgregada(sucursalId,ciudad,telefono,direccion));

    }

    public void calcularSubtotal(){

    }

    public void calcularTotal(){

    }

    public void calcularDescuento(){

    }

    public void eliminarProducto(ProductoId productoId){
        appendChange(new ProductoEliminado(productoId)).apply();

    }

    public void generarFactura(){

    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public Iva getIva() {
        return iva;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public Valor getTotal() {
        return total;
    }

    public Valor getSubtotal() {
        return subtotal;
    }

    public Valor getDescuento() {
        return descuento;
    }

    public Boolean getEstaGenerada() {
        return estaGenerada;
    }
}
