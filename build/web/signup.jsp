<%@page import="sample.user.UserError"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="US-ASCII">
        <title>Signup Page</title>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>




        <div class="form">


            <div class="tab-content">
                <div id="signup">   
                    <h1>Sign Up for Free</h1>



                    <%
                        UserError userError = (UserError) request.getAttribute("USER_ERROR");
                        if (userError == null) {
                            userError = new UserError();
                        }

                    %>

                    <form action="MainController" method="post">
                        <div class="field-wrap">
                            <label>
                                User ID<span class="req">*</span>
                            </label>
                            <input name="userID" type="text" required="">
                            <h5 class="incorrect"><%= userError.getUserID()%></h5>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Email Address<span class="req">*</span>
                            </label>
                            <input name="email" type="text" required="">
                        </div>

                        <div class="field-wrap">
                            <label>
                                Set A Password<span class="req">*</span>
                            </label>
                            <input name="password" type="password" required/>
                            <h5 class="incorrect"><%= userError.getPassword()%></h5>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Confirm Password<span class="req">*</span>
                            </label>
                            <input name="confirm" type="password" required/>
                            <h5 class="incorrect"><%= userError.getConfirm()%></h5>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Full Name<span class="req"></span>
                            </label>
                            <input name="fullName" type="text"/>
                            <h5 class="incorrect"><%= userError.getFullName()%></h5>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Address <span class="req"></span>
                            </label>
                            <input name="address" type="text"/>
                        </div>

                        <div class="field-wrap">
                            <label class="birthday">
                                Birthday <span class="req"></span>
                            </label>
                            <input name="birthDay" type="date"/>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Phone <span class="req"></span>
                            </label>
                            <input name="phone" type="text"/>
                        </div>

                        <p class="signup" style="color: #179b77">Already a member ? <a href="login.jsp">Log In</a></p>

                        <div class="g-recaptcha" data-sitekey="6Lec5S8eAAAAAPk7BqieN4vSZdP1Po2AFzsPy-2e"></div>

                        <h3><a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Assignment/login-google&response_type=code
                               &client_id=982510024258-s2jv747oq2078nelpb4qu76je7vkm2oi.apps.googleusercontent.com&approval_prompt=force">Login With Google
                            </a> </h3>
                            <%                            String error = (String) request.getAttribute("ERROR");
                                if (error == null) {
                                    error = "";
                                }
                            %>
                        <h3 class="incorrect"> <%= error%> </h3>

                        <button type="submit" name="action" value="CreateUser" class="button button-block"/>Get Started</button>

                    </form>

                </div>

               
            </div>

        </div> 
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/script.js">


        </script>
    </body>

</html>