<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="web_presentation.page_controller.ArtistHelper" %>
<html>
<head>
<meta charset="UTF-8">
<title>Artist</title>
</head>
<body>
<h3>Artist</h3>
<%
  Calendar c = Calendar.getInstance();
  int hour = c.get(Calendar.HOUR_OF_DAY);
  int minute = c.get(Calendar.MINUTE);
  int second = c.get(Calendar.SECOND);

  ArtistHelper artistHelper = (ArtistHelper) request.getAttribute("helper");
  String artistPrint = artistHelper.print();
%>
<%=artistPrint%>

응답 시간 : <%=hour%>:<%=minute%>:<%=second%>
</body>
</html>
