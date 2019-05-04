
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>Garant Systems</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>

</head>

<body id="main-elements">
    <div class="main-wrapper">
        <div class="btn-main-section">
            <div class="btn-main" type="button" onClick='location.href="javascript:PopUpShow()"'>
                <h2 class="btn-main-title">Как это работает</h2>
                <div class="b-popup" id="popup1">
                    <div class="b-popup-content">
                        <ul>
                            <li>Регистрация</br>
                                Услуги гаранта сделок предоставляются только после того, как оба участника
                                зарегестрируются на сайте.
                            </li>
                            <li>Обсуждение условий</br>
                                Участники обсуждают характеристики товара/услуги и сумму оплаты.
                            </li>
                            <li>Открытие сделки</br>
                                Один из участников открывает сделку, другой ее принимает.</br>
                                Если на данном этапе что-то идет не так (второй участник не пополнит</br>
                                баланс сделки), то сделка отменяется. Для общения и передачи</br>
                                информации используется внутренний чат.
                            </li>
                            <li>Исполнения обязательств</br>
                                Заказчик вносит деньги на баланс сделки, сумма сразу же блокируется.</br>
                                Исполнитель видит это и передает товар, после чего заказчик</br>
                                завершает сделку и средства автоматически переводятся на баланс</br>
                                исполнителя. В случае спора участники могут обратиться к гаранту</br>
                                сделок (в арбитраж).
                            </li>
                            <li>Закрытие сделки</br>
                                Если на балансе сделки нет денег, то один из участников ее закрывает.
                            </li>
                        </ul>
                        <div class="btn-close-pop-up" type="button" onClick='location.href="javascript:PopUpHide()"'>Закрыть</div>
                    </div>
                </div>
            </div>
             <div class="btn-main" type="button">
            <a href="login" class="btn-main-title">
                    <h2 class="btn-main-title">Вход</h2></a>
            </div>
        </div>
         <div class="main-content">  
            <h2 class="main-title">Garant</h2>    
            <div class="main-subtitle">Мы обеспечим безопасность</div>
            <div class="main-subtitle">как исполнителю, так и заказчику.</div> 
    </div>
    </div>
</body>


<script src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
<script>

    $(document).ready(function () {
        PopUpHide();

    });

    function PopUpShow() {
        $(".b-popup-content").css("display", "block");
        $(".b-popup").css("display", "block");
    }

    function PopUpHide() {
        $(".b-popup-content").css("display", "none");
        $(".b-popup").css("display", "none");
    }

    $("body").click(function (e) {
        $(".b-popup-content").css("display", "none");
        $(".b-popup").css("display", "none");
    });

    $(".b-popup-content").click(function (e) {
        e.stopImmediatePropagation();
    });

</script>
</html>