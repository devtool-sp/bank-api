<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

			<div class="b-popup-accept-deal-executor">
				<div class="b-popup-content-accept-deal-executor">
					<div class="accept-deal-executor-block">
						<div class="accept-deal-executor-section">
							<div class="accept-deal-executor-subsection">
								<div class="accept-deal-executor-number-field">Сделка № ${sellerDeal.id}</div>
								<div class="accept-deal-executor-sum-field">Сумма: ${sellerDeal.sum}</div>
								<div class="accept-deal-executor-status-field">(оплачено)
								</div>
								<div class="accept-deal-executor-user-status-field">Ваш
									статус: заказчик</div>
							</div>
							<div
								class="accept-deal-executor-subject-field accept-deal-executor">Контрагент: ${sellerDeal.buyer.ssoId}
							</div>
							<div
								class="accept-deal-executor-object-field accept-deal-executor">Объект
								договора: ${sellerDeal.subject}</div>
							<div
								class="accept-deal-executor-quantity-field accept-deal-executor">Количество:
								${sellerDeal.quantity}</div>
							<div
								class="accept-deal-executor-complection-field accept-deal-executor">Комплект:
								${sellerDeal.complect}</div>
							<div
								class="accept-deal-executor-weight-field accept-deal-executor">Вес,г:
								${sellerDeal.weight}</div>
							<div
								class="accept-deal-executor-additional-field accept-deal-executor">Дополнительно:
								${sellerDeal.additionaly}</div>
							<div class="accept-deal-executor-term-field accept-deal-executor">Срок
								выполнения сделки: ${sellerDeal.terms}</div>

							<div class="accept-deal-executor-current-time-field">${currentTime}</div>
						</div>

						<div class="accept-deal-executor-btns-section">

							<div class="accept-deal-executor-submit-btn">
								<sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER') ">
                            					<a href="<c:url value='/edit-deal-${sellerDeal.id}' />" class="customer-submit-btn">Редактировать</a>
                       					 </sec:authorize>
							</div>

							<div class="executor-submit-btn-subsection">
								<div class="accept-deal-executor-submit-btn">
									<input type="submit" class="executor-submit-btn"
										value="Оплатить" />
								</div>
								<div class="accept-deal-executor-submit-btn">
									<input type="submit" class="executor-submit-btn"
										value="Принять" />
								</div>
							</div>
							<div class="executor-submit-btn-subsection">
								<div class="accept-deal-executor-submit-btn">
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER') ">
                            					<a href="<c:url value='/delete-deal-${sellerDeal.id}' />" class="customer-submit-btn">Удалить</a>
                       					 </sec:authorize>
								</div>
								<div class="accept-deal-executor-submit-btn">
									<a href="arbitration">Арбитраж</a>
								</div>
							</div>
							<div class="accept-deal-executor-submit-btn">
								<input type="submit" class="executor-submit-btn"
									value="Выполнено" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>