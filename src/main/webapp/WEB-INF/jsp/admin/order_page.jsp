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
                            type="hidden" name="page" value="order_page_admin"/> <input
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
                <fmt:message key="menu.allorders"/>
            </h2>


            <table id="usertable">
                <thead>
                <tr>
                    <th>
                        <fmt:message key="allorders.id"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.date"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.startdate"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.enddate"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.payment"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.damage"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.car"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.days"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.additionalbill"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.user"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.amount"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.cancel"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.complete"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.createaddbill"/>
                    </th>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${allOrders}">
                    <tr>

                        <td>${order.id}</td>
                        <c:set var="id" scope="session" value="${order.id}"/>
                        <td>${order.dateOrder}</td>
                        <td>${order.startDate}</td>
                        <td>${order.endDate}</td>
                        <td>
                            <c:if test="${order.paid == true}">
                                <fmt:message key="allorders.paid"/>
                            </c:if>
                            <c:if test="${order.paid != true}">
                                <fmt:message key="allorders.notpaid"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${order.crash == true}">
                                <fmt:message key="allorders.crash"/>
                            </c:if>
                            <c:if test="${order.crash != true}">
                                <fmt:message key="allorders.notcrash"/>
                            </c:if>
                        </td>
                        <td>
                            <c:forEach var="car" items="${carsO}">
                                <c:if test="${order.idCar == car.id }">
                                    ${car.name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${order.dayCol}</td>
                        <td>${order.crashBill}</td>
                        <td>
                            <c:forEach var="user" items="${usersO}">
                                <c:if test="${order.idUser == user.id }">
                                    ${user.fullName}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${order.amount}</td>
                        <td>
                            <c:if
                                    test="${order.canceled == false && order.complete == false}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="cancel_order"/>
                                    <input type="hidden" name="orderId" value="${order.id}"/> <input
                                        type="text" required
                                        placeholder="<fmt:message key='allorders.cause'/>" size="8"
                                        maxlength="40" name="reason" value=""/><input
                                        type="submit" value="<fmt:message key='allorders.cancel'/>"
                                        onclick="return confirm('?')"/>
                                </form>
                            </c:if>
                            <c:if test="${order.canceled != false}"> ${order.rejectReason}</c:if>

                        </td>
                        <td>
                            <c:if
                                    test="${order.complete == false && order.canceled == false}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="complete_order"/>
                                    <input type="hidden" name="orderId" value="${order.id}"/> <input
                                        type="submit"
                                        value="<fmt:message key='allorders.complete'/>"
                                        onclick="return confirm('?')"/>
                                </form>
                            </c:if>
                            <c:if
                                    test="${order.complete == true}">
                                <fmt:message key='allorders.successfullyComplete'/>
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${order.crash == false}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="command"
                                           value="new_crash_order_page"/><input type="hidden" name="orderId"
                                                                                value="${order.id}"/>
                                    <input type="hidden"
                                           name="carId" value="${order.idCar}"/> <input type="hidden" name="userId"
                                                                                        value="${order.idUser}"/><input
                                        type="submit"
                                        value="<fmt:message key='allorders.createaddbill'/>"/>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <center>
                <table id="usertable">
                    <tr>
                        <td width="50%">
                            <center>
                                <c:if test="${currentPage != 0 }">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="order_page"/> <input
                                            type="hidden" name="currentPage" value="${currentPage - 1}"/>
                                        <input type="submit" value="<fmt:message key='page.back'/>"/><br/>
                                    </form>
                                </c:if>
                            </center>
                        </td>
                        <td width="50%">
                            <center>
                                <c:if test="${id>1}">
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="order_page"/> <input
                                            type="hidden" name="currentPage" value="${currentPage + 1}"/>
                                        <input type="submit" value="<fmt:message key='page.forward'/>"/><br/>
                                    </form>
                                </c:if>
                            </center>
                        </td>
                    </tr>
                </table>
            </center>
        </div>

    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>
</html>
