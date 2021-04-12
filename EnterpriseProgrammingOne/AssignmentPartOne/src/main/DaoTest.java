package main;

import java.util.ArrayList;

public class DaoTest {

	public static void main(String[] args) {
		Film film = new Film();

		film.setId(1);
		film.setDirector("James");
		film.setReview("Excellent");
		film.setStars("Jennifer");
		film.setTitle("test");
		film.setYear(2021);

		FilmInterface fInterface = new FilmInterface();
		System.out.println(fInterface.listFilm());
		ArrayList<Film> allFilms = fInterface.listFilm();
		for (Film f : allFilms) {
			System.out.println(f.getTitle());
		}

		Film getFilm = fInterface.getFilm(10001);
		System.out.println(getFilm.getTitle());

		fInterface.insertFilm(film);

		Film insertFilm = fInterface.getFilm(1);
		System.out.println(insertFilm.getTitle());

		film.setTitle("Random");
		fInterface.updateFilm(film);
		System.out.println(film.getTitle());

		ArrayList<Film> searchFilms = fInterface.retrieveFilm("Year");
		for (Film f : searchFilms) {
			System.out.println(f.getTitle());
		}

		fInterface.deleteFilm(1);

		System.out.println(fInterface.getFilm(1));
	}
}
