package br.com.alura.studykafka.producers;

import br.com.alura.studykafka.model.Email;

public class EmailProducer {
    private static final String TOPIC = "STORE_SEND_EMAIL";

    private final MyProducer<Email> _producer;

    private EmailProducer(MyProducer<Email> producer) {
        this._producer = producer;
    }

    public void sendEvent(Email email) {
        this._producer.sendEvent(email);
    }

    public static EmailProducer factory() {
        var producer = new MyProducer<Email>(TOPIC);
        return new EmailProducer(producer);
    }
}
