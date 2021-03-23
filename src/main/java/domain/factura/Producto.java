package domain.factura;

import co.com.sofka.domain.generic.Entity;
import domain.factura.values.Descripcion;
import domain.factura.values.Nombre;
import domain.factura.values.ProductoId;
import domain.factura.values.Valor;

public class Producto extends Entity<ProductoId> {

    private final Nombre nombre;
    private final Valor precio;
    private final Descripcion descripcion;


    public Producto(ProductoId productoId, Nombre nombre, Valor precio, Descripcion descripcion) {
        super(productoId);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Valor getPrecio() {
        return precio;
    }

    public Descripcion getDescripcion() {
        return descripcion;
    }
}
