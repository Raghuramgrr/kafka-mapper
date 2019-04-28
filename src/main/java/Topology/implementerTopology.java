package Topology;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class implementerTopology implements ApplicationContextAware {

    @Value("#kafkaStreamsConfiguration")
    private Properties kafkaStreams;
    @Setter
    private ApplicationContext applicationContext;

    @Getter
    private volatile KafkaStreams streams;

    @PostConstruct
    public void init(){
        StreamsBuilder builder=new StreamsBuilder();
        builderTopology constructor = null;
        try {
            constructor.constructTopology(builder);

        }catch (IOException e){
            e.printStackTrace();
        }
        Topology topology= builder.build();
        streams = new KafkaStreams(topology,kafkaStreams);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        streams.setUncaughtExceptionHandler((thread,exception)->{
            ConfigurableApplicationContext configurableApplicationContext=(ConfigurableApplicationContext)applicationContext;
            configurableApplicationContext.close();
        });
        log.info("About to Start 3 2 1");
        streams.start();
        log.info("JetStreams started");

    }



    @PreDestroy
    public void shutdown(){
        log.info("Shutdown");
        if(streams!=null){
            boolean success=streams.close(10, TimeUnit.SECONDS);
            log.info("Shutdown{]",success?"successfull":"not yet successfull after waiting for 10 seconds");
        }
        else {
            log.info("kafka streams wasn't Intialized,Shutdown complete");
        }
    }


}
