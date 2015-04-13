package repository;

import java.util.Collection;

import model.Movie;

public interface MovieRepository {
	Movie find(String name);
	Collection<Movie> findAll();
	Movie save(Movie movie);
}
