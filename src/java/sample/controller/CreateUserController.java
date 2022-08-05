/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;
import sample.utils.VerifyRecaptcha;

/**
 *
 * @author Utech
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    private static final String ERROR = "signup.jsp";
    private static final String SUCCESS = "login.jsp";
    static final Logger LOGGER = Logger.getLogger(CreateUserController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserError userError = new UserError();
        boolean checkValidation = true;
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String birthDay = request.getParameter("birthDay");
            String phone = request.getParameter("phone");
            String roleID = "US";
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
            
            UserDAO dao = new UserDAO();
            boolean checkDuplicate = dao.checkDuplicate(userID);
            if (checkDuplicate) {
                userError.setUserID("DUPLICATE");
            }
            if (userID.length() < 2 || userID.length() > 10) {
                userError.setUserID("user id must be [2,10]");
                checkValidation = false;
            }
            if (fullName.length() < 5 || fullName.length() > 20) {
                userError.setFullName("full Name must be [5,20]");
                checkValidation = false;
            }
            
            if (password.length() < 1) {
                userError.setPassword("Must create password");
                checkValidation = false;
            }
            
            if (!password.equals(confirm)) {
                userError.setConfirm("not correct");
                checkValidation = false;
            }
            if (!verify) {
                request.setAttribute("ERROR", "Invalid Captcha");
                checkValidation = false;
            }

            if (checkValidation) {
                UserDTO user = new UserDTO(userID, fullName, roleID, password, address, birthDay, phone, email);
                boolean checkCreate = dao.create(user);
                if (checkCreate) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            LOGGER.error("Error at CreateUserController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
