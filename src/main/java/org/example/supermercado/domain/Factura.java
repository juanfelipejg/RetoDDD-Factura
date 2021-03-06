package org.example.supermercado.domain;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.entities.Sucursal;
import org.example.supermercado.domain.events.*;
import org.example.supermercado.domain.entities.Cliente;
import org.example.supermercado.domain.entities.Producto;
import org.example.supermercado.domain.values.*;

import java.util.List;
import java.util.Map;

public class Factura extends AggregateEvent<FacturaId> {

    protected Cliente cliente;
    protected Map<ProductoId,Producto> productos;
    protected Sucursal sucursal;
    protected Iva iva;
    protected Fecha fecha;
    protected Valor total;
    protected Valor subtotal;
    protected Valor descuento;
    protected Valor puntos;
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
        appendChange(new SubtotalCalculado(subtotal.incrementar(precio.value())));
    }

    public void agregarSucursal(SucursalId sucursalId, Ciudad ciudad, Telefono telefono, Direccion direccion ){
        appendChange(new SucursalAgregada(sucursalId,ciudad,telefono,direccion));

    }

    public void calcularTotal(Valor total){
        appendChange(new TotalCalculado(total)).apply();
    }

    public void calcularDescuento(Valor descuento){
        appendChange(new DescuentoCalculado(descuento)).apply();

    }

    public void eliminarProducto(ProductoId productoId){
        var producto = productos.get(productoId);
        appendChange(new ProductoEliminado(productoId)).apply();
        appendChange(new SubtotalCalculado(subtotal.reducir(producto.getPrecio().value())));

    }

    public void asignarPuntos(Valor puntos){
        appendChange(new PuntosAsignados(puntos)).apply();
    }

    public void generarFactura(){

    }

    public Cliente getCliente() {
        return cliente;
    }

    public Map<ProductoId,Producto> getProductos() {
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
