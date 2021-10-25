package br.com.alura.studykafka.serializer;

import br.com.alura.studykafka.model.Email;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonSerializer<T> implements Serializer<T>, Deserializer<T> {

    private final Gson gson = new GsonBuilder().create();


    @Override
    public byte[] serialize(String s, T object) {
        return gson.toJson(object).getBytes();
    }

    //---------------------------- DESERIALIZE METHODS ---------------------------------
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        var jsonString = new String(bytes);
        Type type = new TypeToken<T>() {
        }.getType();

        return gson.fromJson(jsonString, type);
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
