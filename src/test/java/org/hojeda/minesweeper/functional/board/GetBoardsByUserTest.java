package org.hojeda.minesweeper.functional.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import kong.unirest.Unirest;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.entrypoint.router.dto.response.user.UserResponse;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.util.JsonLoader;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.configuration.mapper.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetBoardsByUserTest extends FunctionalTest {

    @Test
    public void when_call_get_boards_by_user_id_should_return_his_boards() throws JsonProcessingException {
        var createdUser = createUser();
        var createdBoard = createBoard(createdUser.getId());

        var givenUri = baseUrl +
            Routes.USER +
            Routes.USER_ID.replace(":userId", String.valueOf(createdUser.getId())) +
            Routes.BOARD;

        var response = Unirest.get(givenUri).asString();

        System.out.println(givenUri);
        System.out.println(response);

        var body = JsonMapper.get().readValue(response.getBody(), new TypeReference<List<BoardResponse>>() {});

        assertThat(body.iterator().next(), is(createdBoard));
    }

    private UserResponse createUser() throws JsonProcessingException {
        var givenUri = baseUrl + Routes.USER;
        var givenName = "test-user-2";
        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/post_user_request.json",
            Map.of(
                "name", givenName
            )
        );

        var response = Unirest.post(givenUri)
            .body(givenBody)
            .asString();

        return JsonMapper.get().readValue(response.getBody(), UserResponse.class);
    }

    private BoardResponse createBoard(Long userId) throws JsonProcessingException {
        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/post_board_with_user_request.json",
            Map.of(
                "row_size", "10",
                "column_size", "10",
                "mines", "10",
                "user_id", userId.toString()
            )
        );

        var response = Unirest.post(baseUrl + Routes.BOARD)
            .body(givenBody)
            .asString();

        return JsonMapper.get().readValue(response.getBody(), BoardResponse.class);
    }
}
