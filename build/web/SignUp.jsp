<%-- 
    Document   : SignUp
    Created on : Jun 8, 2022, 10:40:32 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <a href="index.html"><img src='image/avatar.jpg' width='150px' height='150px' alt='Home'></a>
            <h1>Welcome to Project Manager!</h1>
            <div>
                <form action="SignUpControl" method="post" id="login-form">
                    <label for="username">Username</label>
                    <input type="text" id="username"><br>
                    <label for="Email">Email</label>
                    <input type="text" id="email"><br>
                    <label for="password">Password</label>
                    <input type="password" id="password"><br>
                    <label for="confirm_pass">Confirm Password</label>
                    <input type="password" id="confirm_password"><br>
                    <input type="submit" name="Create" value="Create Account">
                </form>
                <div id="createAccount" name="createAccount">
                    <p>Already have a account?</p>
                    <a href="SignIn.jsp">Sign In</a>
                </div>
            </div>
        </div>
    </body>
</html>
