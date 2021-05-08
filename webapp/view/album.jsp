<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="web_presentation.page_controller.AlbumHelper" %>
<html>
<head>
<meta charset="UTF-8">
<title>Album</title>
</head>
<body>
<h3>Album</h3>
<%
  Calendar c = Calendar.getInstance();
  int hour = c.get(Calendar.HOUR_OF_DAY);
  int minute = c.get(Calendar.MINUTE);
  int second = c.get(Calendar.SECOND);

  AlbumHelper albumHelper = (AlbumHelper) request.getAttribute("helper");
  String albumPrint = albumHelper.print();
%>
<%=albumPrint%>
<br/>
응답 시간 : <%=hour%>:<%=minute%>:<%=second%>
</body>
</html>
