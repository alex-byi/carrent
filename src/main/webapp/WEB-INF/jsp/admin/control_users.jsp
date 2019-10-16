<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
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
                    <c:out value="${sessionScope.error }"></c:out>
                </h2>
                <br>
            </c:if>
            <c:remove var="error" scope="session"/>


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
                                        value="<fmt:message key=" usercontrol.deactivate" />"
                                    onclick="return confirm('
                                    <fmt:message key="usercontrol.deactivate"/>
                                    ?')" /><br/>
                                </form>
                            </c:if>
                            <c:if test="${user.active == false}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="idUser" value="${user.id}"/> <input
                                        type="hidden" name="command" value="activate_user"/> <input
                                        type="submit"
                                        value="<fmt:message key=" usercontrol.activate" />"
                                    onclick="return confirm('
                                    <fmt:message key="usercontrol.activate"/>
                                    ?')" /><br/>
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
                                    value="<fmt:message key=" usercontrol.addcash" />"
                                onclick="return confirm('
                                <fmt:message key="usercontrol.addcash"/>
                                ?')" /><br/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>

</div>

<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>
</html>
