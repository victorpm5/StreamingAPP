<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
</head>

<body>
<h2>Pàgina bàsica de Registre Video</h2>
<form action="newvideo" method="POST">

    Introdueix titol del video
    <input type="text" name="title" required/><br>

    Introdueix el nom de l'autor
    <input type="text" name="autor" required/><br>

    Introdueix una data de creació
    <input type="date" name="fechaCreacion" required/><br>

    Introdueix una duracion
    <input type="number" step=".01" min="0" name="duracion" required/><br>

    Introdueix una descripcion
    <input type="text" name="descripcion" required/>

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
