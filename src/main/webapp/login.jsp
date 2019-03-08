<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
    <head>
    </head>

    <body>
        <h2>Pàgina bàsica de Login</h2>
        <form action="LoginServlet" method="POST">

            Introdueix el teu nom d’usuari
            <input type="text" name="usuari"/><br>

            Introdueix la teva contrasenya
            <input type="password" name="contrasenya"/>

            <input type="submit" value="submit">
        </form>

        <%=request.getParameter("msg")!=null?request.getParameter("msg"):""%>

    </body>

</html>
