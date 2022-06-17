<%-- 
    Document   : SignIn
    Created on : Jun 8, 2022, 10:40:19 AM
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
        <a href="index.html"><img src='image/avatar.jpg' width='150px' height='150px' alt='Home'></a>//return home
        <h1>Sign in to Project Manager</h1>
        <div>
            <form action="SignInControl" method="post" id="login-form">
                <label for="login_field">Username</label>
                <input type="text" id="login_field"><br>
                <label for="password">Password</label>
                <input type="password" id="password"><br>
                <input type="submit" name="SignIn" value="Sign in">
            </form>
            <a href="ForgotPass.jsp">Forgot password?</a></div>
        <div id="createAccount" name="createAccount">
            <p>New to Project Manager?</p>
            <a href="SignUp.jsp" style="display:inline">Create an account</a>
        </div>
    </body>
</html>
