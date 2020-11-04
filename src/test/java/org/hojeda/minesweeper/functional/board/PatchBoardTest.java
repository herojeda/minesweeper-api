package org.hojeda.minesweeper.functional.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import kong.unirest.Unirest;
import org.hojeda.minesweeper.core.entity.board.field.MineBoardField;
import org.hojeda.minesweeper.core.entity.constants.board.MovementType;
import org.hojeda.minesweeper.entrypoint.router.Routes;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardFieldResponse;
import org.hojeda.minesweeper.entrypoint.router.dto.response.board.BoardResponse;
import org.hojeda.minesweeper.util.JsonLoader;
import org.hojeda.minesweeper.util.base.FunctionalTest;
import org.hojeda.minesweeper.util.mapper.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hojeda.minesweeper.core.entity.constants.board.BoardStatus.LOST;
import static org.hojeda.minesweeper.core.entity.constants.board.BoardStatus.PLAYING;
import static org.hojeda.minesweeper.core.entity.constants.board.field.BoardFieldStatus.*;

public class PatchBoardTest extends FunctionalTest {

    @Test
    public void when_open_field_with_0_should_open_adyacents_fields() throws JsonProcessingException {

        var createdBoard = createBoard();

        var givenMovementType = MovementType.OPEN;

        var fieldWithZero = createdBoard.getFields().stream()
            .filter(field -> field.getValue() == 0)
            .findAny()
            .get();

        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/patch_board_request.json",
            Map.of(
                "row", String.valueOf(fieldWithZero.getRow()),
                "column", String.valueOf(fieldWithZero.getColumn()),
                "movement_type", givenMovementType.name().toLowerCase()
            )
        );

        var givenUri = baseUrl + Routes.BOARD + Routes.BOARD_ID.replace(":boardId", createdBoard.getId().toString());

        var response = Unirest.patch(givenUri)
            .body(givenBody)
            .asString();

        System.out.println(response.getBody());

        var body = JsonMapper.get().readValue(response.getBody(), BoardResponse.class);

        assertThat(body.getStatus(), equalToIgnoringCase(PLAYING.name()));

        var mappedFields = new HashMap<Integer, Map<Integer, BoardFieldResponse>>();
        body.getFields()
            .forEach(field -> {
                if (!mappedFields.containsKey(field.getRow())) mappedFields.put(field.getRow(), new HashMap<>());
                mappedFields.get(field.getRow()).put(field.getColumn(), field);
            });

        var firstAdyacentRow = fieldWithZero.getRow() - 1;
        var firstAdyacentColumn = fieldWithZero.getColumn() - 1;

        IntStream.range(
            Math.max(0, firstAdyacentRow),
            Math.min(firstAdyacentRow + 3, mappedFields.size())
        ).forEach(rowIdx -> IntStream.range(
            Math.max(0, firstAdyacentColumn),
            Math.min(firstAdyacentColumn + 3, mappedFields.get(rowIdx).size())
        ).forEach(columnIdx -> {
                var adyacentField = mappedFields.get(rowIdx).get(columnIdx);
                assertThat(adyacentField.getStatus(), equalToIgnoringCase(OPENED.name()));
            }
        ));
    }

    @Test
    public void when_open_field_with_mine_should_return_board_with_status_lost() throws JsonProcessingException {

        var createdBoard = createBoard();

        var givenMovementType = MovementType.OPEN;

        var fieldWithMine = createdBoard.getFields().stream()
            .filter(field -> field.getValue().equals(MineBoardField.MINE_VALUE))
            .findAny()
            .get();

        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/patch_board_request.json",
            Map.of(
                "row", String.valueOf(fieldWithMine.getRow()),
                "column", String.valueOf(fieldWithMine.getColumn()),
                "movement_type", givenMovementType.name().toLowerCase()
            )
        );

        var givenUri = baseUrl + Routes.BOARD + Routes.BOARD_ID.replace(":boardId", createdBoard.getId().toString());

        var response = Unirest.patch(givenUri)
            .body(givenBody)
            .asString();

        System.out.println(response.getBody());

        var body = JsonMapper.get().readValue(response.getBody(), BoardResponse.class);

        assertThat(body.getStatus(), equalToIgnoringCase(LOST.name()));

        var returnedMineField = body.getFields().stream()
            .filter(field -> field.getColumn().equals(fieldWithMine.getColumn()) && field.getRow().equals(fieldWithMine.getRow()))
            .findFirst()
            .get();

        assertThat(returnedMineField.getStatus(), equalToIgnoringCase(OPENED.name()));
    }

    @Test
    public void when_flag_field_should_return_flagged_field() throws JsonProcessingException {

        var createdBoard = createBoard();

        var givenMovementType = MovementType.FLAG;

        var aField = createdBoard.getFields().stream()
            .findAny()
            .get();

        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/patch_board_request.json",
            Map.of(
                "row", String.valueOf(aField.getRow()),
                "column", String.valueOf(aField.getColumn()),
                "movement_type", givenMovementType.name().toLowerCase()
            )
        );

        var givenUri = baseUrl + Routes.BOARD + Routes.BOARD_ID.replace(":boardId", createdBoard.getId().toString());

        var response = Unirest.patch(givenUri)
            .body(givenBody)
            .asString();

        System.out.println(response.getBody());

        var body = JsonMapper.get().readValue(response.getBody(), BoardResponse.class);

        assertThat(body.getStatus(), equalToIgnoringCase(PLAYING.name()));

        var returnedField = body.getFields().stream()
            .filter(field -> field.getColumn().equals(aField.getColumn()) && field.getRow().equals(aField.getRow()))
            .findFirst()
            .get();

        assertThat(returnedField.getStatus(), equalToIgnoringCase(FLAGGED.name()));
    }

    @Test
    public void when_flag_field_should_return_questioned_field() throws JsonProcessingException {

        var createdBoard = createBoard();

        var givenMovementType = MovementType.QUESTION;

        var aField = createdBoard.getFields().stream()
            .findAny()
            .get();

        var givenBody = JsonLoader.readFromFile(
            "/file/json/request/patch_board_request.json",
            Map.of(
                "row", String.valueOf(aField.getRow()),
                "column", String.valueOf(aField.getColumn()),
                "movement_type", givenMovementType.name().toLowerCase()
            )
        );

        var givenUri = baseUrl + Routes.BOARD + Routes.BOARD_ID.replace(":boardId", createdBoard.getId().toString());

        var response = Unirest.patch(givenUri)
            .body(givenBody)
            .asString();

        System.out.println(response.getBody());

        var body = JsonMapper.get().readValue(response.getBody(), BoardResponse.class);

        assertThat(body.getStatus(), equalToIgnoringCase(PLAYING.name()));

        var returnedMineField = body.getFields().stream()
            .filter(field -> field.getColumn().equals(aField.getColumn()) && field.getRow().equals(aField.getRow()))
            .findFirst()
            .get();

        assertThat(returnedMineField.getStatus(), equalToIgnoringCase(QUESTIONED.name()));
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
