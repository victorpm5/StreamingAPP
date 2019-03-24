<%@ page import="com.isdcm.streamingapp.models.Video" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>

<body>
<h2>Pàgina bàsica de Registre</h2>
</body>

<table>
    <% List<Video> videos = (List<Video>)request.getAttribute("videos");%>
    <% for(int i = 0; i < videos.size(); i+=1) { %>
        <tr>
            <td><%=videos.get(i).getTitle()%></td>
        </tr>
    <% } %>
</table>

<a href="./registroVid.jsp">Add Video</a>

</html>