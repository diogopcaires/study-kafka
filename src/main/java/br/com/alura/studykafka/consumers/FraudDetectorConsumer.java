package br.com.alura.studykafka.consumers;

import br.com.alura.studykafka.model.Email;
import br.com.alura.studykafka.model.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorConsumer {
    private static final String TOPIC = "STORE_NEW_ORDER";
    MyKafkaConsumer _consumer;

    private FraudDetectorConsumer(MyKafkaConsumer consumer) {
        this._consumer = consumer;
        this._consumer.setConsumerFunction(this::consume);
    }

    private void consume(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Order check fraud finished...");
    }

    public void run() {
        this._consumer.run();
    }

    public static FraudDetectorConsumer factory() {
        var kafkaConsumer = new MyKafkaConsumer(TOPIC, FraudDetectorConsumer.class.getName());
        return new FraudDetectorConsumer(kafkaConsumer);
    }
}