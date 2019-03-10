<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
</head>

<body>
<h2>Pàgina bàsica de Registre</h2>
<form action="register" method="POST">

    Introdueix un nom d’usuari
    <input type="text" name="usuari"/><br>

    Introdueix una contrasenya
    <input type="password" name="contrasenya"/>

    Repeteix una contrasenya
    <input type="password" name="contrasenya2"/>

    <input type="submit" value="submit">
</form>

<a href="./login.jsp">Login</a>

<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

</body>

</html>
