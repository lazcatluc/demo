package repository;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import model.Movie;
import model.Rental;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import demo.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class RentalRepositoryJpaTest {
	@Inject
	private RentalRepository rentalRepository;
	@Inject
	private MovieRepository movieRepository;
	private Movie movie;
	
	@Before
	public void setUp() {
		movie = new Movie.Builder().build();
		movieRepository.save(movie);
	}
	
	@Test
	public void findsAddedRental() throws Exception {
		Rental expectedRental = new Rental.Builder().build();
		rentalRepository.save(expectedRental);
		assertNotEquals(0, expectedRental.getId());
		
		Rental actualRental = rentalRepository.find(expectedRental.getId());
		assertEquals(expectedRental, actualRental);
	}
	
	@Test
	public void addedRentalCanBeUpdated() throws Exception {
		Rental original = new Rental.Builder().build();
		rentalRepository.save(original);
		long id = original.getId();
		
		Rental updated = new Rental.Builder()
				.withId(id)
				.starting(LocalDate.now())
				.ending(LocalDate.now().plusDays(1))
			.build();
		rentalRepository.save(updated);
		
		assertEquals(updated, rentalRepository.find(id));
	}
	
	@Test
	public void looksUpOnDate() throws Exception {
		Rental original = new Rental.Builder().starting(LocalDate.now()).build();
		rentalRepository.save(original);
		
		Collection<Rental> allRentalsBetween = rentalRepository.findAllBetween(LocalDate.now().minusDays(1), 
				LocalDate.now().plusDays(1));
		assertTrue(allRentalsBetween.contains(original));
		
		allRentalsBetween = rentalRepository.findAllBetween(LocalDate.now().plusDays(1),
				LocalDate.now().plusDays(2));
		assertFalse(allRentalsBetween.contains(original));
	}
}
