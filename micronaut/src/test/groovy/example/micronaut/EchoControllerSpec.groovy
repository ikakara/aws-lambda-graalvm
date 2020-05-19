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
public class EchoControllerSpec extends Specification {

    @Inject
    EmbeddedServer server

    @Inject
    @Client('/')
    HttpClient client

    def "Test /echo/stuff"() {
        expect: 'expect stuff'

        EchoResponse response = client.toBlocking().retrieve(HttpRequest.GET('/echo/stuff'), EchoResponse)

        assert response.echo == 'stuff'
        assert response.graal == true
    }

}
