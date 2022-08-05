<%-- 
    Document   : checkOut
    Created on : Mar 7, 2022, 7:53:35 PM
    Author     : Utech
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.shopping.Item"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>Payment</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <link rel="stylesheet" href="css/checkOutStyle.css">
    </head>
    <body>
        <main class="page payment-page">
            <section class="payment-form dark">
                <div class="container">
                    <div class="block-heading">
                        <h2>Payment</h2>
                        <!--          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis vitae leo.</p>-->
                    </div>


                    <form action="MainController">
                        <div class="products">
                            <h3 class="title">Checkout</h3>
                            <%
                                Cart cart = (Cart) session.getAttribute("CART");
                                if (cart != null) {
                                    if (cart.getCart().size() > 0) {
                                        int total = 0;
                                        for (Item item : cart.getCart().values()) {
                                            total += item.getPrice() * item.getQuantity();
                            %>
                            <div class="item">
                                <span class="price"><%=item.getPrice()%>&emsp;&emsp; X&nbsp;<%=item.getQuantity()%></span>
                                <p class="item-name"><%=item.getName()%></p>
                            </div>
                            <%
                                }
                            %>
                            <div class="total">Total
                                <span class="price">
                                    <%=total%>
                                    <input type="hidden" name="total" value="<%= total%>"/>
                                </span>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>

                </div>


                <%
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (loginUser == null) {
                        loginUser = new UserDTO();
                    }
                %>

                <div class="card-details">
                    <h3 class="title">Credit Card Details</h3>
                    <div class="row">

                        <div class="form-group col-sm-8">
                            <label for="card-number">Email</label>
                            <input name="email" type="text" class="form-control" required="" value="<%=loginUser.getEmail()%>">
                        </div>
                        
                        <div class="form-group col-sm-7">
                            <input type="hidden" name="userID" value="<%= loginUser.getUserID()%>"/>
                            <label for="card-holder">Full Name</label>
                            <input name="fullName" type="text" class="form-control" required="" value="<%=loginUser.getFullName()%>">
                        </div>

                        <div class="form-group col-sm-8">
                            <label for="card-number">Address</label>
                            <input name="address" type="text" class="form-control" required="" value="<%=loginUser.getAddress()%>">
                        </div>



                        <div class="form-group col-sm-8">
                            <label for="card-number">Phone</label>
                            <input name="phone" type="text" class="form-control" required="" value="<%=loginUser.getPhone()%>">
                        </div>

                        <div class="form-group col-sm-12">
                            <button type="submit" name="action" value="Checkout" required="" class="btn btn-primary btn-block">Proceed</button>
                        </div>
                        <h3 style="text-align: center">The checkout process may take a few minutes, please wait</h3>
                    </div>
                </div>
                <form>
                    <div class="col-sm-6 mb-3 mb-m-1 order-md-1 text-md-left">
                        <a href="viewCart.jsp">
                            <i class="fas fa-arrow-left mr-2"></i>Back to Cart</a>
                    </div>
                    </div>
            </section>
        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>