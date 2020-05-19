package example.micronaut.spoonacular;

import java.util.Collections;
import java.util.Map;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;

//
// https://spoonacular.com/food-api/docs#Get-Random-Food-Trivia
//
@ConfigurationProperties(SpoonacularConfiguration.PREFIX)
@Requires(property = SpoonacularConfiguration.PREFIX)
class SpoonacularConfiguration {

    public static final String PREFIX                  = "spoonacular";
    public static final String API_URL                 = "https://api.spoonacular.com";
    public static final String PATH_FOOD_TRIVIA_RANDOM = "/food/trivia/random";

    public String apiKey;

    Map<String, Object> toMap() {
        return Collections.singletonMap("apiKey", apiKey);
    }
}