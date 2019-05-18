<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>


						<div class="chats-conversation">
							<div id="chat">${chatHistory}</div>
							<div class="chats-conversation-section">
								<c:forEach items="${senderMessages}" var="element">
									<div class="chats-conversation-user-name">${element.author.ssoId}
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
