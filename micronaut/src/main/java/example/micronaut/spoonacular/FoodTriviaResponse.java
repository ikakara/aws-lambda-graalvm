package example.micronaut.spoonacular;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodTriviaResponse {

    private final String text;

    @JsonCreator
    public FoodTriviaResponse(@JsonProperty("text") String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
