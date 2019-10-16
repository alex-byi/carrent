<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message key="header.title"/>
    </title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>

<div class="page-wrapper">
    <div class="header">
        <c:import url="common/header.jsp" charEncoding="utf-8"/>
    </div>
    <div class="page-buffer">
        <center>
            <div id="login">

                <form name="LanguageForm" method="POST" action="controller" class="lang">
                    <input type="hidden" name="command" value="set_language"/> <input type="hidden" name="page"
                                                                                      value="index"/> <input
                        name="language" type="submit" value="RU"/> <input type="hidden"
                                                                          name="command" value="set_language"/> <input
                        name="language" type="submit" value="EN"/>
                </form>

                <form name='form-login' method="POST" action="controller">
                    <h1>
                        <fmt:message key="login.title"/>
                    </h1>
                    <br/> <input type="hidden" name="command" value="authorization"/>
                    <input type="text" name="login" value="" required placeholder=<fmt:message key="login.name"/> />
                    <input type="password" name="pass" value="" required
                           placeholder=<fmt:message key="login.password"/> /> <input
                        type="submit" value=<fmt:message key="login.enter"/>>
                </form>
                <form name="RegisterForm" method="POST" action='jsp/registration_user.jsp'>
                    <input type="submit" value=<fmt:message key="login.register"/> >
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
    <c:import url="common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>

</html>