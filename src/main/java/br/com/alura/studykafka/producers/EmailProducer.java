package br.com.alura.studykafka.producers;

public class EmailProducer {
    private static final String TOPIC = "STORE_SEND_EMAIL";

    private MyProducer _producer;

    private EmailProducer(MyProducer producer) {
        this._producer = producer;
    }


    public void sendEvent(String email, String content) {
        this._producer.sendEvent("{\"email\" : \"" + email + " \" , \" content\" : \"" + content + "\" }");

    }

    public static EmailProducer factory() {
        var producer = new MyProducer(TOPIC);
        return new EmailProducer(producer);
    }
}
