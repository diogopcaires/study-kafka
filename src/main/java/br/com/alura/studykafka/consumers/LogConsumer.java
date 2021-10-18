package br.com.alura.studykafka.consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.regex.Pattern;


public class LogConsumer {
    private static final Pattern TOPIC = Pattern.compile("STORE.*");
    MyKafkaConsumer _consumer;

    private LogConsumer(MyKafkaConsumer consumer) {
        this._consumer = consumer;
        this._consumer.setConsumerFunction(this::consume);
    }

    private void consume(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------");
        System.out.println("LOGGING MESSAGE FROM");
        System.out.println(record.topic());
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
    }

    public void run() {
        this._consumer.run();
    }


    public static LogConsumer factory() {
        var kafkaConsumer = new MyKafkaConsumer(TOPIC, LogConsumer.class.getName());
        return new LogConsumer(kafkaConsumer);
    }
}
