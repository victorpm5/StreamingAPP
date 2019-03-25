<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
</head>

<body>

<%  HttpSession sessioUsuari= (HttpSession) request.getSession();
    String usuariLogged = (String) sessioUsuari.getAttribute("usuari");

    //Si entrem aquí és pq no hi ha sessió
    if(StringUtils.isBlank(usuariLogged)){
        response.sendRedirect("login.jsp");
    };
%>


<h2>Pàgina bàsica de Registre Video</h2>
<form action="newvideo" method="POST">

    Introdueix titol del video
    <input type="text" name="title"/><br>

    Introdueix el nom de l'autor
    <input type="text" name="autor"/><br>

    Introdueix una data de creació
    <input type="date" name="fechaCreacion"/><br>

    Introdueix una duracion
    <input type="number" name="duracion"/><br>

    Introdueix una descripcion
    <input type="text" name="descripcion"/>

    Introdueix una descripcion
    <input type="text" name="formato"/>

    Introdueix una descripcion
    <input type="text" name="url"/>

    <input type="submit" name="register">
</form>

<a href="./listadoVid">Lista videos</a>

<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

</body>

</html>
