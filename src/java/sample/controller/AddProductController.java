/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;

/**
 *
 * @author Utech
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    private static final String ERROR = "product.jsp";
    private static final String SUCCESS = "product.jsp";
    private static final String CURRENT_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    static final Logger LOGGER = Logger.getLogger(AddProductController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductError productError = new ProductError();
        boolean checkValidation = true;
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            int price = Integer.parseInt(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String importDate = CURRENT_DATE;
            String usingDate = request.getParameter("usingDate");
            String categoryID = request.getParameter("categoryID");

            ProductDAO dao = new ProductDAO();

            boolean checkDuplicate = dao.checkDuplicate(productID);
            if (checkDuplicate) {
                productError.setProductID("DUPLICATE");
                checkValidation = false;
            }
            if (productID.length() < 2 || productID.length() > 10) {
                productError.setProductID("Product id must be [2,10]");
                checkValidation = false;
            }
            if (productName.length() < 5 || productName.length() > 20) {
                productError.setProductName("Product Name must be [5,20]");
                checkValidation = false;
            }

            Date iDate = new SimpleDateFormat("yyyy-MM-dd").parse(CURRENT_DATE);
            Date uDate = new SimpleDateFormat("yyyy-MM-dd").parse(usingDate);
            if (uDate.compareTo(iDate) < 0) {
                productError.setUsingDate("Using date must be greater than current date");
                checkValidation = false;
            }

            if (checkValidation) {
                ProductDTO product = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate);
                boolean checkCreate = dao.create(product);
                if (checkCreate) {
                    url = SUCCESS;
                    request.setAttribute("PRODUCT_MESSAGE", "Add successfully");
                }
            } else {
                request.setAttribute("PRODUCT_MESSAGE", "Cannot add this product, please try again before searching product");
                request.setAttribute("PRODUCT_ERROR", productError);
            }
        } catch (Exception e) {
            LOGGER.error("Error at AddProductController: " + e.toString());
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
