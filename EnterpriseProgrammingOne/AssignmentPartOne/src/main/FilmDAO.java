package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "jacksonl";
    String password = "bortHwik9";
    // Note none default port used, 6306 not 3306
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;
    private static FilmDAO dao;
    
	private FilmDAO() {}

	public static synchronized FilmDAO getInstance() {
		if(dao == null) {
			dao = new FilmDAO();
		}
		System.out.println(dao);
		return dao;
	}
	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(Exception se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(Exception se) { System.out.println(se); }
	   return allFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   
   public void insertFilm(Film finfo) {
	   openConnection();
	   oneFilm = null;
	   try{
		   
		    String query = "insert into films(id, title, year, director, stars, review)" + "values (?, ?, ?, ?, ?, ?)";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setInt(1, finfo.getId());
		    preparedStatement.setString(2, finfo.getTitle());
		    preparedStatement.setInt(3, finfo.getYear());
		    preparedStatement.setString(4, finfo.getDirector());
		    preparedStatement.setString(5, finfo.getStars());
		    preparedStatement.setString(6, finfo.getReview());
		    preparedStatement.execute();

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

   }
   
   public void updateFilm(Film finfo) {
	   openConnection();
	   oneFilm = null;
	   try{
		   
		    String query = "update films set title=?, year=?, director=?, stars=?, review=? where id=?";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setString(1, finfo.getTitle());
		    preparedStatement.setInt(2, finfo.getYear());
		    preparedStatement.setString(3, finfo.getDirector());
		    preparedStatement.setString(4, finfo.getStars());
		    preparedStatement.setString(5, finfo.getReview());
		    preparedStatement.setInt(6, finfo.getId());
		    preparedStatement.execute();

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }
   }
   
   public void deleteFilm(int FilmID) {
	   openConnection();
	   oneFilm = null;
	   try{
		   
		    String query = "delete from films where id = ?;";
		    PreparedStatement preparedStatement = conn.prepareStatement(query);
		    preparedStatement.setInt(1, FilmID);
		    preparedStatement.execute();

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   
  }
   
   
   public ArrayList<Film> retrieveFilm(String searchStr) {
	   ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where title like '%" + searchStr + "%';";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }
   
   
}
