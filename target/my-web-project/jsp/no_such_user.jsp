<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="by.htp.jd2.languagebundles.title" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="header.title" /></title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>

<body>

	<div class="page-wrapper">
		<div class="header">
			<c:import url="common/header.jsp" charEncoding="utf-8" />
		</div>
		<div class="page-buffer">
			<center>
				<div id="login">

					<h1>
						<fmt:message key="registration.nosuchuser" />
					</h1>

					<form name='form-login' action="/my-web-project/index.jsp">
						<h1>
							<fmt:message key="registration.wrongname" />
						</h1>
						<br /> <input type="submit"
							value="<fmt:message key="registration.tryagain" />">

					</form>
					<form name='form-login'
						action="/my-web-project/jsp/registration_user.jsp">

						<input type="submit"
							value="<fmt:message key="registration.reg" />">

					</form>
				</div>
				<p>
					<br>
				<center>
					<h2>
						Если нашли какие-то косяки - не ломайте, а скажите мне, и я
						возмооооожно исправлю (<strike>нет</strike>)
					</h2>
				</center>
			</center>
		</div>
	</div>
	<div class="footer">
		<c:import url="common/footer.jsp" charEncoding="utf-8" />
	</div>
</body>

</html>


