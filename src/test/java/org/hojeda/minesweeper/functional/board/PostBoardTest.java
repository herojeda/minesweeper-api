package org.hojeda.minesweeper.functional.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.Unirest;
import org.apache.http.HttpStatus;
import org.hojeda.minesweeper.configuration.Context;
import org.hojeda.minesweeper.configuration.database.SqlClient;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.entrypoint.router.Routes;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.util.JsonLoader;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostBoardTest extends FunctionalTest {

    @Test
    public void when_post_a_board_correctly_should_return_201_and_board_data_is_correctly_saved() throws JsonProcessingException {

        var givenRowSize = 10;
        var givenColumnSize = 10;
        var givenMines = 10;
        var givenUri = baseUrl + Routes.BOARD;
        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/post_board_request.json",
            Map.of(
                "row_size", String.valueOf(givenRowSize),
                "column_size", String.valueOf(givenColumnSize),
                "mines", String.valueOf(givenMines)
            )
        );

        var response = Unirest.post(givenUri)
            .body(givenBody)
            .asString();

        System.out.println(response.getBody());

        // Validate status, should be 201
        assertThat(response.getStatus(), is(HttpStatus.SC_CREATED));

        var body = JsonMapper.get().readValue(response.getBody(), BoardResponse.class);
        assertThat(body.getFields().size(), is(givenRowSize * givenColumnSize));
        assertThat(
            body.getFields().stream()
                .filter(field -> MineBoardField.MINE_VALUE == field.getValue())
                .collect(Collectors.toList())
                .size(),
            is(givenMines)
        );

        var sqlClient = Context.getInjector().getInstance(SqlClient.class);

        // Validate generated fields
        var fieldsCount = sqlClient.runQuery(
            "SELECT COUNT(*) as count FROM BOARD_FIELD bf WHERE bf.board_id = ?",
            rs -> {
                if (rs.next()) return rs.getInt("count");
                else return null;
            },
            body.getId()
        );
        assertThat(fieldsCount, is(givenRowSize * givenColumnSize));

        // Validate generated mines
        var minesCount = sqlClient.runQuery(
            "SELECT COUNT(*) as count FROM BOARD_FIELD bf WHERE bf.board_id = ? and value = 9",
            rs -> {
                if (rs.next()) return rs.getInt("count");
                else return null;
            },
            body.getId()
        );
        assertThat(minesCount, is(givenMines));
    }
}
