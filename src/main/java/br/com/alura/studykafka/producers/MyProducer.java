package br.com.alura.studykafka.producers;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class MyProducer {
    private String _topic;
    private KafkaProducer _producer;

    MyProducer(String topic) {
        this._topic = topic;
        this._producer = new KafkaProducer<String, String>(properties());
    }

    public void sendEvent(String event) {
        var uuid = UUID.randomUUID().toString();
        var record =  new ProducerRecord(this._topic, uuid, event);

        try {
            this._producer.send(record, getCallback()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private  Callback getCallback() {
        return (RecordMetadata recordMetadata, Exception e) -> {
            if (e != null) {
                e.printStackTrace();
            }
            System.out.println(recordMetadata.topic() + ":::" + recordMetadata.partition() + ":::" + recordMetadata.offset() + ":::" + recordMetadata.timestamp());
        };
    }

    private Properties properties() {
        var prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return prop;
    }
}
