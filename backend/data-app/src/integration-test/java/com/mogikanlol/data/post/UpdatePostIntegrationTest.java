package com.mogikanlol.data.post;

import com.mogikanlol.data.AbstractIntegrationTest;
import com.mogikanlol.data.TestUtils;
import com.mogikanlol.data.domain.Board;
import com.mogikanlol.data.domain.Post;
import com.mogikanlol.data.domain.Thread;
import com.mogikanlol.data.repository.BoardRepository;
import com.mogikanlol.data.repository.PostRepository;
import com.mogikanlol.data.repository.ThreadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdatePostIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PostRepository postRepository;

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

        Post post = new Post()
                .setContent("content")
                .setThread(thread);
        postRepository.save(post);
    }

    @Test
    void successPath() throws Exception {
        String expectedJson = TestUtils.readResourceAsString("post/update-post-response-200.json");
        String requestJson = TestUtils.readResourceAsString("post/update-post-request.json");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json;charset=UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/posts/1",
                HttpMethod.PATCH,
                httpEntity,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        jsonExpectationsHelper.assertJsonEqual(expectedJson, response.getBody());
    }

    @Test
    void postNotFound() throws Exception {
        String requestJson = TestUtils.readResourceAsString("post/update-post-request.json");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-type", "application/json;charset=UTF-8");
        HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/posts/999",
                HttpMethod.PATCH,
                httpEntity,
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
