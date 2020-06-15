package br.uece.eesdevops.introducaospringboot;

import br.uece.eesdevops.cearamovies.IntroducaoSpringBootApplication;
import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.repository.MovieRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.NestedServletException;

import static br.uece.eesdevops.introducaospringboot.Fakes.fakeMovieWithNoId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = IntroducaoSpringBootApplication.class)
@DisplayName("Runs all tests for movie registration")
class MovieTest {

    // region setup tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MovieRepository repository;

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
        repository.deleteAllInBatch();
    }

    // endregion

    // region GET /movies

    @Test
    @DisplayName("should get all movies with no results")
    void should_get_all_movies_with_no_results() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("should get all movies with one result")
    void should_get_all_movies_with_one_result() throws Exception {
        
    	Movie movie = fakeMovieWithNoId();

        movie = repository.save(movie);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].score", is(movie.getScore())))
                .andExpect(jsonPath("$[0].title", is(movie.getTitle())))
                .andExpect(jsonPath("$[0].protagonists", is(movie.getProtagonists())))
                .andExpect(jsonPath("$[0].producer", is(movie.getProducer())))
                .andExpect(jsonPath("$[0].synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$[0].year", is(movie.getYear())));
    }

    @Test
    @DisplayName("should get all movies with three result")
    void should_get_all_movies_with_three_result() throws Exception {
        List<Movie> movies = new ArrayList<>();

        movies.add(fakeMovieWithNoId());
        movies.add(fakeMovieWithNoId());
        movies.add(fakeMovieWithNoId());

        movies = repository.saveAll(movies);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                
                .andExpect(jsonPath("$[0].id", is(movies.get(2).getId())))
                .andExpect(jsonPath("$[0].score", is(movies.get(2).getScore())))
                .andExpect(jsonPath("$[0].title", is(movies.get(2).getTitle())))
                .andExpect(jsonPath("$[0].protagonists", is(movies.get(2).getProtagonists())))
                .andExpect(jsonPath("$[0].producer", is(movies.get(2).getProducer())))
                .andExpect(jsonPath("$[0].synopsis", is(movies.get(2).getSynopsis())))
                .andExpect(jsonPath("$[0].year", is(movies.get(2).getYear())))
                
                .andExpect(jsonPath("$[1].id", is(movies.get(1).getId())))
                .andExpect(jsonPath("$[1].score", is(movies.get(1).getScore())))
                .andExpect(jsonPath("$[1].title", is(movies.get(1).getTitle())))
                .andExpect(jsonPath("$[1].protagonists", is(movies.get(1).getProtagonists())))
                .andExpect(jsonPath("$[1].producer", is(movies.get(1).getProducer())))
                .andExpect(jsonPath("$[1].synopsis", is(movies.get(1).getSynopsis())))
                .andExpect(jsonPath("$[1].year", is(movies.get(1).getYear())))
                
                .andExpect(jsonPath("$[2].id", is(movies.get(2).getId())))
                .andExpect(jsonPath("$[2].score", is(movies.get(2).getScore())))
                .andExpect(jsonPath("$[2].title", is(movies.get(2).getTitle())))
                .andExpect(jsonPath("$[2].protagonists", is(movies.get(2).getProtagonists())))
                .andExpect(jsonPath("$[2].producer", is(movies.get(2).getProducer())))
                .andExpect(jsonPath("$[2].synopsis", is(movies.get(2).getSynopsis())))
                .andExpect(jsonPath("$[2].year", is(movies.get(2).getYear())));
    }

    // endregion

    // region GET /movies/{id}

    @Test
    @DisplayName("should get movie for ID successfully")
    void should_get_a_movie_for_id_successfully() throws Exception {
        Movie movie = fakeMovieWithNoId();

        movie = repository.save(movie);

        mockMvc.perform(get("/movies/" + movie.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(movie.getId())))
                .andExpect(jsonPath("$.score", is(movie.getScore())))
                .andExpect(jsonPath("$.title", is(movie.getTitle())))
                .andExpect(jsonPath("$.protagonists", is(movie.getProtagonists())))
                .andExpect(jsonPath("$.producer", is(movie.getProducer())))
                .andExpect(jsonPath("$.synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.year", is(movie.getYear()))); 
    
    }

    @Test
    @DisplayName("should not get movie for ID when it does not exist")
    void should_not_get_a_movie_for_id_when_it_does_not_exist() throws Exception {
        
    	Movie movie = fakeMovieWithNoId();

        repository.save(movie);

        int movieId = 9999;

        mockMvc.perform(get("/movies/" + movieId))
                .andExpect(status().isNotFound());
    }

    // endregion

    // region POST /movies

    @Test
    @DisplayName("should save a new movie successfully")
    void should_save_new_movie_successfully() throws Exception {
        Movie movie = mapper.readValue(Payloads.newMovieRequest(), Movie.class);

        MockHttpServletRequestBuilder request = post("/movies")
                .content(Payloads.newMovieRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(notNullValue())))                
                .andExpect(jsonPath("$.score", is(movie.getScore())))
                .andExpect(jsonPath("$.title", is(movie.getTitle())))
                .andExpect(jsonPath("$.protagonists", is(movie.getProtagonists())))
                .andExpect(jsonPath("$.producer", is(movie.getProducer())))
                .andExpect(jsonPath("$.synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.year", is(movie.getYear()))); 
    }

    @Test
    @DisplayName("should not save a new movie when an error occurs")
    void should_not_save_new_movie_when_an_error_occurs() throws Exception {
        Movie movie = mapper.readValue(Payloads.newMovieRequest(), Movie.class);

        repository.save(movie);

        MockHttpServletRequestBuilder request = post("/movies")
                .content(Payloads.newMovieRequest())
                .contentType(MediaType.APPLICATION_JSON);

        assertThrows(
                NestedServletException.class,
                () -> mockMvc.perform(request),
                "ERROR: duplicate key value violates unique constraint \"movie_isbn_key\"\n" +
                        "  Detalhe: Key (isbn)=(0321125215) already exists."
        );
    }

    //endregion

    // region PUT /movies/{id}

    @Test
    @DisplayName("should update a movie successfully")
    void should_update_a_movie_successfully() throws Exception {
        Movie movie = mapper.readValue(Payloads.updateMovieRequest(), Movie.class);

        movie = repository.save(movie);

        MockHttpServletRequestBuilder request = put("/movies/" + movie.getId())
                .content(Payloads.updateMovieRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(movie.getId())))
                .andExpect(jsonPath("$.score", is(movie.getScore())))
                .andExpect(jsonPath("$.title", is(movie.getTitle())))
                .andExpect(jsonPath("$.protagonists", is(movie.getProtagonists())))
                .andExpect(jsonPath("$.producer", is(movie.getProducer())))
                .andExpect(jsonPath("$.synopsis", is(movie.getSynopsis())))
                .andExpect(jsonPath("$.year", is(movie.getYear()))); 
    }

    @Test
    @DisplayName("should not update a movie when it does not exist")
    void should_not_update_a_movie_when_it_does_not_exist() throws Exception {
        MockHttpServletRequestBuilder request = put("/movies/" + 9999)
                .content(Payloads.newMovieRequest())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    //endregion

}
