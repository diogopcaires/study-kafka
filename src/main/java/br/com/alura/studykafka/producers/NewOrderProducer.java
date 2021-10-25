package br.com.alura.studykafka.producers;

import br.com.alura.studykafka.model.Order;

public class NewOrderProducer {
    private static final String TOPIC = "STORE_NEW_ORDER";

    private final MyProducer<Order> _producer;

    private NewOrderProducer(MyProducer<Order> producer) {
        this._producer = producer;
    }

    public void sendEvent(Order order) {
        this._producer.sendEvent(order);
    }

    public static NewOrderProducer factory() {
        var producer = new MyProducer<Order>(TOPIC);
        return new NewOrderProducer(producer);
    }
}