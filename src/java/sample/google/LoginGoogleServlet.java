/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.google;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.google.GoogleDTO;
import sample.user.UserDAO;
import sample.user.UserDTO;

@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final Logger LOGGER = Logger.getLogger(LoginGoogleServlet.class);

    public LoginGoogleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            try {
                String accessToken = GoogleUtils.getToken(code);
                GoogleDTO googleDTO = GoogleUtils.getUserInfo(accessToken);
                HttpSession session = request.getSession();
                String email = googleDTO.getEmail();
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkLoginGoogle(email);

                if (user != null) {
                    session.setAttribute("LOGIN_USER", user);
                } else {
                    session.setAttribute("LOGIN_USER", new UserDTO("", "", "", "", "", "", "", email));
                }

            } catch (Exception e) {
                LOGGER.error("Error at LoginGoogleServlet: " + e.toString());
            } finally {
                request.getRequestDispatcher("ShoppingController").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
