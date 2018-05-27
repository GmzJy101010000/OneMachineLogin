<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<%@ page import="entity.User" %>
<%

String username = request.getParameter("userName");
String action = request.getParameter("action");

if("login".equals(action) && username.trim().length() >0){
	
	User user =new User();
    user.setName(username);
    user.setLoginTime(new java.util.Date());
    user.setIp(request.getRemoteAddr());
   
    session.setAttribute("user", user);
    session.setAttribute("test", "11");
    session.setAttribute("test", "22");
    response.sendRedirect(request.getRequestURL().toString());
   
    return;
}
else if("logout".equals(action)){
	
	session.removeAttribute("user");
	response.sendRedirect(request.getRequestURL().toString());
   return;
}
%>
<html>
<body>
<c:choose>
<c:when test="${user !=null }">
   欢迎你，${ user.name}<br>
 您的IP为： ${user.ip }<br>
 登录时间为： <fmt:formatDate value="${user.loginTime }" pattern="yyyy-MM-dd HH:mm:ss"/>  <br>  
<a href="${pageContext.request.requestURL }?action=logout">退出</a>
</c:when>
<c:otherwise>
${msg }
<c:remove var="msg" scope="session"/>
<form action="${pageContext.request.requestURL }?action=login" method="post">
账号：
<input name="userName"/>
<input type="submit" value="登录"/>
</form>
</c:otherwise>
</c:choose>

</body>
</html>
