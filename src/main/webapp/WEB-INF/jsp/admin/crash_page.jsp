<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                            type="hidden" name="page" value="crash_page_admin"/> <input
                            type="hidden" name="command" value="set_language"/> <input
                            name="language" type="submit" value="RU"/> <input
                            name="language" type="submit" value="EN"/>
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
                                key="role.admin"/>
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


            <h2>
                <fmt:message key="menu.crashbills"/>
            </h2>
            <table id="usertable">
                <thead>
                <tr>
                    <th>
                        <fmt:message key="crashpage.id"/>
                    </th>
                    <th>
                        <fmt:message key="crashpage.damage"/>
                    </th>
                    <th>
                        <fmt:message key="crashpage.amount"/>
                    </th>
                    <th>
                        <fmt:message key="crashpage.car"/>
                    </th>
                    <th>
                        <fmt:message key="crashpage.user"/>
                    </th>
                    <th>
                        <fmt:message key="crashpage.completed"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="crash" items="${allCrashs}">
                    <tr>
                        <td>${crash.id}</td>
                        <td>${crash.damage}</td>
                        <td>${crash.amount}</td>
                        <td>
                            <c:forEach var="car" items="${carsO}">
                                <c:if test="${crash.idCar == car.id }">
                                    ${car.name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="user" items="${usersO}">
                                <c:if test="${crash.idUser == user.id }">
                                    ${user.fullName}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:if test="${crash.complete == true}">
                                <fmt:message key="crashpage.completed"/>
                            </c:if>
                            <c:if test="${crash.complete != true}">
                                <fmt:message key="crashpage.notcompleted"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>
</html>
