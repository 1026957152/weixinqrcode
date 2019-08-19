package com.coalvalue.configuration;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerJSONConfig {
    private final static String BOOTSTRAP_SERVERS = "192.168.10.90:9092";
    private final static String TOPIC = "new-employees";


    @Bean
    public ConsumerFactory<String, Object> yourConsumerFactory2() {
        Map<String, Object> props = new HashMap<>();
/*
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleAvroConsumer");
*/
/*
       props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                LongDeserializer.class.getName());

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
               StringDeserializer.class.getName());
        //Use Kafka Avro Deserializer.
       props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,                KafkaAvroDeserializer.class.getName());  //<----------------------

        //Use Specific Record or else you get Avro GenericRecord.
      //  props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        //Schema registry location.
       props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG,"http://192.168.10.90:8081"); //<----- Run Schema Registry on 8081

*/


        props.put("bootstrap.servers", "192.168.10.90:9092");//,192.168.42.89:9093,192.168.42.89:9094");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 使用Confluent实现的KafkaAvroSerializer
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put("schema.registry.url", "http://192.168.10.90:8081");

        //props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);



        return new DefaultKafkaConsumerFactory<>(props);
    }
 

/*
    public ConcurrentKafkaListenerContainerFactory<String, Object>
      kafkaListenerContainerFactory() {
    
        ConcurrentKafkaListenerContainerFactory<String, Object> factory
          = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
*/


    @Bean(name = "kafkaListenerContainerFactory_JSON")
   // @Qualifier(value="kafkaListenerContainerFactory_JSON")
    public KafkaListenerContainerFactory kafkaListenerContainerFactory_JSON() {
/*        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(yourConsumerFactory2());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;*/

        ConcurrentKafkaListenerContainerFactory<String, Object> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(yourConsumerFactory2());
        factory.setMessageConverter(new StringJsonMessageConverter());

        return factory;
    }

/*    @Bean(name = "kafkaListenerContainerFactory_JSON")
    // @Qualifier(value="kafkaListenerContainerFactory_JSON")
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory_JSON() {
        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }*/
}