package model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

@Entity
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Type(type = "model.LocalDateUserType")
	private LocalDate start;
	@Type(type = "model.LocalDateUserType")
	private LocalDate end;
	@ManyToOne
	private Movie movie;

	/**
	 * Used by Hibernate
	 */
	@SuppressWarnings("unused")
	private Rental() {
	}

	public Rental(Builder builder) {
		this.id = builder.id;
		this.start = builder.start;
		this.end = builder.end;
		this.movie = builder.movie;
	}

	public long getId() {
		return id;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public Movie getMovie() {
		return movie;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static class Builder {
		private long id;
		private LocalDate start = LocalDate.of(1970, 1, 1);
		private LocalDate end = LocalDate.of(1970, 1, 2);
		private Movie movie = new Movie.Builder().build();

		public Rental build() {
			return new Rental(this);
		}
		
		public Builder starting(LocalDate start) {
			this.start = start;
			return this;
		}
		
		public Builder ending(LocalDate end) {
			this.end = end;
			return this;
		}

		public Builder withId(long id) {
			this.id = id;
			return this;
		}
	}
}
