package com.mogikanlol.data.thread;

import com.mogikanlol.data.AbstractIntegrationTest;
import com.mogikanlol.data.TestUtils;
import com.mogikanlol.data.domain.Board;
import com.mogikanlol.data.domain.Thread;
import com.mogikanlol.data.repository.BoardRepository;
import com.mogikanlol.data.repository.ThreadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetThreadByIdIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUpDatabase() {
        Board animeBoard = new Board()
                .setId("a")
                .setTitle("Anime")
                .setGenre(Board.BoardGenre.JAPAN);
        boardRepository.save(animeBoard);

        Thread thread = new Thread()
                .setTitle("Neon Genesis Evangelion Thread")
                .setImageName("eva.png")
                .setContent("content")
                .setBoard(animeBoard);
        threadRepository.save(thread);
    }

    @Test
    void successPath() throws Exception {
        String expectedJson = TestUtils.readResourceAsString("thread/get-thread-by-id-response-200.json");

        ResponseEntity<String> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/threads/1",
                String.class);
        String actualJson = entity.getBody();

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

    @Test
    void threadNotFound() {
        ResponseEntity<String> entity = restTemplate.getForEntity(
                "http://localhost:" + port + "/threads/999",
                String.class);

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

}
