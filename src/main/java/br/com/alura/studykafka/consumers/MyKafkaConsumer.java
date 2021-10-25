package br.com.alura.studykafka.consumers;

import br.com.alura.studykafka.serializer.GsonSerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

interface ConsumerFunction<T> {
    void run(ConsumerRecord<T, String> record);
}

public class MyKafkaConsumer<T> {
    private String _topic;
    private String _group;
    private Pattern _pattern;
    private ConsumerFunction _fn;

    MyKafkaConsumer(String topic, String group) {
        this._topic = topic;
        this._group = group;
    }

    MyKafkaConsumer(Pattern pattern, String group) {
        this._pattern = pattern;
        this._group = group;
    }

    public void setConsumerFunction(ConsumerFunction consumerFunction) {
        this._fn = consumerFunction;
    }

    void run() {
        var consumer = new KafkaConsumer<T, String>(properties());

        if (this._pattern == null)
            consumer.subscribe(Collections.singletonList(this._topic));
        else
            consumer.subscribe(this._pattern);

        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
                for (var record : records) {
                    this._fn.run(record);
                }
            }
        }
    }

    private Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, this._group);
        return properties;
    }
}
