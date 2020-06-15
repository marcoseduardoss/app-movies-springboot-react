package br.uece.eesdevops.introducaospringboot;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import br.uece.eesdevops.cearamovies.IntroducaoSpringBootApplication;
import br.uece.eesdevops.cearamovies.repository.MovieRepository;
import br.uece.eesdevops.cearamovies.repository.RatingRepository;

@AutoConfigureMockMvc
@SpringBootTest(classes = IntroducaoSpringBootApplication.class)
@DisplayName("Runs all tests for movie rating")
class MovieRatingTest {

    // region setup tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository movieRatingRepository;

    private static EmbeddedDatabase database;

    @BeforeAll
    static void init() throws IOException {
        database = new EmbeddedDatabase();
    }

    @AfterAll
    static void tearDown() throws IOException {
        database.stopServer();
    }

    @BeforeEach
    void beforeEach() {
        movieRatingRepository.deleteAllInBatch();
        movieRepository.deleteAllInBatch();
    }

    // endregion

    // region GET /movies-rating

    @Test
    @DisplayName("should get all movies rating with no results")
    void should_get_all_movies_rating_with_no_results() throws Exception {
        mockMvc.perform(get("/movies-rating"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
