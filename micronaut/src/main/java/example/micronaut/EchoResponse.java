package example.micronaut;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EchoResponse {
    private final String echo;
    private final boolean graal;

    @JsonCreator
    public EchoResponse(@JsonProperty("echo") String echo, @JsonProperty("graal") boolean graal) {
        this.echo = echo;
        this.graal = graal;
    }

    public String getEcho() {
        return echo;
    }

    public boolean getGraal() {
        return graal;
    }
}