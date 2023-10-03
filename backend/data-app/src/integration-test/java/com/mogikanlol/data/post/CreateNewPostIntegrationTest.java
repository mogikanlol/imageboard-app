package com.mogikanlol.data.post;

import com.mogikanlol.data.AbstractIntegrationTest;
import com.mogikanlol.data.TestUtils;
import com.mogikanlol.data.domain.Board;
import com.mogikanlol.data.domain.Thread;
import com.mogikanlol.data.repository.BoardRepository;
import com.mogikanlol.data.repository.ThreadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateNewPostIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        Board board = new Board()
                .setId("a")
                .setTitle("Anime")
                .setGenre(Board.BoardGenre.JAPAN);
        boardRepository.save(board);

        Thread thread = new Thread()
                .setTitle("Neon Genesis Evangelion Thread")
                .setImageName("eva.png")
                .setContent("content")
                .setBoard(board);
        threadRepository.save(thread);
    }

    @Test
    void successPath() throws Exception {
        String requestJson = TestUtils.readResourceAsString("post/new-post-request.json");
        String expectedJson = TestUtils.readResourceAsString("post/new-post-response-200.json");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json;charset=UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/posts", httpEntity, String.class);
        String actualJson = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(actualJson);
        jsonExpectationsHelper.assertJsonEqual(expectedJson, actualJson);
    }

    @Test
    void threadNotFound() throws Exception {
        String requestJson = TestUtils.readResourceAsString("post/new-post-with-wrong-thread-request.json");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json;charset=UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

        ResponseEntity<String> response = restTemplate
                .postForEntity("http://localhost:" + port + "/posts", httpEntity, String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
