<!DOCTYPE html>
<html>
    <head>
        <meta charset="US-ASCII">
        <title>Login Page</title>
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>




        <div class="form">
                <div id="login">   
                    <h1>Welcome Back!</h1>

                    <form action="MainController" method="POST">

                        <div class="field-wrap">
                            <label>
                                Email Address<span class="req">*</span>
                            </label>
                            <input type="text" name="userID" required autocomplete="off"/>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Password<span class="req">*</span>
                            </label>
                            <input type="password" name="password" required autocomplete="off"/>
                        </div>

                        <p class="signup"><a href="signup.jsp">Sign Up </a></p>

                        <div class="g-recaptcha" data-sitekey="6Lec5S8eAAAAAPk7BqieN4vSZdP1Po2AFzsPy-2e"></div>

                        <h3><a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Assignment/login-google&response_type=code
                                &client_id=982510024258-s2jv747oq2078nelpb4qu76je7vkm2oi.apps.googleusercontent.com&approval_prompt=force">Login With Google
                            </a> </h3>

                        <%
                            String error = (String) request.getAttribute("ERROR");
                            if (error == null) {
                                error = "";
                            }
                        %>
                        <h3 class="incorrect"> <%= error%> </h3>
                        
                        <button class="button button-block" name="action" value="Login"/>Log In</button>

                    </form>

                </div>

            </div><!-- tab-content -->

        </div> <!-- /form -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/script.js">


        </script>
    </body>

</html>