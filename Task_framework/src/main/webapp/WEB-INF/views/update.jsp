<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		更新を行うデータのIDを入力してください<br> <span class="required"></span>は必須です
	</p>
<c:if test="${not empty msg}">
		<div class="message">
			<p class="required">${msg}</p>
		</div>
	</c:if>
	<form:form action="updateInput" modelAttribute="command">
		<div>
			ID:
			<form:input path="id" />
			<form:errors path="id" cssStyle="color: red" />
		</div>
		<div>
			<form:button>確認</form:button>
		</div>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>