package repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import model.Movie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import demo.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class MovieRepositoryJpaTest {
	@Inject
	private MovieRepository movieRepository;
	
	@Test
	public void findsAddedMovie() throws Exception {
		Movie expectedMovie = new Movie.Builder().withName("name").build();
		movieRepository.save(expectedMovie);
		
		Movie actualMovie = movieRepository.find("name");
		
		assertEquals(expectedMovie, actualMovie);
	}
	
	@Test
	public void addedMovieCanBeUpdated() throws Exception {
		Movie movie = new Movie.Builder().withName("name").build();
		movieRepository.save(movie);
		
		Movie addedMovie = movieRepository.find("name");
		Movie updatedMovie = movieRepository.save(addedMovie);
		
		assertTrue(movieRepository.findAll().contains(updatedMovie));
	}
}
