package com.cognizant.moviecrusier.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecrusier.model.Favorites;
import com.cognizant.moviecrusier.model.Movie;
@Repository
public class FavoritesDaoCollectionImpl implements FavoritesDao {
	@Autowired
	private HashMap<Long, Favorites> userFavorites;
	@Autowired
	private MovieDao movieDao;
	public void addFavoritesMovie(long userId, long movieId) {
		Movie movie = movieDao.getMovie(movieId);

		if (userFavorites.containsKey(userId)) {
			List<Movie> movieList = userFavorites.get(userId).getMovieList();
			movieList.add(movie);
		} else {
			Favorites favorites = new Favorites(new ArrayList<Movie>(), 0);
			favorites.getMovieList().add(movie);
			userFavorites.put(userId, favorites);
		}
	}

	public List<Movie> getAllFavoritesMovies(long userId) throws FavoritesEmptyException {
		if (userFavorites.containsKey(userId)) {
			List<Movie> movieList = userFavorites.get(userId).getMovieList();
			if (movieList.isEmpty()) {
				throw (new FavoritesEmptyException());
			} else {
				userFavorites.get(userId).setNoOfFavorites(movieList.size());
			}
			return movieList;
		} else {
			throw (new FavoritesEmptyException());
		}
	}

	public void removeFavoritesMovie(long userId, long movieId) {
		List<Movie> movieList = userFavorites.get(userId).getMovieList();
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getId() == movieId) {
				movieList.remove(i);
				break;
			}
		}
	}

}
