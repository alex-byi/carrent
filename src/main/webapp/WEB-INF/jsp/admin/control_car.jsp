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
                            type="hidden" name="page" value="control_car"/> <input
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
            <p>
            <table id="usertable">
                <tr>
                    <th>Поиск по типу трансмиссии</th>
                    <th>Поиск по типу топлива</th>
                    <th>Добавить автомобиль</th>
                </tr>
                <tr>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command"
                                   value="GET_TRANSMISSION_CARS"/> <select
                                name="searchtransmission" required>
                            <option disabled>
                                <fmt:message
                                        key="addcar.transmission"/>
                            </option>

                            <option value="AUTOMATIC">
                                <fmt:message
                                        key="addcar.automatic"/>
                            </option>
                            <option value="MANUAL">
                                <fmt:message
                                        key="addcar.manual"/>
                            </option>
                        </select><input type="submit" value=" Поиск"/><br/>
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="GET_FUEL_CARS"/> <select
                                name="searchFuel" required>
                            <option disabled>
                                <fmt:message key="addcar.fueltype"/>
                            </option>
                            <option value="бензин">
                                <fmt:message key="addcar.gas"/>
                            </option>
                            <option value="дизель">
                                <fmt:message
                                        key="addcar.diesel"/>
                            </option>
                            <option value="электричество">
                                <fmt:message
                                        key="addcar.electricity"/>
                            </option>
                        </select><input type="submit" value=" Поиск"/><br/>
                        </form>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="add_car_page"/> <input
                                type="submit" value=<fmt:message key="carcontrol.addcar"/> /><br/>
                        </form>
                    </td>
                </tr>
            </table>
            <p>
                <br>
                <c:if test="${requestScope.fuelCar != null}" var="testcif">
                    <h2>Все автомобили с ... двигателем:</h2>
                    <table id="usertable">
                        <tr>
                            <th>
                                <fmt:message key="carcontrol.id"/>
                            </th>
                            <th>
                                <fmt:message key="carcontrol.carname"/>
                            </th>
                            <th>
                                <fmt:message key="carcontrol.carprice"/>
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
                            <th>
                                <fmt:message key="carcontrol.activity"/>
                            </th>
                            <th>
                                <fmt:message key="carcontrol.activ"/>
                            </th>
                        </tr>
                        <c:forEach var="car" items="${fuelCar}">
                            <c:if test="${car.active == true}" var="testcif">
                                <tr>
                                    <td>${car.id}</td>
                                    <td>${car.name}</td>
                                    <td>${car.price}</td>
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
                                    <td>
                                        <c:if test="${car.active == true}">
                                            <fmt:message key="carcontrol.active"/>
                                        </c:if>
                                        <c:if test="${car.active != true}">
                                            <fmt:message key="carcontrol.notactive"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${car.active == true }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="del_car"/> <input
                                                    type="submit"
                                                    value=<fmt:message key="carcontrol.deactivate"/>
                                                onclick="return confirm('
                                                <fmt:message key="carcontrol.deactivate"/>
                                                ?')" /><br/>
                                            </form>
                                        </c:if>
                                        <c:if test="${car.active == false }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="activate_car"/> <input
                                                    type="submit"
                                                    value=<fmt:message key="carcontrol.activate"/>
                                                onclick="return confirm('
                                                <fmt:message key="carcontrol.activate"/>
                                                ?')" /><br/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>

                        </c:forEach>

                    </table>
                </c:if>

                <c:if test="${requestScope.transmissionCar != null}" var="testcif">
                    <h2>Все автомобили с ... трансмиссией:</h2>
                    <table id="usertable">
                        <tr>
                            <th>
                                <fmt:message key="carcontrol.id"/>
                            </th>
                            <th>
                                <fmt:message key="carcontrol.carname"/>
                            </th>
                            <th>
                                <fmt:message key="carcontrol.carprice"/>
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
                            <th>
                                <fmt:message key="carcontrol.activity"/>
                            </th>
                            <th>
                                <fmt:message key="carcontrol.activ"/>
                            </th>
                        </tr>
                        <c:forEach var="car" items="${transmissionCar}">
                            <c:if test="${car.active == true}" var="testcif">
                                <tr>
                                    <td>${car.id}</td>
                                    <td>${car.name}</td>
                                    <td>${car.price}</td>
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
                                    <td>
                                        <c:if test="${car.active == true}">
                                            <fmt:message key="carcontrol.active"/>
                                        </c:if>
                                        <c:if test="${car.active != true}">
                                            <fmt:message key="carcontrol.notactive"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${car.active == true }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="del_car"/> <input
                                                    type="submit"
                                                    value=<fmt:message key="carcontrol.deactivate"/>
                                                onclick="return confirm('
                                                <fmt:message key="carcontrol.deactivate"/>
                                                ?')" /><br/>
                                            </form>
                                        </c:if>
                                        <c:if test="${car.active == false }">
                                            <form action="controller" method="post">
                                                <input type="hidden" name="idCar" value="${car.id}"/> <input
                                                    type="hidden" name="command" value="activate_car"/> <input
                                                    type="submit"
                                                    value=<fmt:message key="carcontrol.activate"/>
                                                onclick="return confirm('
                                                <fmt:message key="carcontrol.activate"/>
                                                ?')" /><br/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:if>

                        </c:forEach>

                    </table>
                </c:if>

                <c:if test="${requestScope.transmissionCar == null && requestScope.fuelCar == null}" var="testcif">
            <p>
                <br>
            <h2>
                <fmt:message key="menu.carcontrol"/>
            </h2>
            <table id="usertable">

                <tr>
                    <th>
                        <fmt:message key="carcontrol.id"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.carname"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.carprice"/>
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
                    <th>
                        <fmt:message key="carcontrol.activity"/>
                    </th>
                    <th>
                        <fmt:message key="carcontrol.activ"/>
                    </th>
                </tr>

                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td>${car.id}</td>
                        <td>${car.name}</td>
                        <td>${car.price}</td>
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
                        <td>
                            <c:if test="${car.active == true}">
                                <fmt:message key="carcontrol.active"/>
                            </c:if>
                            <c:if test="${car.active != true}">
                                <fmt:message key="carcontrol.notactive"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${car.active == true }">
                                <form action="controller" method="post">
                                    <input type="hidden" name="idCar" value="${car.id}"/> <input
                                        type="hidden" name="command" value="del_car"/> <input
                                        type="submit"
                                        value=<fmt:message key="carcontrol.deactivate"/>
                                    onclick="return confirm('
                                    <fmt:message key="carcontrol.deactivate"/>
                                    ?')" /><br/>
                                </form>
                            </c:if>
                            <c:if test="${car.active == false }">
                                <form action="controller" method="post">
                                    <input type="hidden" name="idCar" value="${car.id}"/> <input
                                        type="hidden" name="command" value="activate_car"/> <input
                                        type="submit"
                                        value=<fmt:message key="carcontrol.activate"/>
                                    onclick="return confirm('
                                    <fmt:message key="carcontrol.activate"/>
                                    ?')" /><br/>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
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
