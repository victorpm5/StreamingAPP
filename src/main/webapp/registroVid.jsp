<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
</head>

<body>
<h2>Pàgina bàsica de Registre Video</h2>
<form action="newvideo" method="POST">

    Introdueix titol del video
    <input type="text" name="name"/><br>

    Introdueix el nom de l'autor
    <input type="text" name="surname"/><br>

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

<a href="./listadoVid.jsp">Lista videos</a>

<%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

</body>

</html>
