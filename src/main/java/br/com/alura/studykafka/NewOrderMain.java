package br.com.alura.studykafka;

import br.com.alura.studykafka.producers.EmailProducer;
import br.com.alura.studykafka.producers.NewOrderProducer;

import java.util.UUID;

public class NewOrderMain {
    public static void main(String[] args) {
        var emailProducer = EmailProducer.factory();
        var newOrderProducer = NewOrderProducer.factory();
        var email = "Thanks for your order! We are processing your order!";

        //new order message
        for (var i = 0; i < 5; i++) {
            var orderId = UUID.randomUUID().toString();
            var userId = UUID.randomUUID().toString();
            Float value = 100.00F;
            newOrderProducer.sendEvent(orderId, userId, value);
            emailProducer.sendEvent(userId + "@gmail.com", email);
        }
    }
}

