<%@page import="sample.shopping.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/User_styles.css" rel="stylesheet" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">HOME</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    </ul>
                    <%
                        int cartQuantity = 0;
                        Cart cart = (Cart) session.getAttribute("CART");
                        if (cart == null) {
                            cart = new Cart();
                        } else {
                            cartQuantity = cart.getCart().size();
                        }

                    %> 
                    <form action="MainController" method="POST">    

                        <button name="action" value="View" class="btn btn-outline-dark" >
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill"><%=cartQuantity%></span>
                        </button>

                        <button name="action" value="Logout" class="btn btn-outline-dark" >
                            Log Out
                            <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                        </button>
                    </form>

                </div>
            </div>

        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Welcome !!</h1>
                </div>
            </div>
        </header>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <h1 style="text-align: center"><%= message%></h1>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

                    <c:if test="${sessionScope.LIST_PRODUCT != null}">
                        <c:if test="${not empty sessionScope.LIST_PRODUCT}">
                            <c:forEach items="${sessionScope.LIST_PRODUCT}" var="o">
                                <form action="MainController" method="POST">
                                    <div class="col mb-5">
                                        <div class="card h-100">
                                            <!-- Product image-->
                                            <img class="card-img-top" src="${o.image}" <img src="${o.image}" width="250" height="250" />
                                            <!-- Product details-->
                                            <div class="card-body p-4">
                                                <div class="text-center">
                                                    <!-- Product name-->
                                                    <h4 class="fw-bolder">${o.productName}
                                                        <input type="hidden" name="productName" value="${o.productName}" readonly=""/>
                                                    </h4>
                                                    <h5 class="fw-bolder">${o.productID}
                                                        <input type="hidden" name="productID" value="${o.productID}" readonly=""/>
                                                    </h5>
                                                    <!-- Product price-->
                                                    <h5>
                                                        ${o.price} $
                                                        <input type="hidden" name="price" value="${o.price}" readonly=""/>
                                                    </h5>
                                                    <h4>
                                                        Quantity
                                                    </h4>
                                                    <input type="number" name="quantity" value="1" min="1" required=""/>
                                                </div>
                                            </div>
                                            <!-- Product actions-->
                                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                                <div class="text-center"><button type="submit" name="action" value="Add" class="btn btn-outline-dark mt-auto"/>Add to cart</button></div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; DO_DUY_ANH SE16412</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
