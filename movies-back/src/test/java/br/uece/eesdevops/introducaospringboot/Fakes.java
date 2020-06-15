package br.uece.eesdevops.introducaospringboot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import br.uece.eesdevops.cearamovies.domain.entity.Movie;
import br.uece.eesdevops.cearamovies.domain.entity.Rating;

final class Fakes {

    private Fakes() { }

    static Movie fakeMovie() {
        Movie movie = new Movie();
        movie.setId(1L);        
        movie.setScore(new Random().nextInt(5));
        movie.setTitle("Fake Movie");
        movie.setProtagonists("Fake Protagonist");
        movie.setProducer("Fake producer");
        movie.setSynopsis("Fake synopsis");        
        movie.setYear(1999);
        return movie;
    }
    
    public static void main(String[] args) {
		System.out.println(new Gson().toJson(fakeMovie()));	
	}
    

    
    public static Movie fakeMovieWithNoId() {
        Movie movie = fakeMovie();
        movie.setId(null);
        return movie;
    }

    private static String generateFakeEnrollment() {
        Random random = new Random();
        StringBuilder isbn = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            isbn.append(random.nextInt(10));
        }

        return isbn.toString();
    }

    static Rating fakeMovieRating() {
    	return fakeMovieRating(1L);
    }
    
    static Rating fakeMovieRating(Long id) {
        Rating movieRating = new Rating();
        movieRating.setId(id);
        movieRating.setMovie(fakeMovie());
        movieRating.setAuthor("Facke Author");
        return movieRating;
    }

    static Rating fakeMovieRatingWithNoId() {
        Rating movieRating = fakeMovieRating();
        movieRating.setId(null);
        return movieRating;
    }

    static Rating fakeMovieRating(Movie movie) {
        Rating movieRating = new Rating();
        movieRating.setId(1L);
        movieRating.setMovie(movie);
        movieRating.setComment("Great");
        movieRating.setAuthor("Claudio");
        movieRating.setScore(new Random(5).nextInt());
        return movieRating;
    }


    static List<Rating> fakeMovieRatingList() {
        List<Rating> movieRatings = new ArrayList<>();
        movieRatings.add(fakeMovieRating());
        return movieRatings;
    }

    static List<Rating> fakeMovieRatingListWithLentAndReturned() {
        List<Rating> movieRatings = new ArrayList<>();
        movieRatings.add(fakeMovieRating(1L));
        movieRatings.add(fakeMovieRating(2L));
        return movieRatings;
    }
    
//    private static String generateFakeIsbn() {
//        Random random = new Random();
//        StringBuilder isbn = new StringBuilder();
//
//        for (int i = 0; i < 10; i++) {
//            isbn.append(random.nextInt(10));
//        }
//
//        return isbn.toString();
//    }

}
