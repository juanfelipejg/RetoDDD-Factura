package org.example.supermercado.infra.bus;

public interface EventSubscriber {
    void subscribe(String eventType, String exchange);
}
