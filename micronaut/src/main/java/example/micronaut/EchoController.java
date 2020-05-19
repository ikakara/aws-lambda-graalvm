package example.micronaut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micronaut.http.annotation.*;

@Controller("/")
public class EchoController {
    private static final Logger LOG = LoggerFactory.getLogger(EchoController.class);

    @Get("/echo/{stuff}")
    public EchoResponse index(String stuff) {
        return new EchoResponse(stuff, true);
    }
}
