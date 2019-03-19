<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
</head>

<body>
<h2>Pàgina bàsica de Registre</h2>
<form action="login" method="POST">

    Introdueix un nom d’usuari
    <input type="text" name="name"/><br>

    Introdueix un nom d’usuari
    <input type="text" name="surname"/><br>

    Introdueix un nom d’usuari
    <input type="text" name="email"/><br>

    Introdueix un nom d’usuari
    <input type="text" name="username"/><br>

    Introdueix una contrasenya
    <input type="password" name="password"/>

    Repeteix una contrasenya
    <input type="password" name="password2"/>

    <input type="submit" name="register" value="REGISTER">
</form>

<a href="./login.jsp">Login</a>

<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

</body>

</html>
