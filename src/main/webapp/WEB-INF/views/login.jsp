<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta charset="UTF-8" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
</head>
<body>
<div class="login-section">
	<div class="login">
		<h2 class="login-title">Garant</h2>
	</div>
	<div class="login-form">
		<h2 class="login-subtitle">Вход</h2>
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="post">
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">
					<p>Invalid username or password.</p>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>

			<div class="field">
				<div class="input">
					<input class="login-input-login" type="text" id="username"
						name="ssoId" placeholder="Логин" />
				</div>
			</div>

			<div class="field">
				<div class="input">
					<input class="login-input-password" type="password"
						placeholder="Пароль" id="password" name="password" />
				</div>
			</div>

			<div class="field">
				<div class="btn-login-section">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="submit" class="btn-login"
						value="Авторизоваться" />
				</div>

			</div>
		</form>
		<a href="registration" class="login-title-bottom">регистрация</a>
	</div>
	</div>
</body>
</html>
