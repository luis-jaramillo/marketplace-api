package com.basic.java.tdd.movies.data;


import com.basic.java.tdd.movies.model.Genre;
import com.basic.java.tdd.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

public class MovieRepositoryjdbcIntegrationTest {

    private MovieRepositoryjdbc movieRepositoryjdbc;
    DataSource dataSource;
    @Before
    public void setUp() throws SQLException{
          dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         movieRepositoryjdbc = new MovieRepositoryjdbc(jdbcTemplate);
    }

    @Test
    public void load_all_movies()  {
        Collection<Movie> movies = movieRepositoryjdbc.findAll();
        assertThat(movies, CoreMatchers.is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void load_movie_by_id(){
        Movie movie = movieRepositoryjdbc.findById(2);
        assertThat(movie, CoreMatchers.is( new Movie(2, "Memento", 113, Genre.THRILLER)));
    }

    @Test
    public void insert_movie(){
        Movie movie = new Movie( "Super 8", 112, Genre.THRILLER);
        movieRepositoryjdbc.saveOrUpdate(movie);
        Movie movieLoaded = movieRepositoryjdbc.findById(4);
        assertThat(movieLoaded, CoreMatchers.is( new Movie( 4,"Super 8", 112, Genre.THRILLER)));
    }

    @After
    public void tearDown()throws  Exception{
        final Statement statement = dataSource.getConnection().createStatement();
        statement.execute("drop all objects delete files");
    }

}