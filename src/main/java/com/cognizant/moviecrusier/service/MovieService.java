package com.cognizant.moviecrusier.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.moviecrusier.dao.MovieRepositary;
import com.cognizant.moviecrusier.model.Movie;

@Service
public class MovieService {
	@Autowired
	private MovieRepositary movieRepositary;

	@Transactional
	public Movie getMovie(long movieId) {
		return movieRepositary.findOne(movieId);
	}

	@Transactional
	public List<Movie> getMovieListCustomer() {
		return movieRepositary.findByActiveTrueAndDateOfLaunchBefore(new Date());
	}

	@Transactional
	public List<Movie> getMovieListAdmin() {
		return movieRepositary.findAll();
	}

	@Transactional
	public void modifyMovie(Movie movie) {
		movieRepositary.save(movie);
	}

	@Transactional
	public void addMovie(Movie movie) {
		movieRepositary.save(movie);
	}

	@Transactional
	public void deleteMovie(Long movieId) {
		movieRepositary.delete(movieId);
	}

	@Transactional
	public long totalMovie() {
		return movieRepositary.count();
	}
}