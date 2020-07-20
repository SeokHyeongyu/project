<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, sec01.ex02.*"%>
     <%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <%
    request.setCharacterEncoding("utf-8");
   	MemberDAO dao =new MemberDAO();
   	List list=dao.listMembers();
    %>
    
   
    <c:set var="list" value="<%= list %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	   <table border="1" align ="center">
    
    <tr align ="center" bgcolor ="#99ccff">
    <td width ="7%"> 아이디  </td>
    <td width ="7%"> 비밀번호  </td>
    <td width ="7%"> 이름  </td>
    <td width ="7%"> 이메일  </td>
    <td width ="7%"> 가입일자 </td>
    </tr>
    
    <tr>
    <c:forEach var="list" items="${list }">
    <td>${list.id}</td>
    <td>${list.pwd}</td>
    <td>${list.name}</td>
    <td>${list.email}</td>
    <td>${list.joinDate}</td>
    </tr>
    </c:forEach>
    <tr align ="center">
    </table>

</body>
</html>