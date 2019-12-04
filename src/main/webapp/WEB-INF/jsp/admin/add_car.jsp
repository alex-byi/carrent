<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="title" />
    <title>
        <fmt:message key="header.title" />
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="..\css\style.css" type="text/css">

</head>

<body>
    <div class="page-wrapper">
        <div class="header">
            <c:import url="../common/header.jsp" charEncoding="utf-8" />
        </div>
        <div class="page-buffer">
            <div id="menu">

                <br> <br>
                <c:import url="../common/menu_admin.jsp" charEncoding="utf-8" />
            </div>
            <div>
                <table summary="user role table">
                    <tr>
                        <th id="user role" colspan=2>
                            <fmt:message key="role.admin" />
                        </th>
                    </tr>
                </table>
            </div>
            <div id="page-content">

                <c:if test="${sessionScope.error != null }">
                    <h2>
                        <c:out value="${sessionScope.error }" />
                    </h2>
                    <br>
                </c:if>
                <c:remove var="error" scope="session" />

                <h2>
                    <fmt:message key="menu.carcontrol" />
                </h2>
                <form name="AddingNewCar" action="controller" method="post">

                    <input type="hidden" name="command" value="add_car" />
                    <center>
                        <table  summary="add car table" id="usertable">
                            <tr>
                                <th id="add car" colspan=2>
                                    <center>
                                        <font size=5>
                                            <fmt:message key="carcontrol.addcar" />
                                        </font>
                                    </center>
                                </th>
                            </tr>
                            <tr>
                                <td valign=top>
                                    <fmt:message key="carcontrol.carname" />
                                    <h7>*</h7>
                                    <input type="text" name="carName" value="" required placeholder=<fmt:message
                                        key="carcontrol.carname" /> size=37
                                    maxlength=50>
                                </td>
                            </tr>
                            <tr>
                                <td valign=top>
                                    <fmt:message key="carcontrol.carprice" />
                                    <h7>*</h7>
                                    <input type="number" min="0" name="carPrice" value="" required
                                        placeholder=<fmt:message key="carcontrol.carprice" /> size=26
                                    maxlength=125>
                                </td>
                            <tr>
                                <td valign=top>
                                    <fmt:message key="carcontrol.carfuel" />
                                    <h7>*</h7>
                                    <select name="carFuel" required>
                                        <option>
                                            <fmt:message key="addcar.fueltype" />
                                        </option>
                                        <option value="бензин">
                                            <fmt:message key="addcar.gas" />
                                        </option>
                                        <option value="дизель">
                                            <fmt:message key="addcar.diesel" />
                                        </option>
                                        <option value="электричество">
                                            <fmt:message key="addcar.electricity" />
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td valign=top>
                                    <fmt:message key="carcontrol.carcolor" />
                                    <h7>*</h7>
                                    <input type="text" name="carColor" size=26 value="" required
                                        placeholder=<fmt:message key="carcontrol.carcolor" />
                                    maxlength=10>
                                </td>
                            <tr>
                                <td valign=top>
                                    <fmt:message key="carcontrol.carbody" />
                                    <h7>*</h7>
                                    <select name="carBody" required>
                                        <option>
                                            <fmt:message key="addcar.bodytype" />
                                        </option>
                                        <option value="седан">
                                            <fmt:message key="addcar.sedan" />
                                        </option>
                                        <option value="универсал">
                                            <fmt:message key="addcar.touring" />
                                        </option>
                                        <option value="купе">
                                            <fmt:message key="addcar.coupe" />
                                        </option>
                                        <option value="кабриолет">
                                            <fmt:message key="addcar.cabriolet" />
                                        </option>
                                        <option value="минивэн">
                                            <fmt:message key="addcar.minivan" />
                                        </option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td valign=top>
                                    <fmt:message key="carcontrol.transmission" />
                                    <h7>*</h7>
                                    <select name="transmission" required>
                                        <option>
                                            <fmt:message key="addcar.transmission" />
                                        </option>
                                        <option value="AUTOMATIC">
                                            <fmt:message key="addcar.automatic" />
                                        </option>
                                        <option value="MANUAL">
                                            <fmt:message key="addcar.manual" />
                                        </option>
                                    </select>
                                </td>
                            </tr>

                            <tr>
                                <td align=center><input type="submit" value="<fmt:message key='addcar.submit'/>"
                                        onClick="alert('?')">
                            </tr>
                        </table>
                    </center>
                </form>
            </div>

        </div>

    </div>
    <div class="footer">
        <c:import url="../common/footer.jsp" charEncoding="utf-8" />
    </div>

</body>

</html>