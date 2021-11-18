package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDetails;
import service.EditProfileService;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet(description = "za editovanje User Details-a", urlPatterns = { "/EditProfileController" })
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EditProfileService servis = new EditProfileService();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String idUser = request.getParameter("idUser");
		
		System.out.println("First name: " + firstName);
		System.out.println("lastName: " + lastName);
		System.out.println("phone: " + phone);
		System.out.println("email: " + email);
		System.out.println("country: " + country);
		System.out.println("city: " + city);
		System.out.println("street: " + street);
		System.out.println("idUser: " + idUser);
		
		//1. Pronadji u bazi UserDetails ciji je user sa prihvacenim idUser
		User user = servis.vratiUseraPoId(idUser);
		UserDetails details = servis.vratiUserDetailsPoUseru(user);
		// ako ne nadje userdetails da vrati gresku
		if(details == null) {
			response.sendRedirect("html_stranice/noUserDetailsFound.html");
		}else {
			//2. Setuj UserDetails i sacuvaj u bazi izmene
			boolean editujUserDetails = servis.editujUserDetails(firstName, lastName, phone, email, country, city, street, details);
			if(editujUserDetails) {
				response.sendRedirect("jsp/seller.jsp");
			}else {
				response.sendRedirect("html_stranice/noUserDetailsFound.html");
			}
		}

	}

}
