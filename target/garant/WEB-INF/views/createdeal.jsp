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
				<div class="subtitle-name-btn btn">
					<a href="cashout">Вывод</a>
				</div>
			</div>
			<div class="subtitle-name pay-in">
				<i class="far fa-plus-square fa-2x"></i>
				<div class="subtitle-name-btn btn">
					<a href="cashin">Ввод</a>
				</div>
			</div>
			<div class="subtitle-name">
				<i class="fas fa-users-cog fa-2x"></i>
				<div class="subtitle-name-btn btn">
					<a href="cabinet">Кабинет</a>
				</div>
			</div>
		</div>
	</div>

	<div class="main-section">

		<%@ include file="/WEB-INF/views/alldeals.jsp"%>

		<div class="main-subsection">
			<div class="subsection-btns">
				<div class="btn-chat" type="button">
					<h2 class="deal-btn-title btn">
						<a href="chats">Чаты</a>
					</h2>
				</div>
				<div class="btn-deals" type="button">
					<h2 class="deal-btn-title btn">
						<a href="deals">Сделки</a>
					</h2>
				</div>
				<div class="btn-arbitration" type="button">
					<h2 class="deal-btn-title btn">
						<a href="arbitration">Арбитраж</a>
					</h2>
				</div>
				<div class="btn-service" type="button">
					<h2 class="deal-btn-title btn">
						<a href="customerservice">Служба поддержки</a>
					</h2>
				</div>
			</div>

			<div class="b-popup-create-deal main-subsection">
				<div class="b-popup-content-create-deal">

					<form:form modelAttribute="deal" class="create-deal-block"
						method="post">
						<div class="create-deal-fields-section">
							<form:input type="hidden" path="id" id="id" />
							<form:hidden class="current-user" path="buyer" id="buyer"
								name="buyer" />

							<div class="create-dealt-field">
								<form:input class="create-deal-input contractor-user"
									type="text" placeholder="Имя контрагента" id="seller"
									path="seller" name="seller" />
								<form:errors class="deal-errors contractor-user-errors" path=""></form:errors>
							</div>
							<div class="create-dealt-field">
								<form:input class="create-deal-input" type="text"
									placeholder="Объект договора" id="subject" path="subject" />
								<form:errors class="deal-errors" path="subject"></form:errors>
							</div>
							<div class="create-dealt-field">
								<form:input class="create-deal-input" type="text"
									placeholder="Количество" id="quantity" path="quantity" />
								<form:errors class="deal-errors" path="quantity"></form:errors>
							</div>
							<div class="create-dealt-field">
								<form:input class="create-deal-input" type="text"
									placeholder="Комплект" id="complect" path="complect" />
								<form:errors class="deal-errors" path="complect"></form:errors>
							</div>
							<div class="create-dealt-field">
								<form:input class="create-deal-input" type="text"
									placeholder="Вес,г" id="weight" path="weight" />
								<form:errors class="deal-errors" path="weight"></form:errors>
							</div>
							<div class="create-dealt-field additionally-field">
								<form:input class="create-deal-input" type="text"
									placeholder="Дополнительно" id="additionaly" path="additionaly" />
								<form:errors class="deal-errors" path="additionaly"></form:errors>
							</div>
						</div>

						<div class="create-deal-btns-section">
							<div class="create-deal-status-input-field">
								<h2 class="create-deal-headers">Ваш статус</h2>
								<div class="create-deal-ul">
									<div class="create-deal-li" type="button" id="button-buyer">Исполнитель</div>
									<div class="create-deal-li" type="button" id="button-seller">Заказчик</div>
								</div>
							</div>
							<div class="create-deal-term-input-field-section">
								<h2 class="create-deal-headers">Срок выполнения</h2>
								<div class="create-deal-term-input-field-subsection">
									<form:input class="create-deal-term-input-field-time"
										type="timestamp" placeholder="2019-01-01 00:00:00" id="terms"
										path="terms" />
								</div>
							</div>

							<div class="create-deal-sum-input-field-section">
								<h2 class="create-deal-headers">Сумма сделки UAH</h2>
								<form:input class="create-deal-sum-input-field" type="text"
									placeholder="Сумма" id="sum" path="sum" />
							</div>
							<div class="create-deal-submit-btn">
								<input type="submit" class="btn-create-deal-submit"
									value="+Создать сделку" />
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>