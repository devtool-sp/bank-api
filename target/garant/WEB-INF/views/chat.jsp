<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
<title></title>
<meta charset="UTF-8" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet"></link>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script language="JavaScript" type="text/javascript" src="<c:url value="/static/js/header.js" />"></script>
<script language="JavaScript" type="text/javascript" src="<c:url value="/static/js/script.js" />"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
	integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf"
	crossorigin="anonymous">
</head>

<div id="chat">
    ${chatHistory}
</div>
<br />
<sf:form id="chatMessageSubmitForm" >
    <br />
    <span id="flash"></span>
    <br />
    <div id="pageSettings">Messages per page:
        <input type="radio" id="messagesPerPage50" name="messagesPerPage" value="50" checked="true" />50
        <input type="radio" id="messagesPerPage100" name="messagesPerPage" value="100" />100
        <input type="radio" id="messagesPerPage200" name="messagesPerPage" value="200" />200
        <input type="radio" id="messagesPerPageAll" name="messagesPerPage" value="0" />All
    </div>
    <br />
    <input type="text" id="data" name="data" rows="3" cols="50"></input>
    <br />
    <input type="button" name="postMessage" id="postMessage" value="Post Message"/>
</sf:form>