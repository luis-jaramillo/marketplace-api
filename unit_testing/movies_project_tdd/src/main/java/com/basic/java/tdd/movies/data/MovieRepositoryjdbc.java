package com.basic.java.tdd.movies.data;

import com.basic.java.tdd.movies.model.Genre;
import com.basic.java.tdd.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MovieRepositoryjdbc implements MovieRepository {
    private static RowMapper<Movie> movieMapper = new RowMapper<Movie>() {
        @Override
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Movie(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("minutes"),
                    Genre.valueOf(resultSet.getString("genre")));
        }
    };
    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryjdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findById(long id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("select * from movies where id = ? ", args, movieMapper);
    }

    @Override
    public Collection<Movie> findAll() {


        return jdbcTemplate.query("select * from movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        jdbcTemplate.update("insert into movies(name, minutes, genre) values (?,?,?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString());
    }
}
