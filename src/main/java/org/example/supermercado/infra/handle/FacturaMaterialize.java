package org.example.supermercado.infra.handle;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.supermercado.domain.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class FacturaMaterialize {

    private static final String COLLECTION_NAME = "facturas";
    private static final Logger logger = Logger.getLogger(FacturaMaterialize.class.getName());

    @Autowired
    @Qualifier("mongoTemplateQueries")
    private MongoTemplate mongoTemplate;

    @Async
    @EventListener
    public void handleEventFacturaCreada(FacturaCreada facturaCreada) {
        logger.info("****** Handle event facturaCreada");
        Map<String, Object> data = new HashMap<>();
        data.put("_id", facturaCreada.getFacturaId().value());
        data.put("fecha", facturaCreada.getFecha().value());
        data.put("estaGenerada", false);
        mongoTemplate.save(data, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventClienteRegistrado(ClienteRegistrado clienteRegistrado) {
        logger.info("****** Handle event clienteRegistrado");
        Update update = new Update();
        var id = clienteRegistrado.getClienteId().value();
        update.set("cliente." + id + ".nombre", clienteRegistrado.getNombre().value());
        update.set("cliente." + id + ".cedula", clienteRegistrado.getCedula().value());
        update.set("cliente." + id + ".telefono", clienteRegistrado.getTelefono().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(clienteRegistrado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventSucursalAgregada(SucursalAgregada sucursalAgregada) {
        logger.info("****** Handle event sucursalAgregada");
        Update update = new Update();
        var id = sucursalAgregada.getSucursalId().value();
        update.set("sucursal." + id + ".ciudad", sucursalAgregada.getCiudad().value());
        update.set("sucursal." + id + ".telefono", sucursalAgregada.getTelefono().value());
        update.set("sucursal." + id + ".direccion", sucursalAgregada.getDireccion().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(sucursalAgregada), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventAgregarProducto(ProductoAgregado productoAgregado) {
        logger.info("****** Handle event productoAgregado");
        Update update = new Update();
        var id = productoAgregado.getProductoId().value();
        update.set("producto." + id + ".nombre", productoAgregado.getNombre().value());
        update.set("producto." + id + ".precio", productoAgregado.getPrecio().value());
        update.set("producto." + id + ".descripción", productoAgregado.getDescripcion().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(productoAgregado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventEliminarProducto(ProductoEliminado productoEliminado) {
        logger.info("****** Handle event productoEliminado");
        Update update = new Update();
        var id = productoEliminado.getProductoId().value();
        update.unset("producto." + id);
        mongoTemplate.updateFirst(getFilterByAggregateId(productoEliminado), update, COLLECTION_NAME);
    }


    @Async
    @EventListener
    public void handleEventSubtotalCalculado(SubtotalCalculado subtotalCalculado) {
        logger.info("****** Handle event subtotalCalculado");
        Update update = new Update();
        update.set("subtotal" , subtotalCalculado.getSubtotal().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(subtotalCalculado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventDescuentoCalculado(DescuentoCalculado descuentoCalculado) {
        logger.info("****** Handle event descuentoCalculado");
        Update update = new Update();
        update.set("descuento" , descuentoCalculado.getDescuento().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(descuentoCalculado), update, COLLECTION_NAME);
    }

    @Async
    @EventListener
    public void handleEventTotalCalculado(TotalCalculado totalCalculado) {
        logger.info("****** Handle event totalCalculado");
        Update update = new Update();
        update.set("total" , totalCalculado.getTotal().value());
        mongoTemplate.updateFirst(getFilterByAggregateId(totalCalculado), update, COLLECTION_NAME);
    }


    private Query getFilterByAggregateId(DomainEvent event) {
        return new Query(
                Criteria.where("_id").is(event.aggregateRootId())
        );
    }

}
