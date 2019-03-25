<%@ page import="com.isdcm.streamingapp.models.Video" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


    <table>
        <% List<Video> videos = (List<Video>)request.getAttribute("videos");%>
        <% for(int i = 0; i < videos.size(); i+=1) { %>
            <tr>
                <td><%=videos.get(i).getTitle()%></td>
            </tr>
        <% } %>
    </table>

</body>

<a href="./registroVid.jsp">Add Video</a>

</html>