package br.uece.eesdevops.introducaospringboot;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

import br.uece.eesdevops.cearamovies.IntroducaoSpringBootApplication;

class Payloads {

    private static ClassLoader classLoader;

    static {
        classLoader = IntroducaoSpringBootApplication.class.getClassLoader();
    }

    static String newMovieRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("new-movie-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File new-movie-request.json could not be loaded.");
        }
    }

    static String updateMovieRequest() throws IOException {
        InputStream stream = classLoader.getResourceAsStream("update-movie-request.json");
        if (stream != null) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        } else {
            throw new IllegalArgumentException("File update-movie-request.json could not be loaded.");
        }
    }

}
