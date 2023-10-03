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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetAllBoardsIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUpDatabase() {
        Board japanBoard = new Board()
                .setId("jp")
                .setTitle("Japan")
                .setGenre(Board.BoardGenre.JAPAN);
        Board animeBoard = new Board()
                .setId("a")
                .setTitle("Anime")
                .setGenre(Board.BoardGenre.JAPAN);
        boardRepository.saveAll(Arrays.asList(japanBoard, animeBoard));
    }

    @Test
    void successPath() throws Exception {
        String expectedJson = TestUtils.readResourceAsString("board/get-boards-response-200.json");

        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/boards", String.class);
        String actualJson = entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

}
