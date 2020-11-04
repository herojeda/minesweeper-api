package org.hojeda.minesweeper.functional.healthcheck;

import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthCheckTest extends FunctionalTest {

    @Test
    public void when_call_health_check_should_return_200() {

        final var givenUri = baseUrl + Routes.HEALTH;

        var response = Unirest.get(givenUri).asString();

        // Validate status, should be 200
        assertThat(response.getStatus(), is(HttpStatus.SC_OK));

        // Validate message, should be "pong"
        assertThat(response.getBody(), is("pong"));
    }
}
