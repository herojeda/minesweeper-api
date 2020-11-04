package org.hojeda.minesweeper.functional.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUsersTest extends FunctionalTest {

    @Test
    public void when_call_get_users_should_return_a_list_of_users() throws JsonProcessingException {
        var givenUri = baseUrl + Routes.USER;

        var response = Unirest.get(givenUri).asString();
        var body = JsonMapper.get().readValue(response.getBody(), new TypeReference<List<UserResponse>>(){});

        assertThat(response.getStatus(), is(HttpStatus.SC_OK));
        assertThat(body, not(emptyCollectionOf(UserResponse.class)));
    }

}
