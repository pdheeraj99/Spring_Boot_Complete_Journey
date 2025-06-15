package com.learn_kafka_with_java;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderProducerSync {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
        ProducerRecord<String, Integer> record = new ProducerRecord<String, Integer>("OrderTopic", "Mac Book Pro", 10);

        try {
            // 1. The send(record) method is asynchronous, but calling .get() makes it
            // synchronous,
            // 2. The RecordMetadata object contains metadata about the sent record, such as
            // the partition and offset.
            RecordMetadata recordMetadata = producer.send(record).get();

            // Prints the partition number where the record was written.
            System.out.println(recordMetadata.partition());

            // Prints the offset of the record in the partition. The offset is a unique
            // identifier for the record within the partition.
            System.out.println(recordMetadata.offset());
            System.out.println("Record Sent from here");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }

    }

}
