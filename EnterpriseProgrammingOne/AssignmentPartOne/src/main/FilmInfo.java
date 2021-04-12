package main;

import java.util.ArrayList;

public interface FilmInfo {

	public Film getFilm(int FilmID);

	public void insertFilm(Film finfo);

	public void updateFilm(Film finfo);

	public void deleteFilm(int FilmID);

	public ArrayList<Film> listFilm();

	public ArrayList<Film> retrieveFilm(String searchStr);

}

class FilmInterface implements FilmInfo {

	@Override
	public void insertFilm(Film finfo) {
		FilmDAO dao = FilmDAO.getInstance();
		dao.insertFilm(finfo);
	}

	@Override
	public Film getFilm(int FilmID) {
		FilmDAO dao = FilmDAO.getInstance();
		return dao.getFilmByID(FilmID);

	}

	@Override
	public void updateFilm(Film finfo) {
		FilmDAO dao = FilmDAO.getInstance();
		dao.updateFilm(finfo);

	}

	@Override
	public void deleteFilm(int FilmID) {
		FilmDAO dao = FilmDAO.getInstance();
		dao.deleteFilm(FilmID);

	}

	@Override
	public ArrayList<Film> listFilm() {
		FilmDAO dao = FilmDAO.getInstance();
		return dao.getAllFilms();

	}

	@Override
	public ArrayList<Film> retrieveFilm(String searchStr) {
		FilmDAO dao = FilmDAO.getInstance();
		return dao.retrieveFilm(searchStr);
	}
}