package repository;

import java.time.LocalDate;
import java.util.Collection;

import model.Rental;

public interface RentalRepository {
	Rental find(long id);
	Collection<Rental> findAll();
	Rental save(Rental rental);
	Collection<Rental> findAllBetween(LocalDate start, LocalDate end);
}
