<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >

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
            <div id="lang">

                <center>
                    <form name="LanguageForm" method="POST" action="controller"
                          class="lang">
                        <input type="hidden" name="command" value="set_language"/> <input
                            type="hidden" name="page" value="user_car_page"/> <input type="hidden"
                                                                                     name="command"
                                                                                     value="set_language"/> <input
                            name="language"
                            type="submit" value="RU"/> <input name="language"
                                                              type="submit" value="EN"/>
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
            <p>
            <table id="usertable">
                <tr>
                    <th>Поиск по типу трансмиссии</th>
                    <th>Поиск по типу топлива</th>
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
                </tr>


            </table>

            <p>
                <br>

                <c:if test="${requestScope.fuelCar != null}" var="testcif">
                    <h2>Все автомобили с ... двигателем:</h2>
                    <table id="usertable">
                        <tr>
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
                        </tr>
                        <c:forEach var="car" items="${fuelCar}">
                            <c:if test="${car.active == true}" var="testcif">
                                <tr>
                                    <td>${car.name}</td>
                                    <td>${car.price}</td>
                                    <td>${car.fuel}</td>
                                    <td>${car.color}</td>
                                    <td>${car.body}</td>
                                    <td>
                                        <c:if test="${car.transmissionType == 'AUTOMATIC'}"> Автоматическая</c:if>
                                        <c:if test="${car.transmissionType == 'MANUAL'}"> Механическая</c:if>
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
                        </tr>
                        <c:forEach var="car" items="${transmissionCar}">
                            <c:if test="${car.active == true}" var="testcif">
                                <tr>
                                    <td>${car.name}</td>
                                    <td>${car.price}</td>
                                    <td>${car.fuel}</td>
                                    <td>${car.color}</td>
                                    <td>${car.body}</td>
                                    <td>
                                        <c:if test="${car.transmissionType == 'AUTOMATIC'}"> Автоматическая</c:if>
                                        <c:if test="${car.transmissionType == 'MANUAL'}"> Механическая</c:if>
                                    </td>
                                </tr>
                            </c:if>

                        </c:forEach>

                    </table>
                </c:if>

                <c:if
                        test="${requestScope.transmissionCar == null && requestScope.fuelCar == null}"
                        var="testcif">
            <p>
                <br>
            <h2>
                <fmt:message key="menu.allcars"/>
            </h2>

            <table id="usertable">
                <tr>
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
                </tr>
                <c:forEach var="car" items="${carsP}">
                    <c:if test="${car.active == true}" var="testcif">
                        <tr>
                            <td>${car.name}</td>
                            <td>${car.price}</td>
                            <td>${car.fuel}</td>
                            <td>${car.color}</td>
                            <td>${car.body}</td>
                            <td>
                                <c:if test="${car.transmissionType == 'AUTOMATIC'}"> Автоматическая</c:if>
                                <c:if test="${car.transmissionType == 'MANUAL'}"> Механическая</c:if>
                            </td>
                        </tr>
                    </c:if>

                </c:forEach>

            </table>

            <table id="usertable">
                <tr>

                    <td width="50%">
                        <center>
                            <form name="menu" class="menu" action="controller"
                                  method="post">
                                <input type="hidden" name="command" value="car_page"/> <input
                                    type="hidden" name="page" value="${pageR - 1}"/>
                                <c:if test="${pageR > 1}">
                                    <input type="submit" value="Предыдущие автомобили"/>
                                </c:if>
                                <br/>
                            </form>
                        </center>
                    </td>
                    <td width="50%">
                        <center>
                            <form name="menu" class="menu" action="controller"
                                  method="post">
                                <input type="hidden" name="command" value="car_page"/> <input
                                    type="hidden" name="page" value="${pageR + 1}"/>
                                <c:if test="${pageR <= pageEnd}">
                                    <input type="submit" value="Следующие автомобили"/>
                                </c:if>


                                <br/>
                            </form>
                        </center>
                    </td>
                </tr>
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
