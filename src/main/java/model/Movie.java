package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String name;
	
	/**
	 * Used by Hibernate
	 */
	@SuppressWarnings("unused")
	private Movie() {
	}
	
	public Movie(Builder builder) {
		this.name = builder.name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
		private String name = "undefined";
		
		public Movie build() {
			return new Movie(this);
		}
		
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
	}
	
}
