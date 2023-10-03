package com.mogikanlol.data;

import com.mogikanlol.data.repository.BoardRepository;
import com.mogikanlol.data.repository.PostRepository;
import com.mogikanlol.data.repository.ThreadRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.JsonExpectationsHelper;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    protected static JsonExpectationsHelper jsonExpectationsHelper;

    @BeforeAll
    static void setUpJsonExpectationsHelper() {
        jsonExpectationsHelper = new JsonExpectationsHelper();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDatabase() {
        postRepository.deleteAll();
        threadRepository.deleteAll();
        boardRepository.deleteAll();

        jdbcTemplate.update("ALTER sequence thread_id_seq restart with 1");
        jdbcTemplate.update("ALTER sequence post_id_seq restart with 1");
    }
}
