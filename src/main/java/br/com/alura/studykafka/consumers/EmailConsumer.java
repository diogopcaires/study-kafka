package br.com.alura.studykafka.consumers;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailConsumer {
    private static final String TOPIC = "STORE_SEND_EMAIL";
    MyKafkaConsumer _consumer;

    private EmailConsumer(MyKafkaConsumer consumer) {
        this._consumer = consumer;
        this._consumer.setConsumerFunction(this::consume);
    }

    private void consume(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------");
        System.out.println("Send email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }

    public void run() {
        this._consumer.run();
    }


    public static EmailConsumer factory() {
        var kafkaConsumer = new MyKafkaConsumer(TOPIC, EmailConsumer.class.getName());
        return new EmailConsumer(kafkaConsumer);
    }
}
