package api.v1;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import model.Movie;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import repository.MovieRepository;

@RestController
@RequestMapping("/api/v1")
public class Movies {
	@Inject
	private MovieRepository movieRepository;
	@Inject
	private HttpServletResponse response;
	
	@RequestMapping(value = "movie/{name}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie getMovie(@PathVariable String name) {
		Movie movie = movieRepository.find(name);
		if (movie == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return movie;
	}
	
	@RequestMapping(value = "movie", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie save(@RequestBody Movie movie) {
		return movieRepository.save(movie);
	}
}
