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
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript" src="<c:url value="/static/js/header.js" />"></script>
<script language="JavaScript" type="text/javascript" src="<c:url value="/static/js/userchat.js" />"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
	<c:url var="home" value="/" scope="request" />
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

			<div class="subsection-chats ">
				<div class="b-popup-chats main-subsection">
					<div class="b-popup-content-chats">
						<div class="chats-contacts">
							
							<div class="chats-contact">
							<c:forEach items="${listOfSimpleMessagesSender}" var="element">
								<a href="<c:url value='/userchat-${element.chatId}' />"  class="chats-contact-name">${element.reciever.ssoId}</a>
								<a href="<c:url value='/userchat-${element.chatId}' />"  class="chats-contact-status">0</a>
								<a href="<c:url value='/userchat-${element.chatId}' />"  class="chats-contact-last-chat-time">${element.timestamp}</a>
									</c:forEach>
							</div>
							<div class="chats-contact">
								<c:forEach items="${listOfSimpleMessagesReciever}" var="element">
								<a href="<c:url value='/userchat-${element.chatId}' />"  class="chats-contact-name">${element.sender.ssoId}</a>
								<a href="<c:url value='/userchat-${element.chatId}' />"  class="chats-contact-status">0</a>
								<a href="<c:url value='/userchat-${element.chatId}' />"  class="chats-contact-last-chat-time">${element.timestamp}</a>
									</c:forEach>
							</div>
						</div>
					
					<div class="chats-conversation">
							<div id="chat">${chatHistory}</div>
							<div class="chats-conversation-section">
								<c:forEach items="${allMessages}" var="element">
									<div class="chats-conversation-user-name">${element.sender.ssoId}
									</div>
									<div class="chats-conversation-details">
										<div class="chats-conversation-message">${element.message}
										</div>
										<div class="chats-conversation-message-time">${element.timestamp}
										</div>
									</div>
								</c:forEach>

								<div class="chats-conversation-section-input">
									<span id="flash"></span>
									<div class="chats-conversation-input-field">
										<textarea class="chats-input" placeholder="написать..."
											id="data"></textarea>
									</div>
									<div class="chats-conversation-submit-btn">
										<button id="postUserMessage" />
										Отправить
										</button>
									</div>
								</div>
							</div>
						</div>
					
					
					
					</div>
			</div>
		</div>
	</div>
</body>
</html>