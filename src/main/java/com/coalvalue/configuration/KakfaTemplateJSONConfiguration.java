package com.coalvalue.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KakfaTemplateJSONConfiguration {



/*
    @Bean
    public SchemaRegistryClient schemaRegistryClient(@Value("${spring.cloud.stream.schemaRegistryClient.endpoint}") String endpoint){
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(endpoint);
        return client;
    }
*/

    @Bean
    public ProducerFactory jsonPproducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "192.168.10.90:9092");//,192.168.42.89:9093,192.168.42.89:9094");
/*        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 使用Confluent实现的KafkaAvroSerializer
        config.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");*/
        // 添加schema服务的地址，用于获取schema
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put("schema.registry.url", "http://192.168.10.90:8081");

/*
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
*/

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    @Qualifier(value="kafkaTemplateJson")
    public KafkaTemplate kafkaTemplateJson() {
        KafkaTemplate kafkaTemplate =  new KafkaTemplate<>(jsonPproducerFactory());
     //   kafkaTemplate.setProducerListener(kafkaSendResultHandler);

        return kafkaTemplate;
    }

}