package com.cognizant.moviecrusier.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.moviecrusier.model.Movie;

@Repository
public interface MovieRepositary extends JpaRepository<Movie, Long> {

	List<Movie> findByActiveTrueAndDateOfLaunchBefore(Date date);

	Optional<Movie> findById(long id);
}
