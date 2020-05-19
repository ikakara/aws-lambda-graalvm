package example.micronaut.spoonacular

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Flowable

import javax.inject.Inject

import spock.lang.Shared
import spock.lang.Specification

@MicronautTest
class FoodTriviaControllerSpec extends Specification {

    @Inject
    EmbeddedServer server

    @Inject
    @Client('/')
    HttpClient client

    def "test /food-trivia-random/xxxx"() {
        expect:
        HttpRequest request = HttpRequest.GET('/food-trivia-random/xxxx')
        FoodTriviaResponse response = client.toBlocking().retrieve(request, FoodTriviaResponse)

        assert response.text =~ 'You are not authorized.'
    }

}
