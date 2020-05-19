package example.micronaut;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

//
// From: https://guides.micronaut.io/micronaut-function-graalvm-aws-lambda-gateway/guide/index.html
//
@Introspected
public class PrimeFinderResponse {

    public String message;
    public List<Integer> primes;
}