package br.com.alura.studykafka;

import br.com.alura.studykafka.model.Email;
import br.com.alura.studykafka.model.Order;
import br.com.alura.studykafka.producers.EmailProducer;
import br.com.alura.studykafka.producers.NewOrderProducer;

import java.math.BigDecimal;
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
            BigDecimal value = new BigDecimal(100.00);
            newOrderProducer.sendEvent(new Order(orderId, userId, value));
            emailProducer.sendEvent(new Email(userId + "@gmail.com", email));
        }
    }
}

