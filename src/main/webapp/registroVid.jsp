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
    <input type="text" name="title" required/><br>

    Introdueix el nom de l'autor
    <input type="text" name="autor" required/><br>

    Introdueix una data de creació
    <input type="date" name="fechaCreacion" required/><br>

    Introdueix la duració
    <input type="number" step=".01" min="0" name="duracion" required/><br>

    Introdueix una descripció
    <input type="text" name="descripcion" required/><br>

    Introdueix el format
    <input type="text" name="formato"/><br>

    Introdueix una url
    <input type="text" name="url"/><br>

    <input type="submit" name="register">
</form>

<a href="./listadoVid">Lista videos</a>

<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

</body>

</html>
