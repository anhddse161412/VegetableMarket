package sample.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.user.UserDAO;
import sample.user.UserDTO;

import sample.utils.VerifyRecaptcha;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String FAIL = "login.jsp";
    private static final String AD = "AD";
    private static final String US = "US";
    private static final String ADMIN_PAGE = "MainController?action=Search&Search=";
    private static final String USER_PAGE = "MainController?action=Shopping";
    static final Logger LOGGER = Logger.getLogger(LogoutController.class);

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String url = FAIL;
        try {

            // get request parameters for userID and password
            String user = request.getParameter("userID");
            String password = request.getParameter("password");
            // get reCAPTCHA request param
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            UserDAO dao = new UserDAO();
            UserDTO loginUser = new UserDTO();
            loginUser = dao.checkLogin(user, password);

            if (loginUser != null && verify) {
                String roleID = loginUser.getRoleID();

                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else if (US.equals(roleID)) {
                    url = USER_PAGE;
                } else {
                    request.setAttribute("ERROR", "your role is not support");
                }
            } else if (!verify) {
                request.setAttribute("ERROR", "Invalid Captcha");
            } else {
                request.setAttribute("ERROR", "INCORRECT USERID OR PASSWORD");
            }
        } catch (Exception e) {
            LOGGER.error("Error at LoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
