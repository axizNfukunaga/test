<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty msg}">
		<div class="message">
			<p class="required">${msg}</p>
		</div>
	</c:if>
	<form:form action="login" modelAttribute="login">
		<fieldset>
			<div>
				<label>ID</label>
				<form:input path="id" />
			</div>
			<div>
				<label>PASS</label>
				<form:password path="pass" />
			</div>
			<div>
				<form:button>送信</form:button>
			</div>
		</fieldset>
	</form:form>
	<div>
		<a href="index">TOP画面へ</a>
	</div>
</body>
</html>