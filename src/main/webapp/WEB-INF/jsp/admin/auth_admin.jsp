<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="custom" uri="/WEB-INF/tld/custom.tld"%>

<html xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">
<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <title>
        <fmt:message key="header.title"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="..\css\style.css" type="text/css">
</head>

<body>
<div class="page-wrapper">
    <div class="header">
        <c:import url="../common/header.jsp" charEncoding="utf-8"/>
    </div>
    <div class="page-buffer">
        <div id="menu">
            <div id="lang">

                <center>
                    <form name="LanguageForm" method="POST" action="controller"
                          class="lang">
                        <input type="hidden" name="command" value="set_language"/> <input
                            type="hidden" name="page" value="auth"/> <input type="hidden"
                                                                            name="command" value="set_language"/> <input
                            name="language"
                            type="submit" value="RU"/> <input name="language"
                                                              type="submit" value="EN"/>
                    </form>
                </center>
            </div>
            <br> <br>
            <c:import url="../common/menu_admin.jsp" charEncoding="utf-8"/>
        </div>
        <div>
            <table>
                <tr>
                    <th colspan=2><font size=5>
                        <fmt:message
                                key="auth.hello"/>
                        <custom:info-tag
                                type="${sessionScope.user.type}"
                                username="${sessionScope.user.login}">
                            <fmt:message key="auth.logmessage"/>
                        </custom:info-tag>
                    </font></th>

                </tr>
            </table>
        </div>
        <div id="page-content">
            <c:if test="${sessionScope.error != null }">
                <h2>
                    <c:out value="${sessionScope.error }"/>
                </h2>
                <br>
            </c:if>
            <c:remove var="error" scope="session"/>


            <c:set var="user" scope="session" value="${sessionScope.user}"/>
            <table id="usertable">
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message
                                key="userpage.fullname"/>
                    </td>
                    <td style="font-size: 20pt;">${user.fullName}</td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message
                                key="userpage.username"/>
                    </td>
                    <td style="font-size: 20pt;">${user.login}</td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message
                                key="userpage.email"/>
                    </td>
                    <td style="font-size: 20pt;">${user.email}</td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message
                                key="userpage.address"/>
                    </td>
                    <td style="font-size: 20pt;">${user.address}</td>
                </tr>
                <tr>
                    <td style="font-size: 20pt;">
                        <fmt:message key="userpage.cash"/>
                    </td>
                    <td style="font-size: 20pt;">${user.cash}</td>
                </tr>
            </table>
        </div>

    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>
</body>
</html>
