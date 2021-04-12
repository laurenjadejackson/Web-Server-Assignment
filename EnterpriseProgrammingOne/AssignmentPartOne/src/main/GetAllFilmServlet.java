package main;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetAllFilmServlet
 */
@WebServlet("/GetAllFilmServlet")
public class GetAllFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetAllFilmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FilmInterface fInterface = new FilmInterface();
		Gson gson = new Gson();

		ArrayList<Film> filmResults = fInterface.listFilm();

		String format = request.getParameter("format");

		if (format != null) {
			if (format.equals("xml")) {
				try {
					JAXBContext jaxbContect = JAXBContext.newInstance(Film.class);
					Marshaller marshaller = jaxbContect.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					StringWriter stringWriter = new StringWriter();
					ArrayList<Film> list = fInterface.listFilm();
					for (Film film : list) {
						marshaller.marshal(film, stringWriter);
					}

					String xml = stringWriter.toString();
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("./jspHolder/GetAllFilms-XML.jsp");
					request.setAttribute("xml", xml);
					response.setContentType("text/xml");
					requestDispatcher.forward(request, response);
				} catch (Exception exception) {
					System.out.println(exception);
				}
			}

			if (format.equals("json")) {
				response.getWriter().append(gson.toJson(filmResults));
			}

		} else {
			response.getWriter().append(gson.toJson(filmResults));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
