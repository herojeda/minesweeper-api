package org.hojeda.minesweeper.functional.board;

import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.entrypoint.router.Routes;
import org.hojeda.minesweeper.util.FunctionalTest;
import org.hojeda.minesweeper.util.JsonLoader;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostBoardTest extends FunctionalTest {

    @Test
    public void when_post_a_board_correctly_should_return_201_and_board_data() {

        var givenUri = baseUrl + Routes.BOARD;
        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/post_board_request.json",
            Map.of(
                "row_size", "10",
                "column_size", "10",
                "bombs", "10"
            )
        );

        var response = Unirest.post(givenUri)
            .body(givenBody)
            .asString();

        // Validate status, should be 201
        assertThat(response.getStatus(), is(HttpStatus.SC_CREATED));
    }
}
