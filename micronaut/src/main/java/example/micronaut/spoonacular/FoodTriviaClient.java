package example.micronaut.spoonacular;

import java.net.URI;

import javax.inject.Singleton;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;

@Singleton
public class FoodTriviaClient {

    private final RxHttpClient httpClient;
    private final URI uri;

    public FoodTriviaClient(@Client(SpoonacularConfiguration.API_URL) RxHttpClient httpClient,
            SpoonacularConfiguration configuration) {
        this.httpClient = httpClient;
        this.uri = UriBuilder.of(SpoonacularConfiguration.PATH_FOOD_TRIVIA_RANDOM)
                // .path(configuration.apiKey)
                .build();
    }

    FoodTriviaResponse fetch(String apiKey) {
        HttpRequest<?> request = HttpRequest.GET(uri + "?apiKey=" + apiKey);
        FoodTriviaResponse response = httpClient.toBlocking().retrieve(request, FoodTriviaResponse.class);

        return response;
    }

}