 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.orderDetail.OrderDetailDAO;
import sample.orderDetail.OrderDetailDTO;
import sample.product.ProductDAO;
import sample.shopping.Cart;
import sample.shopping.Item;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.utils.SendGmail;

/**
 *
 * @author Utech
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "checkoutSuccessfully.html";
    private static final String CURRENT_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    static final Logger LOGGER = Logger.getLogger(CheckoutController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            List<String> errorList = new ArrayList<String>();
            HttpSession session = request.getSession();
            String orderDate = CURRENT_DATE;

            int total = Integer.parseInt(request.getParameter("total"));
            String userID = request.getParameter("userID");
            String address = request.getParameter("address");
            String fullName = request.getParameter("fullName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");

            if (userID == "") {
                userID = email;
            }
            OrderDAO orderDao = new OrderDAO();
            UserDAO userDao = new UserDAO();
            OrderDetailDAO orderDetailDao = new OrderDetailDAO();
            ProductDAO productDao = new ProductDAO();

            int checkQuantity = 0;
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    // check product quantity
                    for (Item item : cart.getCart().values()) {
                        String productID = item.getId();
                        int quantity = productDao.getProductQuantity(productID);
                        if (item.getQuantity() > quantity) {
                            errorList.add((item.getName() + " exceed the quantity left in stock (" + quantity + " left )"));
                            checkQuantity += 1;
                        }
                    }
                    request.setAttribute("CHECK_OUT_ERROR", errorList);
                    if (checkQuantity == 0) {
                        boolean checkUser = userDao.checkUser(userID);
                        if (!checkUser) {
                            UserDTO user = new UserDTO(userID, fullName, "US", "", address, "", phone, email);
                            userDao.create(user);
                        }

                        OrderDTO order = new OrderDTO(orderDate, total, userID);
                        boolean checkOrder = orderDao.create(order);
                        if (checkOrder) {
                            int orderID = orderDao.getOrderID(userID);
                            for (Item item : cart.getCart().values()) {
                                OrderDetailDTO orderDetail = new OrderDetailDTO(item.getPrice(), item.getQuantity(), orderID, item.getId());
                                orderDetailDao.create(orderDetail);
                                int quantity = productDao.getProductQuantity(item.getId());
                                int newQuantity = quantity - item.getQuantity();
                                productDao.updateQuantity(newQuantity, item.getId());
                            }
                            url = SUCCESS;
                            SendGmail sendGmail = new SendGmail();
                            sendGmail.sendText(email);
                            session.removeAttribute("CART");
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("ERROR at CheckoutController: " + e.toString());
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
