<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>Missing artist error</title>
</head>
<body>
<%
  Calendar c = Calendar.getInstance();
  int hour = c.get(Calendar.HOUR_OF_DAY);
  int minute = c.get(Calendar.MINUTE);
  int second = c.get(Calendar.SECOND);
%>
에러 발생 시간 : <%=hour%>:<%=minute%>:<%=second%>
</body>
</html>
