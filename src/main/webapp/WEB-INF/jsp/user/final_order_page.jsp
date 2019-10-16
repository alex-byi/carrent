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

<div class="page-wrapper">
    <div class="header">
        <c:import url="../common/header.jsp" charEncoding="utf-8"/>
    </div>
    <div class="page-buffer">
        <div id="menu">

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
                <fmt:message key="userorders.choosencar"/>
                :
            </h2>
            <table id="usertable">
                <thead>
                <tr>
                    <th>
                        <fmt:message key="carcontrol.carname"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.carfuel"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.carcolor"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.carbody"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.transmission"/>
                    </th>
                </tr>
                </thead>
                <tbody>

                <c:set var="car" scope="request" value="${requestScope.selectCar}"/>
                <tr>
                    <td>${car.name}</td>
                    <td>${car.fuel}</td>
                    <td>${car.color}</td>
                    <td>${car.body}</td>
                    <td>
                        <c:if test="${car.transmissionType == 'AUTOMATIC'}">
                            <fmt:message key="carcontrol.automatic"/>
                        </c:if>
                        <c:if test="${car.transmissionType == 'MANUAL'}">
                            <fmt:message key="carcontrol.manual"/>
                        </c:if>
                    </td>

                </tr>
                </tbody>
            </table>

            <h3>
                <fmt:message key="userorders.yourorder"/>
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
                        <fmt:message key="allorders.days"/>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:set var="order" scope="request"
                       value="${requestScope.selectOrder}"/>
                <tr>
                    <td>${order.dateOrder}</td>
                    <td>${order.startDate}</td>
                    <td>${order.endDate}</td>
                    <td>${car.name}</td>
                    <td>${requestScope.orderAmount}</td>
                    <td>${requestScope.dayCol}</td>

                </tr>
                </tbody>
            </table>
            <h3>
                <fmt:message key="userorders.correct"/>
            </h3>
            <table id="usertable">
                <tr>
                    <td>
                        <center>
                            <form action="controller" method="post">

                                <input type="hidden" name="command" value="confirm_order"/> <input
                                    type="hidden" name="orderStart"
                                    value="${requestScope.selectOrder.startDate}"/> <input
                                    type="hidden" name="orderDate"
                                    value="${requestScope.selectOrder.dateOrder}"/> <input
                                    type="hidden" name="orderEnd"
                                    value="${requestScope.selectOrder.endDate}"/> <input
                                    type="hidden" name="orderIdCar"
                                    value="${requestScope.selectOrder.idCar}"/> <input
                                    type="hidden" name="orderIdUser"
                                    value="${requestScope.selectOrder.idUser}"/> <input
                                    type="hidden" name="amountOrder"
                                    value="${requestScope.orderAmount}"/> <input type="hidden"
                                                                                 name="dayCol"
                                                                                 value="${requestScope.dayCol}"/> <input
                                    type="submit" value="<fmt:message key=" addcar.submit"/>" /><br/>
                            </form>
                        </center>
                    </td>
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