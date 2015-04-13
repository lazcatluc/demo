package api.v1;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import model.Movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import demo.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
@IntegrationTest
public class MoviesTest {
	
	final String BASE_URL = "http://localhost:8080/";
	
	@Test
	public void showsAddedMovie() throws Exception {
		String movieId = "movieFromController";
		Movie movie = new Movie.Builder().withName(movieId).build();
		String jsonedMovie = "{\"name\":\""+movieId+"\"}";
		
		RestTemplate rest = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonedMovie, headers);
		rest.postForObject(BASE_URL + "api/v1/movie", entity, Movie.class, Collections.emptyMap());
		Movie retrievedMovie = rest.getForObject(BASE_URL + "api/v1/movie/" + movieId, Movie.class);
		
		assertEquals(movie, retrievedMovie);
	}
	
	@Test
	public void nonExistentMovieThrows404() throws Exception {
		RestTemplate rest = new TestRestTemplate();
		ResponseEntity<Movie> retrievedMovie = rest.getForEntity(BASE_URL + "api/v1/movie/bogusId", Movie.class);
		
		assertEquals(HttpStatus.NOT_FOUND, retrievedMovie.getStatusCode());
	}
	
	@Test
	public void badMovieJsonThrowsBadRequest() throws Exception {
		String jsonedMovie = "foo-bar json";
		RestTemplate rest = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonedMovie, headers);
		ResponseEntity<Movie> response = rest.postForEntity(BASE_URL + "api/v1/movie", entity, Movie.class, Collections.emptyMap());
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
}
