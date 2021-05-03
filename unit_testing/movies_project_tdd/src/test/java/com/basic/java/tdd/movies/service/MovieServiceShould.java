package com.basic.java.tdd.movies.service;


import com.basic.java.tdd.movies.data.MovieRepository;
import com.basic.java.tdd.movies.model.Genre;
import com.basic.java.tdd.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MovieServiceShould {

    MovieRepository movieRepository;
    MovieService movieService;
    @Before
    public void setup(){
        movieRepository = Mockito.mock(MovieRepository.class);
          movieService= new MovieService(movieRepository);
        Mockito.when(movieRepository.findAll()).thenReturn( Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                new Movie(4, "Super 8", 112, Genre.THRILLER),
                new Movie(5, "Scream", 111, Genre.HORROR),
                new Movie(6, "Home Alone", 103, Genre.COMEDY),
                new Movie(7, "Matrix", 136, Genre.ACTION)
        ));
    }


    @Test
    public void return_movies_by_genre() {
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);
        assertThat(getIntegersIds(movies), CoreMatchers.is(Arrays.asList(3,6)));
    }
    @Test
    public void return_movies_by_length() {
        Collection<Movie> movies = movieService.findMoviesByLength(119);
        assertThat(getIntegersIds(movies), CoreMatchers.is(Arrays.asList(2,3,4,5,6)));
    }

    private List<Integer> getIntegersIds(Collection<Movie> movies) {
        return movies.stream().map(movie -> movie.getId()).collect(Collectors.toList());
    }

}
