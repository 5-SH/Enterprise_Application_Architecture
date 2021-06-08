<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>Return Asset</title>
</head>
<body>
<h3>Return Asset</h3>
<%
  Calendar c = Calendar.getInstance();
  int hour = c.get(Calendar.HOUR_OF_DAY);
  int minute = c.get(Calendar.MINUTE);
  int second = c.get(Calendar.SECOND);

  String result = (String) request.getAttribute("command");
%>
자산 반환 시간 : <%=hour%>:<%=minute%>:<%=second%>
<br/>
DomainCommand 결과 : <%=result%>
</body>
</html>
