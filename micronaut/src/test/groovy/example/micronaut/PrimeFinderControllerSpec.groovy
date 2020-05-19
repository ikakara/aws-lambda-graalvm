package example.micronaut

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import io.reactivex.Flowable

import javax.inject.Inject

import spock.lang.Shared
import spock.lang.Specification

@MicronautTest
public class PrimeFinderControllerSpec extends Specification {

    @Inject
    EmbeddedServer server

    @Inject
    @Client('/')
    HttpClient client

    def "Test primes below 3"() {
        given: 'make /find-primes-below/3 blocking request'

        PrimeFinderResponse response = client.toBlocking()
                .retrieve(HttpRequest.GET('/find-primes-below/3'), PrimeFinderResponse)

        expect: "Expect 2 primes (${response.primes})"
        assert Collections.singletonList(2) == response.primes
    }

}
