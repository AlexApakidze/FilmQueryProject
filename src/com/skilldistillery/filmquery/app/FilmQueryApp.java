package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		try {1
//			app.test();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		try {
			app.launch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner sc = new Scanner(System.in);

		startUserInterface(sc);

		sc.close();
	}

	private void startUserInterface(Scanner sc) throws SQLException {
		boolean keepGoing = true;
		String input = "nothing";
		while (keepGoing) {

			printMenu();
			input = sc.nextLine();

			switch (input) {
			case "1":
				System.out.println("What is the ID of the film you would like to look up?");
				int filmId = sc.nextInt();
				sc.nextLine();
				if (filmId > 1000 || filmId < 1) {
					System.out.println("No film with that ID exists");
				} else {
					System.out.println(db.findFilmById(filmId));
				}
				continue;
			case "2":
				System.out.println("Search database for a keyword \ninput:");
				String searchTerm = sc.nextLine();
				System.out.println(db.findFilmByKeyword(searchTerm));
				continue;
			case "3":
				System.out.println("Thank you for using our app");
				sc.close();
				keepGoing = false;
				break;
			default:
				System.out.println("Invalid entry, please pick a number from 1-4.");
				continue;

			}
		}

	}

	private void printMenu() {
		System.out.println(" _____________________________________");
		System.out.println("|                  MENU               |");
		System.out.println("|                                     |");
		System.out.println("|1. Look up a film by its ID.         |");
		System.out.println("|                                     |");
		System.out.println("|2. Look up a film by a search keyword|");
		System.out.println("|                                     |");
		System.out.println("|3. Exit                              |");
		System.out.println("|_____________________________________|");
	}
}
