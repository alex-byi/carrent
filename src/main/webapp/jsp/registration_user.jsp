<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">
<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message key="header.title"/>
    </title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>

<div class="header">
    <c:import url="common/header.jsp" charEncoding="utf-8"/>
</div>
<c:if test="${sessionScope.duplicateLogin != null }">
    <center>
        <h2>
            <c:out value="${duplicateLogin}"/>
        </h2>
    </center>
</c:if>
<c:remove var="duplicateLogin" scope="session"/>
<center>

    <div id="login">
        <h1>
            <fmt:message key="registration.title"/>
        </h1>
        <form name="LanguageForm" method="POST" action="../controller"
              class="lang">
            <input type="hidden" name="command" value="set_language"/> <input
                type="hidden" name="page" value="registration"/> <input
                name="language" type="submit" value="RU"/> <input type="hidden"
                                                                  name="command" value="set_language"/> <input
                name="language"
                type="submit" value="EN"/>
        </form>
        <form name="RegisterForm" method="POST" action="../controller"
              accept-Charset="UTF-8">
            <input type="hidden" name="command" value="registration"/>

            <div class="titling">
                <fmt:message key="registration.login"/>
                <h7>*</h7>
            </div>

            <input type="text" name="login" minlength="5" value="" autofocus
                   required placeholder=<fmt:message key="registration.login"/>>
            <div class="titling">
                <fmt:message key="registration.password"/>
                <h7>*</h7>
            </div>
            <input type="password" name="pass" minlength="6" value="" required
                   placeholder=<fmt:message key="registration.password"/>>

            <div class="titling">
                <fmt:message key="registration.fullname"/>
                <h7>*</h7>
            </div>
            <input type="text" name="fullname" minlength="6" maxlength="25"
                   value="" required
                   placeholder=<fmt:message key="registration.fullname"/>>

            <div class="titling">
                <fmt:message key="registration.address"/>
                <h7>*</h7>
            </div>
            <input type="text" name="address" minlength="6" value="" required
                   placeholder=<fmt:message key="registration.address"/>>

            <div class="titling">
                <fmt:message key="registration.email"/>
                <h7>*</h7>
            </div>
            <input type="email" name="email" minlength="6" maxlength="25"
                   value="" required
                   placeholder=<fmt:message key="registration.email"/>>

            <div class="titling">
                <fmt:message key="registration.passportnumber"/>
                <h7>*</h7>
            </div>
            <input type="text" name="passnumber" minlength="6" value="" required
                   placeholder=<fmt:message key="registration.passportnumber"/>>
            <br/> <input type="submit"
                         value=<fmt:message key="registration.submit"/>> <br/>
        </form>
        <form name="back">
            <input type="submit" value=<fmt:message key="registration.back"/>
            onclick="window.history.go(-1); return false;" />
        </form>
    </div>
</center>
<div class="footer">
    <c:import url="common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>