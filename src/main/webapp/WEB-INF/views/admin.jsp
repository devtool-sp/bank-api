<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta charset="UTF-8" />
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
    Dear <strong>${loggedUser.firstName}</strong>, Welcome to Admin Page.
    <a href="<c:url value="/logout" />">Logout</a>
     <div class="admin-container">
         
         <div class="admin-panel panel-default">
                  <!-- Default panel contents -->
                <div class="panel-heading"><span class="lead">List of Users </span></div>
                <table class="admin-table admin-table-hover">
                    <thead>
                        <tr class="admin-table-tr" >
                            <th class="admin-table-th" >SSO ID</th>
                            <th class="admin-table-th" >Password</th>
                            <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                <th width="100"></th>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ADMIN')">
                                <th width="100"></th>
                            </sec:authorize>                       
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr class="admin-table-tr" >
                            <td class="admin-table-th" >${user.ssoId}</td>
                            <td class="admin-table-th" >${user.password}</td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <sec:authorize access="hasRole('ADMIN')">
                <div class="well">
                    <a href="<c:url value='/newuser' />">Add New User</a>
                </div>
            </sec:authorize>
        </div>
</body>
</html>
