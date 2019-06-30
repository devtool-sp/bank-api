<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <div id="fb-root"></div>
    <script>
        window.fbAsyncInit = function() {
            FB.init({
                xfbml            : true,
                version          : 'v3.2'
            });
        };

        (function(d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id)) return;
            js = d.createElement(s); js.id = id;
            js.src = 'https://connect.facebook.net/en_US/sdk/xfbml.customerchat.js';
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));</script>

    <!-- Your customer chat code -->
    <div class="fb-customerchat"
         attribution=setup_tool
         page_id="568950076960381">
    </div>
<div class="header-section">
	<div class="b-popup-rules">
		<div class="b-popup-content-rules">
			<div class="b-popup-content-block">
				<div class="b-popup-content-rules-description-title">Создание
					сделки.</div>
				<div class="b-popup-content-rules-description">
					1. Заказчик и исполнитель должны зарегистрироваться на нашем сайте.</br>
					2. После регистрации нужно пополнить внутренний баланс аккаунта на
					нужную Вам сумму.</br> 3. Заказчик и исполнитель должны договориться о
					объекте сделки, обговорить все условия.</br> 4. Заказчик или исполнитель
					создают сделку.</br> 5. Заказчик или исполнитель полностью описывают
					сделку и условия.</br> 6. Далее стороны редактируют сделку и принимают
					условия.</br> 7. Заказчик пополняет баланс сделки.</br> 8. После пополнения
					баланса исполнитель передает товар (оказывает услугу) покупателю и
					завершает сделку.</br> 9. Заказчик проверяет товар/услугу.</br> 10. Заказчик
					завершает сделку.</br> 10.1 Если заказчик не завершил сделку, средства
					автоматически переведутся на баланс исполнителя по истечению срока
					сделки на следующие сутки.</br> 10.2 Если исполнитель не завершил
					сделку, средства остаются на баланс заказчика.</br> 11. После успешного
					завершения сделки, средства мгновенно переводяться на баланс
					исполнителя.</br> 12. Стороны могут отменить сделку только до момента
					пополнения баланса.</br>
				</div>
			</div>
			<div class="b-popup-content-block">
				<div class="b-popup-content-rules-description-title">Создание
					арбитража при возникновении спорных вопросов.</div>
				<div class="b-popup-content-rules-description">
					1. После пополнения баланса сделки, у пользователей открывается
					возможность создания Арбитража.</br> 2. Арбитраж создается постредством
					нажатия кнопки "Арбитраж" в разделе описания сделки.</br> 3. На время
					арбитража автоматический перевод средств по истечению срока сделки
					замораживается.</br> 4. Заказчик и исполнитель должны написать все свои
					претензии к стороне в чате с арбитром.</br> 5. Арбитраж проводит
					обязательные консультации с участниками сделки в чате, а также
					устанавливает факты, достоверно свидетельствующие о выполнении или
					невыполнении условий сделки.</br> 6. В компетенцию арбитража входит
					вынесение решения о распределении средств между ее участниками.</br> 7.
					В том случае, если какая-либо из сторон, участвующих в сделке,
					уклоняется от проведения разбирательства по существу, арбитраж
					оставляет за собой право разрешить спор в пользу противоположной
					стороны.</br>
				</div>
			</div>
		</div>
	</div>
	<div class="b-popup-comission">
		<div class="b-popup-content-comission">
			<div class="b-popup-content-comission-description">Ввод средств
				- 1.5%*</div>
			<div class="b-popup-content-comission-description">Вывод
				средств - 1.5%*</div>
			<div class="b-popup-content-comission-description-min">
				*</br> может взыматься дополнительная комиссия Вашим банком
			</div>
		</div>
	</div>
	<div class="header-section-titles">
		<div class="title-main">Garant</div>
		<div class="title-user">
			<div class="user-icon-click" type="button"
				onClick='location.href="javascript:UserPopUpShow()"'>${userName}<i
					class="far fa-user fa-2x"></i>
			</div>
			<div class="b-popup-user" id="popup-main-user">
				<div class="b-popup-content-user">
					<div class="btn-main-user-user-name">
						<i class="fas fa-user fa-2x user-green"></i>
					</div>
					<div class="btn-main-user-rules" type="button"
						onClick='location.href="javascript:OpenRules()"'>Правила</div>
					<div class="btn-main-user-comission" type="button"
						onClick='location.href="javascript:OpenComission()"'>Комиссия</div>
					<div class="btn-main-user-exit" type="button">
						<input type="hidden" class="btn-relative2" value="${userName}" />
						<a href="<c:url value="/logout" />" class="btn-main-user-exit">Выход</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

