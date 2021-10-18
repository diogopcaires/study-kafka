package br.com.alura.studykafka;

import br.com.alura.studykafka.consumers.EmailConsumer;
import br.com.alura.studykafka.consumers.FraudDetectorConsumer;
import br.com.alura.studykafka.consumers.LogConsumer;

public class SubscribeMain {
    public static void main(String[] args) {
        var emailConsumerThread = SubscribeMain.startEmailConsumer();
        var fraudConsumerThread = SubscribeMain.startFraudDetectorConsumer();
        var logConsumerThread  = SubscribeMain.startLogConsumer();
        emailConsumerThread.start();
        fraudConsumerThread.start();
        logConsumerThread.start();
    }

    static Thread startEmailConsumer() {
        return new Thread(() -> {
            var emailConsumer =  EmailConsumer.factory();
            emailConsumer.run();
        });
    }

    static Thread startLogConsumer() {
        return new Thread(() -> {
            var consumer =  LogConsumer.factory();
            consumer.run();
        });
    }

    static Thread startFraudDetectorConsumer() {
        return new Thread(() -> {
            var consumer =  FraudDetectorConsumer.factory();
            consumer.run();
        });
    }
}
