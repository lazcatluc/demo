package demo;

import model.Movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import api.v1.Movies;
import repository.MovieRepository;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackageClasses=Movie.class)
@ComponentScan(basePackageClasses= { MovieRepository.class, Movies.class })
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
