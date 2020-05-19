package example.micronaut.spoonacular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.micronaut.EchoController;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

//
// https://spoonacular.com/food-api/docs#Get-Random-Food-Trivia
//
@Controller("/food-trivia-random")
public class FoodTriviaController {

    private static final Logger LOG = LoggerFactory.getLogger(EchoController.class);

    private final FoodTriviaClient client;

    FoodTriviaController(FoodTriviaClient client) {
        this.client = client;
    }

    @Get("/{apiKey}")
    FoodTriviaResponse food_trivia_random(String apiKey) {
        FoodTriviaResponse response = null;

        try {
            response = client.fetch(apiKey);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
            response = new FoodTriviaResponse(e.getMessage());
        }

        return response;
    }
}
