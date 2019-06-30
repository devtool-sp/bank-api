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
                <div class="subtitle-name-btn btn"><a href="cashout">Вывод</a></div>
            </div>
            <div class="subtitle-name pay-in">
                <i class="far fa-plus-square fa-2x"></i>
                <div class="subtitle-name-btn btn"><a href="cashin">Ввод</a></div>
            </div>
            <div class="subtitle-name">
                <i class="fas fa-users-cog fa-2x"></i>
                <div class="subtitle-name-btn btn"><a href="cabinet">Кабинет</a></div>
            </div>
        </div>
    </div>
	<div class="main-section">
		<%@ include file="/WEB-INF/views/alldeals.jsp"%>
	<div class="main-subsection">
       </div>  
    </div>
</body>
</html>