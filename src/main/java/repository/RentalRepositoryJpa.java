package repository;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import model.Rental;

import org.springframework.stereotype.Repository;

@Repository
public class RentalRepositoryJpa implements RentalRepository {
	@PersistenceContext
	private EntityManager entityManager;	

	@Override
	public Rental find(long id) {
		return entityManager.find(Rental.class, id);
	}

	@Override
	public Collection<Rental> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Rental> query = cb.createQuery(Rental.class);
		Root<Rental> rentals = query.from(Rental.class);
		query.select(rentals);
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public Collection<Rental> findAllBetween(LocalDate start, LocalDate end) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Rental> query = cb.createQuery(Rental.class);
		Root<Rental> rentals = query.from(Rental.class);
		query.select(rentals);
		query.where(cb.between(rentals.get("start"), start, end));
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	@Transactional
	public Rental save(Rental rental) {
		if (rental.getId() == 0L) {
			entityManager.persist(rental);
		}		
		return entityManager.merge(rental);

	}

}
