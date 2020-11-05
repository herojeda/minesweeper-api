package org.hojeda.minesweeper.functional.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class GetUserByIdTest extends FunctionalTest {

    @Test
    public void when_call_get_user_with_default_user_id_should_return_a_default_user() throws JsonProcessingException {
        var userId = 1;
        var givenUri = baseUrl + Routes.USER + Routes.USER_ID.replace(":userId", String.valueOf(userId));

        var response = Unirest.get(givenUri).asString();
        var body = JsonMapper.get().readValue(response.getBody(), UserResponse.class);

        assertThat(response.getStatus(), is(HttpStatus.SC_OK));
        assertThat(body.getName(), is("Default"));
    }

    @Test
    public void when_call_get_user_with_non_existent_user_id_should_return_404() {
        var userId = 9999;
        var givenUri = baseUrl + Routes.USER + Routes.USER_ID.replace(":userId", String.valueOf(userId));

        var response = Unirest.get(givenUri).asString();

        assertThat(response.getStatus(), is(HttpStatus.SC_NOT_FOUND));
        assertThat(response.getBody(), containsString("USER_NOT_FOUND"));
    }

}
