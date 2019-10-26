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
                            type="hidden" name="page" value="control_users"/> <input
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


            <c:if test="${userOrders != null}" var="testcif">

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
                            <fmt:message key="allorders.amount"/>
                        </th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${userOrders}">
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
                            <td>${order.amount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table id="usertable">
                    <tr>
                        <td>
                            <center>
                                <form name="back">
                                    <input type="submit" value=<fmt:message key="registration.back"/>
                                    onclick="window.history.go(-1); return false;" />
                                </form>
                            </center>
                        </td>
                    </tr>
                </table>

            </c:if>

            <c:if test="${userOrders == null}" var="testcif">

                <table id="usertable">

                    <tr>
                        <th>
                            <fmt:message key='search.user'/>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command"
                                       value="USER_SEARCH"/>
                                <input type="text" name="searchLogin" value=""/>
                                <input type="submit" value=" <fmt:message
                                        key='search.search'/>"/>
                            </form>
                        </td>
                    </tr>
                </table>
                <br>


                <c:if test="${searchedUsers != null}" var="testcif">
                    <table id="usertable">
                        <tr>
                            <th>
                                <fmt:message key="usercontrol.login"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.fullname"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.passportnumber"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.email"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.address"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.cash"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.role"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.activ"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.addcash"/>
                            </th>
                            <th>
                                Показать заказы
                            </th>
                        </tr>
                        <c:forEach var="user" items="${searchedUsers}">
                            <tr>
                                <td>${user.login}</td>
                                <td>${user.fullName}</td>
                                <td>${user.passNum}</td>
                                <td>${user.email}</td>
                                <td>${user.address}</td>
                                <td>${user.cash}</td>
                                <td>
                                    <c:if test="${user.type == 'ADMIN'}">
                                        <fmt:message key="role.admin"/>
                                    </c:if>
                                    <c:if test="${user.type == 'USER'}">
                                        <fmt:message key="crashpage.user"/>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${user.active == true}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="idUser" value="${user.id}"/> <input
                                                type="hidden" name="command" value="del_user"/> <input
                                                type="submit"
                                                value="<fmt:message key='usercontrol.deactivate'/>"
                                                onclick="return confirm('?')"/>
                                        </form>
                                    </c:if>
                                    <c:if test="${user.active == false}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="idUser" value="${user.id}"/> <input
                                                type="hidden" name="command" value="activate_user"/> <input
                                                type="submit"
                                                value="<fmt:message key='usercontrol.activate'/>"
                                                onclick="return confirm('?')"/>
                                        </form>
                                    </c:if>
                                </td>

                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="idUser" value="${user.id}"/> <input
                                            type="hidden" name="command" value="add_money"/> <input
                                            type="number" required min="0" maxlength="6"
                                            pattern="[0-9]{1,6}" name="moneyCol" value=""/> <input
                                            type="submit"
                                            value="<fmt:message key='usercontrol.addcash'/>"
                                            onclick="return confirm('?')"/>
                                    </form>
                                </td>
                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="idUser" value="${user.id}"/> <input
                                            type="hidden" name="command" value="USER_ORDERS_SEARCH"/>
                                        <input type="submit" value="<fmt:message key='search.search'/>"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>


                <br>

                <c:if test="${searchedUsers == null}" var="testcif">
                    <h2>
                        <fmt:message key="menu.usercontrol"/>
                    </h2>
                    <table id="usertable">
                        <tr>
                            <th>
                                <fmt:message key="usercontrol.login"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.fullname"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.passportnumber"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.email"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.address"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.cash"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.role"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.activity"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.activ"/>
                            </th>
                            <th>
                                <fmt:message key="usercontrol.addcash"/>
                            </th>
                        </tr>
                        <c:forEach var="user" items="${allUsers}">
                            <tr>
                                <c:set var="id" scope="session" value="${user.id}"/>
                                <td>${user.login}</td>
                                <td>${user.fullName}</td>
                                <td>${user.passNum}</td>
                                <td>${user.email}</td>
                                <td>${user.address}</td>
                                <td>${user.cash}</td>
                                <td>
                                    <c:if test="${user.type == 'ADMIN'}">
                                        <fmt:message key="role.admin"/>
                                    </c:if>
                                    <c:if test="${user.type == 'USER'}">
                                        <fmt:message key="crashpage.user"/>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${user.active == true}">
                                        <fmt:message key="usercontrol.active"/>
                                    </c:if>
                                    <c:if test="${user.active!= true}">
                                        <fmt:message key="usercontrol.notactive"/>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${user.active == true}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="idUser" value="${user.id}"/> <input
                                                type="hidden" name="command" value="del_user"/> <input
                                                type="submit"
                                                value="<fmt:message key='usercontrol.deactivate'/>"
                                                onclick="return confirm('?')"/>
                                        </form>
                                    </c:if>
                                    <c:if test="${user.active == false}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="idUser" value="${user.id}"/> <input
                                                type="hidden" name="command" value="activate_user"/> <input
                                                type="submit"
                                                value="<fmt:message key='usercontrol.activate'/>"
                                                onclick="return confirm('?')"/>
                                        </form>
                                    </c:if>
                                </td>

                                <td>
                                    <form action="controller" method="post">
                                        <input type="hidden" name="idUser" value="${user.id}"/> <input
                                            type="hidden" name="command" value="add_money"/> <input
                                            type="number" required size="6" min="0" maxlength="6"
                                            pattern="[0-9]{1,6}" name="moneyCol" value=""/> <input
                                            type="submit"
                                            value="<fmt:message key='usercontrol.addcash'/>"
                                            onclick="return confirm('?')"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <center>
                        <table id="usertable">
                            <tr>
                                <td width="50%">
                                    <center>
                                        <c:if test="${currentPage != 0 }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="all_users"/> <input
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
                                                <input type="hidden" name="command" value="all_users"/> <input
                                                    type="hidden" name="currentPage" value="${currentPage + 1}"/>
                                                <input type="submit" value="<fmt:message key='page.forward'/>"/><br/>
                                            </form>
                                        </c:if>
                                    </center>
                                </td>
                            </tr>
                        </table>
                    </center>
                </c:if>

            </c:if>
        </div>

    </div>

</div>

<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>
</html>
