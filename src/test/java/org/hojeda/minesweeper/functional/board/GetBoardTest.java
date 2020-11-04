package org.hojeda.minesweeper.functional.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.route.Routes;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.util.JsonLoader;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.hojeda.minesweeper.core.entity.constants.board.BoardStatus.CREATED;

public class GetBoardTest extends FunctionalTest {

    @Test
    public void when_call_get_should_return_board() throws JsonProcessingException {
        var createdBoard = createBoard();
        var givenUri = baseUrl + Routes.BOARD + Routes.BOARD_ID.replace(":boardId", createdBoard.getId().toString());

        var response = Unirest.get(givenUri).asString();
        var body = JsonMapper.get().readValue(response.getBody(), BoardResponse.class);

        assertThat(response.getStatus(), is(HttpStatus.SC_OK));
        assertThat(body.getStatus(), equalToIgnoringCase(CREATED.name()));
        assertThat(body.getId(), is(createdBoard.getId()));
    }

    @Test
    public void when_call_get_with_non_existent_id_should_return_404() throws JsonProcessingException {

        var givenUri = baseUrl + Routes.BOARD + Routes.BOARD_ID.replace(":boardId", "99999999");

        var response = Unirest.get(givenUri).asString();

        System.out.println(response.getBody());

        assertThat(response.getStatus(), is(HttpStatus.SC_NOT_FOUND));
    }

    private BoardResponse createBoard() throws JsonProcessingException {
        var response = Unirest.post(baseUrl + Routes.BOARD)
            .body(
                JsonLoader.readFromFile(
                    "/file/json/request/post_board_request.json",
                    Map.of(
                        "row_size", String.valueOf(10),
                        "column_size", String.valueOf(10),
                        "mines", String.valueOf(10)
                    )
                )
            )
            .asString();
        return JsonMapper.get().readValue(response.getBody(), BoardResponse.class);
    }
}
