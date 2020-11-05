package org.hojeda.minesweeper.functional.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.util.JsonLoader;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.configuration.mapper.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostUserTest extends FunctionalTest {

    @Test
    public void when_post_a_user_should_save_it() throws JsonProcessingException {
        var givenUri = baseUrl + Routes.USER;

        var givenName = "test-user";

        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/post_user_request.json",
            Map.of(
                "name", givenName
            )
        );

        var response = Unirest.post(givenUri)
            .body(givenBody)
            .asString();

        var body = JsonMapper.get().readValue(response.getBody(), UserResponse.class);

        assertThat(response.getStatus(), is(HttpStatus.SC_CREATED));
        assertThat(body.getName(), is(givenName));

        var getUri = baseUrl + Routes.USER + Routes.USER_ID.replace(":userId", String.valueOf(body.getId()));

        var getResponse = Unirest.get(getUri).asString();
        var getBody = JsonMapper.get().readValue(getResponse.getBody(), UserResponse.class);

        assertThat(body, is(getBody));
    }
}
