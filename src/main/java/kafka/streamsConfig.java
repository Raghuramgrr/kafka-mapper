package kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class streamsConfig {
    @Value("${kafka.bootstrap.servers}")
    private String boostrapServer;

    @Value("${kafka.streams.application.id")
    private String kafkaStreamsApplicationId;

    @Value(("${kafka.streams.dir}"))
    private String kafkaStreamsstateDirectory;

    @Bean
    public Map<String,Object> kafkaStreamsConfiguration() throws IOException{
        Map<String,Object> properties = new HashMap<String, Object>();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,kafkaStreamsApplicationId);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,boostrapServer);
        properties.put(StreamsConfig.STATE_DIR_CONFIG,kafkaStreamsstateDirectory);
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,10);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(StreamsConfig.DEFAULT_WINDOWED_KEY_SERDE_INNER_CLASS, LogAndContinueExceptionHandler.class.getName());

        return properties;
    }
}
