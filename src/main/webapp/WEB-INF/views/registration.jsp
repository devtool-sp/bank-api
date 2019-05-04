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
    <div class="registration-elements">
	<div class="registration">
		<h2 class="registration-title">Garant</h2>
	 </div>
	<div class="registration-form">
		<h2 class="registration-subtitle">Вход</h2>
			<form:form modelAttribute="user" method="post" >
				<form:input type="hidden" path="id" id="id"/>
			<div class="field">		
				<div class="input">
					<form:input class="registration-input-login" type="text"  path="ssoId" id="ssoId"   placeholder="логин" />
					<form:errors path="ssoId"></form:errors>
				</div>	
			</div>		    
			<div class="field">
				<div class="input">
					<form:input class="registration-input-password" type="password" placeholder="пароль" path="password" id="password" />
					<form:errors path="password"></form:errors>
				</div>
			</div>
				<div class="input">
					<input class="registration-input-password" type="password" name="confirmPassword" placeholder="повторите пароль"
						id="confirmPassword" />
						<form:errors path="confirmPassword"></form:errors>
				</div>
				<form:select type="hidden" path="userProfiles" items="${roles}"  itemValue="id" itemLabel="type"/>
                        
			<div class="field">
				<div class="btn-registration-section">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="btn-registration" value="Регистрация" />
				</div>
			</div>
		</form:form>
	</div>
  </div>
</body>
</html>
