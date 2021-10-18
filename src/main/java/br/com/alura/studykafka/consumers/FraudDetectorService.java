package br.com.alura.studykafka.consumers;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FraudDetectorService {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            var consumerid = i + "";
            new Thread(() -> {
                var consumer = new AntiFraudConsumer(consumerid);
                consumer.start();
            }).start();
        }
    }
}

class AntiFraudConsumer {
    private String _id;

    public AntiFraudConsumer(String id) {
        this._id = id;
    }

    public void start() {
        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList("STORE_NEW_ORDER"));

        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {

                for (var record : records) {
                    System.out.println("-----------------------");
                    System.out.println("Consumer " + _id + " processing Messages");
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
            }
        }
    }

    private Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "batatinhafrita123");
        return properties;
    }
}