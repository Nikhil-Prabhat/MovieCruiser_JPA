package com.cognizant.moviecrusier.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecrusier.model.Movie;
@Repository
@Qualifier("movieDao")
public class MovieDaoCollectionImpl implements MovieDao {
	@Autowired
	private List<Movie> movieList;

	public List<Movie> getMovieListAdmin() {
		return movieList;
	}

	public Movie getMovie(long movieId) {
		Movie movie = null;
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getId() == movieId) {
				movie = movieList.get(i);
				break;
			}
		}
		return movie;
	}

	public List<Movie> getMovieListCustomer() {
		List<Movie> customerMovieList = new ArrayList<Movie>();

		for (int i = 0; i < movieList.size(); i++) {
			Movie movie = movieList.get(i);
			if ((movie.getDateOfLaunch().equals(new Date()) || movie.getDateOfLaunch().before(new Date()))
					&& movie.isActive()) {
				customerMovieList.add(movie);
			}
		}

		return customerMovieList;
	}

	public void modifyMovie(Movie movie) {
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).equals(movie)) {
				movieList.set(i, movie);
				break;
			}
		}
	}

}
