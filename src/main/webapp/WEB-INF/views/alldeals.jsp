<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false"%>

        <div class="all-deals">
            <div class="all-deals-section">
                <h2 class="deals-name"></h2>
                <div class="deals-section">

			<div class="deals-section-subsection">
				<c:forEach items="${buyerdeals}" var="element">
			<div class="deals-section-subsection-element">				
							<a href="<c:url value='/dealbuyer-${element.id}' />"
								class="deals-username">${element.seller.ssoId} </a>
								<a href="<c:url value='/dealbuyer-${element.id}' />"
								class="deals-cash">${element.sum} </a>
					</div>
				</c:forEach>
			</div>

			<div class="deals-section-subsection">
				<c:forEach items="${sellerdeals}" var="element">
				<div class="deals-section-subsection-element">				
							<a href="<c:url value='/dealbuyer-${element.id}' />"
								class="deals-username">${element.seller.ssoId} </a>
								<a href="<c:url value='/dealbuyer-${element.id}' />"
								class="deals-cash">${element.sum} </a>
					</div>
				</c:forEach>
			</div>

		</div>
            </div>
            <div class="add-deal-btn" type="button">
                <div class="add-deal-btn-title btn"><a href="createdeal">Новая сделка</a></div>
            </div>
        </div>
