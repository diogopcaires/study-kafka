package br.com.alura.studykafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        var value = "#12331,123,520";

        var record = new ProducerRecord("STORE_NEW_ORDER", value, value);

        var email = "Thanks for your order! We are processing your order!";

        var emailRecord = new ProducerRecord("STORE_SEND_EMAIL", email, email);

        producer.send(record, getCallback()).get();
        producer.send(emailRecord, getCallback()).get();
    }

    private static Callback getCallback() {
        return (RecordMetadata recordMetadata, Exception e) -> {
            if (e != null) {
                e.printStackTrace();
            }
            System.out.println(recordMetadata.topic() + ":::" + recordMetadata.partition() + ":::" + recordMetadata.offset() + ":::" + recordMetadata.timestamp());
        };
    }

    private static Properties properties() {
        var prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return prop;
    }
}

