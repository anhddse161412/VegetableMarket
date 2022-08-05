<%-- 
    Document   : viewCart
    Created on : Mar 5, 2022, 7:18:54 PM
    Author     : Utech
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.javafx.scene.control.skin.VirtualFlow.ArrayLinkedList"%>
<%@page import="java.util.List"%>
<%@page import="sample.shopping.Item"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.slim.js">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    </head>
    <body>
        <section class="pt-5 pb-5">
            <div class="container">
                <div class="row w-100">
                    <div class="col-lg-12 col-md-12 col-12">
                        <h3 class="display-5 mb-2 text-center">Shopping Cart</h3>
                        <p class="mb-5 text-center">
                            <%

                                Cart cart = (Cart) session.getAttribute("CART");
                                int cartQuantity = 0;
                                if (cart != null) {
                                    if (cart.getCart().size() >= 0) {
                                        cartQuantity = cart.getCart().size();
                                    }
                                }
                            %>
                            <i class="text-info font-weight-bold">You have <%=cartQuantity%> item </i></p>

                        <%
                            if (cart != null) {
                                if (cart.getCart().size() > 0) {
                        %>  

                        <table id="shoppingCart" class="table table-condensed">
                            <thead>
                                <tr>
                                    <th >#</th>
                                    <th>ID</th>
                                    <th >Product</th>
                                    <th>Price</th>
                                    <th >Quantity</th>
                                    <th>Total</th>
                                    <th>action</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1;
                                    int total = 0;
                                    for (Item item : cart.getCart().values()) {
                                        total += item.getPrice() * item.getQuantity();
                                %>
                            <form action="MainController">
                                <tr>
                                    <td><%= count++%></td>
                                    <td>
                                        <%= item.getId()%>
                                        <input type="hidden" name="id" value="<%= item.getId()%>"/>
                                    </td>
                                    <td>
                                                <h4><%= item.getName()%></h4>
                                    </td>

                                    <td><%= item.getPrice()%>$</td>
                                    <td>
                                        <input type="number" name="quantity" class="form-control form-control-lg text-center" value="<%= item.getQuantity()%>">
                                    </td>
                                    <td><%= item.getPrice() * item.getQuantity()%>$</td>
                                    <td class="actions" >
                                        <div class="text-right">
                                            <button  name="action" value="Edit" class="btn btn-white border-secondary bg-white btn-md mb-2">
                                                <i class="fas fa-sync"></i>
                                            </button>

                                            <button name="action" value="Remove" class="btn btn-white border-secondary bg-white btn-md mb-2">
                                                <i class="fas fa-trash"></i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </form>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <div class="float-right text-right">
                            <h4>Total:</h4>
                            <h1><%= total%>$</h1>
                        </div>
                        <%
                                }
                            }
                        %>

                        <%
                            List<String> tmp = new ArrayList<String>();
                            List<String> errorList = (List<String>) request.getAttribute("CHECK_OUT_ERROR");
                            if (errorList == null) {
                                errorList = tmp;
                            }
                            for (String str : errorList) {
                        %>
                        
                        <p> <%=str%> </p>
                        <%
                            }
                        %>

                    </div>
                </div>
                <form action="MainController" method="POST">
                    <div class="row mt-4 d-flex align-items-center">
                        <div class="col-sm-6 order-md-2 text-right">
                            <a href="checkout.jsp" class="btn btn-primary mb-4 btn-lg pl-5 pr-5">Checkout</a>
                        </div>

                        <div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
                            <a href="MainController?action=Shopping">
                                <i class="fas fa-arrow-left mr-2"></i> Continue Shopping</a>
                        </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
