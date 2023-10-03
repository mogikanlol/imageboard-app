package com.mogikanlol.data.board;

import com.mogikanlol.data.AbstractIntegrationTest;
import com.mogikanlol.data.TestUtils;
import com.mogikanlol.data.domain.Board;
import com.mogikanlol.data.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetBoardByIdIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUpDatabase() {
        Board board = new Board()
                .setId("jp")
                .setTitle("Japan")
                .setGenre(Board.BoardGenre.JAPAN);
        boardRepository.save(board);
    }

    @Test
    void successPath() throws Exception {
        String expectedJson = TestUtils.readResourceAsString("board/get-board-by-id-response-200.json");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/boards/jp", String.class);
        String actualJson = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

    @Test
    void boardNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/boards/test",
                String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
