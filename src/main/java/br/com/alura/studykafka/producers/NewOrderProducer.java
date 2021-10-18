package br.com.alura.studykafka.producers;

public class NewOrderProducer {
    private static final String TOPIC = "STORE_NEW_ORDER";

    private MyProducer _producer;

    private NewOrderProducer(MyProducer producer) {
        this._producer = producer;
    }


    public void sendEvent(String orderId, String userId, Float value) {
        this._producer.sendEvent("{\"orderId\" : \"" + orderId + " \" , \" userId\" : \"" + userId + "\" , \" value\" : \"" + value + "\" }");
    }

    public static NewOrderProducer factory() {
        var producer = new MyProducer(TOPIC);
        return new NewOrderProducer(producer);
    }
}