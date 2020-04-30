package com.cognizant.moviecrusier.service;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.moviecrusier.BeanUtil;
import com.cognizant.moviecrusier.model.Movie;
import com.cognizant.moviecrusier.util.DateUtil;

public class MovieServiceTest {
	static {
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");
		System.setProperty(org.slf4j.impl.SimpleLogger.LOG_FILE_KEY, "System.out");
		System.setProperty(org.slf4j.impl.SimpleLogger.SHOW_DATE_TIME_KEY, "false");
		System.setProperty(org.slf4j.impl.SimpleLogger.SHOW_LOG_NAME_KEY, "false");
		;
		System.setProperty(org.slf4j.impl.SimpleLogger.SHOW_SHORT_LOG_NAME_KEY, "false");
		System.setProperty(org.slf4j.impl.SimpleLogger.SHOW_THREAD_NAME_KEY, "false");
		System.setProperty(org.slf4j.impl.SimpleLogger.LEVEL_IN_BRACKETS_KEY, "true");
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceTest.class);
	private static MovieService movieService;

	public static void main(String[] args) {
		LOGGER.info("Start Main");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		movieService = BeanUtil.getBean(MovieService.class);
		Scanner sc = new Scanner(System.in);
		String choice;
		do {
			LOGGER.debug("Menu");
			LOGGER.debug("****************************************");
			LOGGER.debug("1. Admin");
			LOGGER.debug("2. Customer");
			LOGGER.debug("3. Exit");
			LOGGER.debug("Enter You Choice...");
			LOGGER.debug("****************************************");

			choice = sc.nextLine();
			LOGGER.debug("****************************************");

			switch (choice) {
			case "1": {
				String adminChoice;
				do {
					LOGGER.debug("Admin Menu");
					LOGGER.debug("****************************************");
					LOGGER.debug("1. Get Movie List");
					LOGGER.debug("2. Add Movie");
					LOGGER.debug("3. Modify Movie");
					LOGGER.debug("4. Get Movie");
					LOGGER.debug("6. Main Menu");
					LOGGER.debug("Enter You Choice...");
					LOGGER.debug("****************************************");

					adminChoice = sc.nextLine();
					LOGGER.debug("****************************************");

					switch (adminChoice) {
					case "1": {
						LOGGER.debug("Admin Movie List");

						testGetMovieListAdmin();
						LOGGER.debug("****************************************");
						break;
					}
					case "2":
						testaddMovie();
						LOGGER.debug("Movie 1 is added !! Enter 3 to display the changes.");
						LOGGER.debug("****************************************");
						break;
					case "3": {
						testModifyMovie();
						LOGGER.debug("Movie 1 is modified !! Enter 3 to display the changes.");
						LOGGER.debug("****************************************");
						break;
					}
					case "4": {
						LOGGER.debug("Movie 1 is displayed !!");
						testGetMovie();
						LOGGER.debug("****************************************");
						break;
					}
					case "5": {
						testCount();
						LOGGER.debug("****************************************");
						break;
					}
					case "6": {
						break;
					}
					default: {
						LOGGER.debug("Enter valid choice");
						LOGGER.debug("****************************************");
					}
					}
				} while (!adminChoice.equals("6"));
				break;
			}
			case "2": {
				LOGGER.debug("Customer Movie List");

				testGetMovieListCustomer();
				LOGGER.debug("****************************************");
				break;
			}
			case "3": {
				break;
			}
			default: {
				LOGGER.debug("Enter valid choice");
				LOGGER.debug("****************************************");
			}
			}
		} while (!choice.equals("3"));

		sc.close();
		LOGGER.info("End Main");
	}

	public static void testGetMovieListAdmin() {
		List<Movie> movieList = movieService.getMovieListAdmin();
		LOGGER.debug("****************************************");
		LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s " + "%-15s", "Id", "Title", "Bos Office",
				"Active", "Date of Launch", "Genre", "Has Teaser"));
		LOGGER.debug("");
		for (int i = 0; i < movieList.size(); i++) {
			LOGGER.debug(movieList.get(i).toString());
		}
	}

	public static void testGetMovieListCustomer() {
		List<Movie> movieList = movieService.getMovieListCustomer();
		LOGGER.debug("****************************************");
		LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s %-15s", "Id", "Title", "Bos Office", "Active",
				"Date of Launch", "Genre", "Has Teaser"));
		LOGGER.debug("");
		for (int i = 0; i < movieList.size(); i++) {
			LOGGER.debug(movieList.get(i).toString());
		}
	}

	public static void testModifyMovie() {
		Movie movie = movieService.getMovie(1);
		movie.setTitle("The Martian");
		movieService.modifyMovie(movie);
	}

	public static void testGetMovie() {
		LOGGER.debug("****************************************");
		LOGGER.debug(String.format("%-3s %-20s %-15s %-8s %-30s %-18s" + " %-15s", "Id", "Title", "Bos Office",
				"Active", "Date of Launch", "Genre", "Has Teaser"));
		LOGGER.debug("");
		LOGGER.debug(movieService.getMovie(1).toString());
	}

	public static void testaddMovie() {
		Movie movie = new Movie();
		movie.setTitle("Extraction");
		movie.setHasTeaser(true);
		movie.setGenre("Thriller");
		movie.setDateOfLaunch(DateUtil.convertToDate("16/08/2022"));
		movie.setBoxOffice("$65,000,000");
		movie.setActive(true);
		movieService.addMovie(movie);
	}

	public static void testCount() {
		LOGGER.debug("Movie Count = {}", movieService.totalMovie());
	}
}
