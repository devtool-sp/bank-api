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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript" src="<c:url value="/static/js/header.js" />"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
</head>

<body>
	<%@ include file="/WEB-INF/views/head.jsp"%>
	<div class="header-section-subtitles">
		<div class="subtitle-money">
			<div class="subtitle-money-name btn"></div>
		</div>
		<div class="subtitle-section">
			<div class="subtitle-name pay-out">
				<i class="far fa-minus-square fa-2x"></i>
				<div class="subtitle-name-btn btn">Вывод</div>
			</div>
			<div class="subtitle-name pay-in">
				<i class="far fa-plus-square fa-2x"></i>
				<div class="subtitle-name-btn btn">Ввод</div>
			</div>
			<div class="subtitle-name">
				<i class="fas fa-users-cog fa-2x"></i>
				<div class="subtitle-name-btn btn">Кабинет</div>
			</div>
		</div>
	</div>
	<div class="main-section">
		<%@ include file="/WEB-INF/views/alldeals.jsp"%>
		<div class="main-subsection">
			<div class="subsection-btns">
				<div class="btn-chat" type="button">
					<h2 class="deal-btn-title btn">Чаты</h2>
				</div>
				<div class="btn-deals" type="button">
					<h2 class="deal-btn-title btn">Сделки</h2>
				</div>
				<div class="btn-arbitration" type="button">
					<h2 class="deal-btn-title btn">Арбитраж</h2>
				</div>
				<div class="btn-service" type="button">
					<h2 class="deal-btn-title btn">Служба поддержки</h2>
				</div>
			</div>
			<div class="subsection-chats ">
				<div class="b-popup-deals main-subsection">
					<div class="b-popup-content-deals">
					<c:forEach items="${buyerdeals}" var="element">
						<div class="deals-section-elements">
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-number">№1</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-name">${element.seller.ssoId}</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-sum">${element.sum}</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-status">оплачено</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-user-status">Ваш статус:
								исполнитель</a>
						</div>
						</c:forEach>
						<c:forEach items="${sellerdeals}" var="element">
						<div class="deals-section-elements">
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-number">№1</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-name">${element.buyer.ssoId}</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-sum">${element.sum}</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-status">оплачено</a>
							<a href="<c:url value='/dealbuyer-${element.id}' />" class="deal-element deal-user-status">Ваш статус:
								заказчик</a>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>