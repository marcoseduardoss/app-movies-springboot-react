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


//    @Test
//    @Transactional
//    @DisplayName("should get all movies with three result")
//    void should_get_all_movies_with_three_result() throws Exception {
//        List<Rating> list = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            Movie movie = movieRepository.save(fakeMovieWithNoId());
//            Student student = studentRepository.save(fakeStudentWithNoId());
//            list.add(fakeMovieRatingWithNoId(movie, student));
//        }
//
//        movieRatingRepository.saveAll(list);
//
//        mockMvc.perform(get("/movies-rating"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)));
//    }
//
//    // endregion
//
//    // region GET /movies-rating/{id}
//
//    @Test
//    @Transactional
//    @DisplayName("should get a movie rating for ID successfully")
//    void should_get_a_movie_rating_for_id_successfully() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//        Student student = studentRepository.save(fakeStudentWithNoId());
//        Rating movieRating = movieRatingRepository.save(
//                fakeMovieRatingWithNoId(movie, student)
//        );
//
//        mockMvc.perform(get("/movies-rating/" + movieRating.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(movieRating.getId())));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("should not get movie for ID when it does not exist")
//    void should_not_get_a_movie_for_id_when_it_does_not_exist() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//        Student student = studentRepository.save(fakeStudentWithNoId());
//        movieRatingRepository.save(
//                fakeMovieRatingWithNoId(movie, student)
//        );
//
//        mockMvc.perform(get("/movies-rating/" + 9999))
//                .andExpect(status().isNotFound());
//    }
//
//    // endregion
//
//    // region POST /movies-rating
//
//    @Test
//    @Transactional
//    @DisplayName("should lent a movie successfully")
//    void should_lent_a_movie_successfully() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//        Student student = studentRepository.save(fakeStudentWithNoId());
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("movie", movie.getId());
//        jsonObject.put("student", student.getId());
//
//        MockHttpServletRequestBuilder request = post("/movies-rating")
//                .content(jsonObject.toString())
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(request)
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", is(notNullValue())))
//                .andExpect(jsonPath("$.status", is(MovieRatingStatus.LENT.toString())));
//    }
//
//    @Test
//    @DisplayName("should not lent a movie when movie or student is null")
//    void should_not_lent_a_movie_when_movie_or_student_is_null() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//
//        mockMvc.perform(
//                post("/movies-rating")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isBadRequest());
//
//        jsonObject.put("movie", null);
//        jsonObject.put("student", null);
//
//        mockMvc.perform(
//                post("/movies-rating")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isBadRequest());
//
//        jsonObject.put("movie", 1);
//        jsonObject.put("student", null);
//
//        mockMvc.perform(
//                post("/movies-rating")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isBadRequest());
//
//    }
//
//    @Test
//    @DisplayName("should not lent a movie when movie does not exist")
//    void should_not_lent_a_movie_when_movie_does_not_exist() throws Exception {
//        Student student = studentRepository.save(fakeStudentWithNoId());
//
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("movie", 1);
//        jsonObject.put("student", student.getId());
//
//        mockMvc.perform(
//                post("/movies-rating")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @DisplayName("should not lent a movie when student does not exist")
//    void should_not_lent_a_movie_when_student_does_not_exist() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("movie", movie.getId());
//        jsonObject.put("student", 9999);
//
//        mockMvc.perform(
//                post("/movies-rating")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("should not lent a movie when movie is already lent")
//    void should_not_lent_a_movie_when_movie_is_already_lent() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//        Student student = studentRepository.save(fakeStudentWithNoId());
//        movieRatingRepository.save(fakeMovieRatingWithNoId(movie, student));
//
//        JSONObject jsonObject = new JSONObject();
//
//        jsonObject.put("movie", movie.getId());
//        jsonObject.put("student", student.getId());
//
//        mockMvc.perform(
//                post("/movies-rating")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict());
//    }
//
//    // endregion
//
//    // region PATCH /movies-rating{id}/status
//
//    @Test
//    @Transactional
//    @DisplayName("should change movie rating status to returned successfully")
//    void should_change_movie_rating_status_to_returned_successfully() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//        Student student = studentRepository.save(fakeStudentWithNoId());
//        Rating movieRating = movieRatingRepository.save(
//                fakeMovieRatingWithNoId(movie, student)
//        );
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", MovieRatingStatus.RETURNED.toString());
//
//        mockMvc.perform(
//                patch("/movies-rating/" + movieRating.getId() + "/status")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(movieRating.getId())))
//                .andExpect(jsonPath("$.status", is(MovieRatingStatus.RETURNED.toString())));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("should change movie rating status to lent successfully")
//    void should_change_movie_rating_status_to_lent_successfully() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//        Student student = studentRepository.save(fakeStudentWithNoId());
//        Rating movieRating = movieRatingRepository.save(
//                fakeMovieRatingWithNoId(movie, student, MovieRatingStatus.RETURNED)
//        );
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", MovieRatingStatus.LENT.toString());
//
//        mockMvc.perform(
//                patch("/movies-rating/" + movieRating.getId() + "/status")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(movieRating.getId())))
//                .andExpect(jsonPath("$.status", is(MovieRatingStatus.LENT.toString())));
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("should not change movie rating status when it does not exist")
//    void should_not_change_movie_rating_status_when_it_does_exist() throws Exception {
//        Movie movie = movieRepository.save(fakeMovieWithNoId());
//
//        movieRatingRepository.save(
//                fakeMovieRatingWithNoId(
//                        movie,
//                        studentRepository.save(fakeStudentWithNoId())
//                )
//        );
//
//        Rating movieRating = movieRatingRepository.save(
//                fakeMovieRatingWithNoId(
//                        movie,
//                        studentRepository.save(fakeStudentWithNoId()),
//                        MovieRatingStatus.RETURNED
//                )
//        );
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", MovieRatingStatus.LENT.toString());
//
//        mockMvc.perform(
//                patch("/movies-rating/" + movieRating.getId() + "/status")
//                        .content(jsonObject.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict());
//    }
//
//    // endregion

}
