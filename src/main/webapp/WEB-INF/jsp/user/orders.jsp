<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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


<c:forEach var="order" items="${userOrders}">
    <c:if
            test="${order.paid != true && order.canceled == false && order.complete == false}">
        <c:set var="printTable" scope="page" value="true"/>
    </c:if>
</c:forEach>
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
                            type="hidden" name="page" value="userorders"/> <input
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
                    <c:out value="${sessionScope.error }"></c:out>
                </h2>
                <br>
            </c:if>
            <c:remove var="error" scope="session"/>

            <h2>
                <fmt:message key="userorders.yourorders"/>
            </h2>

            <c:if test="${printTable == true}">

                <h3>
                    <fmt:message key="userorders.unpaidorders"/>
                </h3>
                <table id="usertable">
                    <thead>
                    <tr>
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
                            <fmt:message key="allorders.car"/>
                        </th>
                        <th>
                            <fmt:message key="allorders.amount"/>
                        </th>
                        <th>
                            <fmt:message key="crashorders.pay"/>
                        </th>
                        <th>
                            <fmt:message key="allorders.cancel"/>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${userOrders}">
                        <c:if
                                test="${order.paid != true && order.canceled == false && order.complete == false}">
                            <tr>
                                <td>${order.dateOrder}</td>
                                <td>${order.startDate}</td>
                                <td>${order.endDate}</td>
                                <td>
                                    <c:forEach var="car" items="${carsO}">
                                        <c:if test="${order.idCar == car.id }">
                                            ${car.name}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td>${order.amount}</td>
                                <td>
                                    <c:if test="${user.cash < order.amount}">
                                        <fmt:message key="crashpage.nomoney"/>
                                    </c:if>
                                    <c:if test="${ order.amount <= user.cash}">
                                        <form name="pay" action="controller" method="post">
                                            <input type="hidden" name="command" value="pay"/> <input
                                                type="hidden" name="orderId" value="${order.id}"/> <input
                                                type="hidden" name="userId"
                                                value="${sessionScope.user.id}"/> <input type="submit"
                                                                                         value="<fmt:message key="
                                                                                         crashorders.pay"/>"
                                            onclick="return confirm('
                                            <fmt:message key="crashorders.pay"/>
                                            ?')" /><br/>
                                        </form>
                                    </c:if>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="command" value="cancel_order"/>
                                        <input type="hidden" name="orderId" value="${order.id}"/>
                                        <input type="hidden" name="userType" value="user"/> <input
                                            type="hidden" name="reason" value="Отменен пользователем"/><input
                                            type="submit"
                                            value="<fmt:message key=" allorders.cancel" />"
                                        onclick="return confirm('
                                        <fmt:message key="allorders.cancel"/>
                                        ?')" /><br/>

                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>

                </table>
            </c:if>

            <h3>
                <fmt:message key="userorders.paidorders"/>
            </h3>
            <table id="usertable">
                <thead>
                <tr>
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
                        <fmt:message key="allorders.additionalbill"/>
                    </th>
                    <th>
                        <fmt:message key="allorders.amount"/>
                    </th>
                    <th>
                        <fmt:message key="userorders.comments"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${userOrders}">
                    <c:if test="${order.paid == true || order.canceled == true}">
                        <tr>
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
                            <td>${order.crashBill}</td>
                            <td>${order.amount}</td>
                            <c:if test="${order.paid == false}" var="testcif">
                                <td>
                                    <c:if test="${order.canceled == true}">
                                        <fmt:message key="userorders.rejectreason"/>
                                        :
                                        ${order.rejectReason}
                                    </c:if>
                                </td>
                            </c:if>
                        </tr>
                    </c:if>
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
