<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<div class="chats-contact-add-btn">
	<form:form  method="post">
	<form:input type="hidden" path="id" id="id" />
	<form:hidden path="sender" id="sender" name="sender" />
	<form:hidden path="timestamp" id="timestamp" name="timestamp" />
	<form:hidden path="message" id="message" name="message" />
	<form:hidden path="chat_id" id="chat_id" name="chat_id" />
		<form:input class="create-deal-input contractor-user" type="text"
			id="reciever" path="reciever" name="reciever" />
	</form:form>
	<input type="submit" class="btn-create-deal-submit"
		value="+Добавить контакт" />
</div>
