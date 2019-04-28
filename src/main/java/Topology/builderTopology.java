package Topology;

import org.apache.kafka.streams.StreamsBuilder;

import java.io.IOException;

public interface builderTopology {
    void constructTopology(StreamsBuilder builder) throws IOException;
}
