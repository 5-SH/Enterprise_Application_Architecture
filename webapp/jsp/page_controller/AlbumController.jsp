<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<html>
<head>
<meta charset="UTF-8">
<title>Album</title>
</head>
<body>
<h3>Album</h3>
<jsp:useBean id="helper" class="web_presentation.page_controller.AlbumConHelper"/>
<%helper.init(request, response);%>
</body>
</html>
