<%@page import="sample.product.ProductError"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Product page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            h5{
                color: #F24B27;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1750px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }	
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }	
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {        
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <div class="container-xxl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2>Product <b>Management</b></h2>
                            </div>
                            <div class="col-sm-7">
                                <a href="MainController?action=Logout" class="btn btn-secondary"><i class="material-icons">&#xE5C9;</i> <span>Log Out</span></a>
                                <a href="MainController?action=Search&Search=" class="btn btn-secondary"><i class="material-icons">&#xE24D;</i> <span>User Management</span></a>
                                <a href="#addProduct"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            </div>
                        </div>
                    </div>
                    <%
                        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                        if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                            response.sendRedirect("login.jsp");
                            return;
                        }
                    %>
                    <h1>Welcome: <%= loginUser.getFullName()%></h1>
                    <%
                        String search = request.getParameter("Search");
                        if (search == null) {
                            search = "";
                        }
                    %>                    


                    <form action="MainController" method="POST">
                        Search<input type ="text" name ="SearchProduct" value=<%=search%>>
                        <input type="submit" name="action" value="SearchProduct"/>
                    </form>
                    <%
                        String message = (String) request.getAttribute("PRODUCT_MESSAGE");
                        if (message == null) {
                            message = "";
                        }
                    %>
                    <h2><%=message%></h2>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>						
                                <th>image</th>
                                <th>Price</th>
                                <th>quantity</th>
                                <th>category ID </th>
                                <th>import Date</th>
                                <th>using Date</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${requestScope.LIST_PRODUCT != null}">
                                <c:if test="${not empty requestScope.LIST_PRODUCT}">
                                    <c:forEach items="${requestScope.LIST_PRODUCT}" var="o">
                                    <form action="MainController" method="POST">

                                        <tr>
                                            <td>
                                                <input type="text" name="productID" value="${o.productID}" readonly=""/>
                                            </td>
                                            <td>
                                                <input type="text" name="productName" value="${o.productName}" required=""/>
                                            </td>   
                                            <td>
                                                <img src="${o.image}" width="167" height="150">
                                                <input type="text" name="image" value="${o.image}" />
                                            </td>   
                                            <td>
                                                <input type="number" name="price" value="${o.price}" min="1" required=""/>
                                            </td>   
                                            <td>
                                                <input type="number" name="quantity" value="${o.quantity}" min="1" required=""/>
                                            </td>     
                                            <td>
                                                <input type="text" name="categoryID" value="${o.cateloryID}" readonly=""/>
                                            </td>
                                            <td>
                                                <input type="date" name="importDate" value="${o.importDate}"readonly=""/>
                                            </td>  
                                            <td>
                                                <input type="date" name="usingDate" value="${o.usingDate}"readonly=""/>
                                            </td>
                                            <td><span class="status text-success">&bull;</span> Active</td>
                                            <td>
                                                <button type="submit" name="action" value="UpdateProduct" class="settings" title="Update" data-toggle="tooltip"/><i class="material-icons">&#xE8B8;</i></button>
                                                <a href="MainController?action=DeleteProduct&productID=${o.productID}&Search=<%=search%>" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                            </c:if>
                        </c:if>
                        <%
                            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
                            if (productError == null) {
                                productError = new ProductError();
                            }

                        %>

                        <div id="addProduct" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <form action="MainController" method="post">
                                        <div class="modal-header">						
                                            <h4 class="modal-title">Add Product</h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        </div>
                                        <div class="modal-body">	

                                            <div class="form-group">
                                                <label>product ID</label>
                                                <input type="text" name="productID"  class="form-control" required>
                                                <h5 class="incorrect"><%= productError.getProductID()%></h5>
                                            </div>
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" name="productName"  class="form-control" required>
                                                <h5 class="incorrect"><%= productError.getProductName()%></h5>
                                            </div>
                                            <div class="form-group">
                                                <label>Image</label>
                                                <input type="text" name="image"  class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input type="number" name="price" value="0"  class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label>Quantity</label>
                                                <input type="number" name="quantity" value="0" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label>Using date</label>
                                                <input type="date"  name="usingDate" class="form-control" required>
                                                <h5 class="incorrect"><%= productError.getUsingDate()%></h5>
                                            </div>
                                            <div class="form-group">
                                                <label>Category</label>
                                                <select name="categoryID" class="form-select" aria-label="Default select example">
                                                    <option value="R01">Rau</option>
                                                    <option value="C01">Cu</option>
                                                    <option value="Q01">Qua</option>
                                                </select>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                            <input type="submit" class="btn btn-success" name="action" value="AddProduct">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>



                        </tbody>
                    </table>
                </div>
            </div>
        </div>     
    </body>
</html>