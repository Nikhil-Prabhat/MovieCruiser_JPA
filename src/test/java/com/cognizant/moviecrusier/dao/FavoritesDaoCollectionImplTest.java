package com.cognizant.moviecrusier.dao;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.moviecrusier.BeanUtil;
import com.cognizant.moviecrusier.model.Movie;



public class FavoritesDaoCollectionImplTest {
	private static FavoritesDao favoritesDao;
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		favoritesDao = BeanUtil.getBean(FavoritesDao.class);
		Scanner sc = new Scanner(System.in);
		String choice;
		int movieAdded = 0;
		
		do {
			System.out.println("Menu");
			System.out.println("****************************************");
			System.out.println("1. Add Favorites Movie");
			System.out.println("2. Get All Favorites Movies");
			System.out.println("3. Remove Favorites Movie");
			System.out.println("4. Exit");
			System.out.println("****************************************");

			choice = sc.nextLine();
			System.out.println("****************************************");

			switch (choice) {
			case "1": {
				testAddFavoritesMovie();
				System.out.println("Movie 2 is added in User Id 1. Enter 2 to test.");
				System.out.println("****************************************");
				movieAdded++;
				break;
			}
			case "2": {
				testGetAllFavoritesMovies();
				break;
			}
			case "3": {
				if (movieAdded != 0) {
					testRemoveFavoritesMovie();
					movieAdded--;
					System.out.println("Movie 2 is removed in User Id 1. Enter 2 to test.");
					System.out.println("****************************************");
				} else {
					System.out.println("Favorites Empty !! First add something to Favorites.");
					System.out.println("****************************************");
				}
				break;
			}
			case "4": {
				break;
			}
			default: {
				System.out.println("Enter valid choice");
				System.out.println("****************************************");
			}
			}
		} while (!choice.equals("4"));

		sc.close();
	}

	public static void testAddFavoritesMovie() {
		favoritesDao.addFavoritesMovie(1, 2);
	}

	public static void testGetAllFavoritesMovies() {
		try {
			List<Movie> movieList = favoritesDao.getAllFavoritesMovies(1);
			System.out.println("User Id 1 Favorites is displayed");
			System.out.println("****************************************");
			System.out.println(String.format("%-3s %-20s %-15s %-8s %-30s %-18s %-15s", "Id", 
					"Title", "Bos Office", "Active", "Date of Launch", "Genre", "Has Teaser"));
			System.out.println();

			for (Movie movie : movieList) {
				System.out.println(movie);
			}
			System.out.println("****************************************");
		} catch (FavoritesEmptyException e) {
			System.out.println("Favorites Empty !!");
			System.out.println("****************************************");
		}
	}

	public static void testRemoveFavoritesMovie() {
		favoritesDao.removeFavoritesMovie(1, 2);
	}

}
