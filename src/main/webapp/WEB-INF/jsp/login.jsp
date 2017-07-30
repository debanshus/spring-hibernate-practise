<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
        <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <h2>Login</h2>
        <a href="/">Home</a>
        <a href="register">Register</a>

        <br>
        <hr>
        <br>

        <form:form action="login" method="post" commandName="loginForm">
            <table>
                <tr>
                    <td>Email</td>
                    <td><form:input id="email" path="email" type="email" placeholder="enter your email" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><form:input id="password" path="password" type="password" placeholder="enter your password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><form:button name="submit" type="submit">Login</form:button></td>
                    </tr>
                </table>
        </form:form>

        <div class="message">${message}</div>
    </body>
</html>
