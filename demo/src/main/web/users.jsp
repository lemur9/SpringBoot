<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
</head>
<body>
<c:forEach items="${users}" var="user">
    ${user.name} -- ${user.age}
    <br/>
</c:forEach>

<img src="${pageContext.request.contextPath}/wallhaven-oxvrjl.jpg"/>
</body>
</html>