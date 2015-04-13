package repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import model.Movie;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryJpa implements MovieRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Movie find(String name) {
		return entityManager.find(Movie.class, name);
	}

	@Override
	public Collection<Movie> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		Root<Movie> movies = query.from(Movie.class);
		query.select(movies);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@Transactional
	public Movie save(Movie movie) {
		return entityManager.merge(movie);
	}
	
}
