package com.cognizant.moviecrusier.dao;

import java.util.List;

import com.cognizant.moviecrusier.model.Movie;



public interface FavoritesDao {
	public void addFavoritesMovie(long userId, long movieId);

	public List<Movie> getAllFavoritesMovies(long userId) throws FavoritesEmptyException;

	public void removeFavoritesMovie(long userId, long movieId);
}
