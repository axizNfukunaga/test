<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容確認画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
</head>
<body>
	<p>これでよろしいですか？</p>

	<c:if test="${not empty msg}">
		<div class="message">
			<p class="required">${msg}</p>
		</div>
	</c:if>
	<style>
.col {
	float: left;
}

.col-clear {
	clear: both;
}

#arrow {
	margin-top: 60px;
}
</style>
	<form:form action="update" modelAttribute="command">
		<fieldset>
			<div>
				<label>ID</label>
				<form:input path="id" value="${beforeUser.userId}" readonly="true" />
			</div>
		</fieldset>

		<fieldset class="col">
			<legend>変更前</legend>
			<div>
				<label>名前</label>
				<form:input path="name" value="${beforeUser.userName}"
					disabled="true" />
			</div>
			<div>
				<label>TEL</label>
				<form:input path="tel" value="${beforeUser.telephone}"
					disabled="true" />
			</div>
			<div>
				<label>PASS</label>
				<form:password path="pass" value="${beforeUser.password}"
					disabled="true" />
			</div>
		</fieldset>

		<div id="arrow" class="col">⇒</div>

		<fieldset class="col label-110">
			<legend>変更後</legend>
			<div>
				<label>名前</label>
				<form:input path="newName" value="${newUser.userName}"
					readonly="true" />
			</div>
			<div>
				<label>TEL</label>
				<form:input path="newTel" value="${newUser.telephone}"
					readonly="true" />
			</div>
			<div>
				<label>PASS(再入力)</label>
				<form:password path="rePass" value="${Pass}" />
			</div>
		</fieldset>

		<div class="col-clear">
			<form:button>更新</form:button>
			<input type="submit" name="button" value="戻る"
				onclick="location.href='backUpdateInput'; return false;"
				formmethod="get">
		</div>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>