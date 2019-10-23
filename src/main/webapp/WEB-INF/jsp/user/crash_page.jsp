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
                            type="hidden" name="page" value="user_crashs"/> <input
                            type="hidden" name="command" value="set_language"/> <input
                            name="language" type="submit" value="RU"/> <input
                            name="language" type="submit" value="EN"/>
                    </form>
                </center>
            </div>
            <br> <br>
            <c:import url="../common/menu.jsp" charEncoding="utf-8"/>
        </div>
        <div>
            <table>
                <!-- блок для позиционирования меню -->
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
                <fmt:message key="crashorders.youcrashes"/>
            </h2>

            <c:if test="${requestScope.allCrashs == null}">
                <br>
                <br>
                <br>
                <center><h2>Поздравляем, Вы хороший водитель, и вас нету дополнительных счетов</h2></center>
            </c:if>

            <c:if test="${requestScope.allCrashs != null}">

                <table id="usertable">
                    <thead>
                    <tr>
                        <th>
                            <fmt:message key="crashpage.damage"/>
                        </th>
                        <th>
                            <fmt:message key="crashpage.amount"/>
                        </th>
                        <th>
                            <fmt:message key="crashpage.car"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="crash" items="${requestScope.allCrashs}">
                        <tr>
                            <td>${crash.damage}</td>
                            <td>${crash.amount}</td>
                            <td>
                                <c:forEach var="car" items="${cars}">
                                    <c:if test="${crash.idCar == car.id }">
                                        <c:out value="${car.name}"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:if test="${crash.complete == false }">
                                    <c:if test="${crash.amount > user.cash}">
                                        <fmt:message key="crashpage.nomoney"/>
                                    </c:if>
                                    <c:if test="${crash.amount < user.cash}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="USER_CRASH_PAY"/>
                                            <input type="hidden" name="crashId" value="${crash.id}"/>
                                            <input type="hidden" name="amount" value="${crash.amount}"/>
                                            <input type="submit"
                                                   value=<fmt:message key="crashorders.pay"/>
                                            onclick="return confirm('
                                            <fmt:message key="crashorders.pay"/>
                                            ?')" /><br/>
                                        </form>
                                    </c:if>
                                </c:if>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>
</html>
