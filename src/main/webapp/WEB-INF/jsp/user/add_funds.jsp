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
                <div id="lang">
                    <center>
                        <form name="LanguageForm" method="POST" action="controller" class="lang">
                            <input type="hidden" name="command" value="set_language" /> <input type="hidden" name="page"
                                value="user_add_funds" /> <input type="hidden" name="command" value="set_language" />
                            <input name="language" type="submit" value="RU" /> <input name="language" type="submit"
                                value="EN" />
                        </form>
                    </center>
                </div>
                <br> <br>
                <c:import url="../common/menu.jsp" charEncoding="utf-8" />
            </div>
            <div>
                <table summary="position table">
                    <!-- блок для позиционирования меню -->
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
                    <fmt:message key='usercontrol.addcash' />
                </h2>

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="user_add_money" />
                    <input type="hidden" name="idUser" value="${user.id}" />
                    <table summary="add cash" id="usertable">
                        <thead>
                            <tr>
                                <th id="add cash cardnumber">
                                    <fmt:message key='addcash.cardnumber' />
                                </th>
                                <th id="add cash name">
                                    <fmt:message key='addcash.name' />
                                </th>
                                <th id="add cash amount">
                                    <fmt:message key='addcash.amount' />
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" required placeholder="XXXXXXXXXXXXXXXX" pattern="[0-9]{16,16}"
                                        maxlength="16" minlength="16" value="" />
                                </td>
                                <td>
                                    <input type="text" required placeholder="<fmt:message key='addcash.name'/>" value=""
                                        maxlength="16" minlength="5" />
                                </td>
                                <td>
                                    <input type="text" required placeholder="<fmt:message key='addcash.amount'/>"
                                        pattern="[0-9]{1,6}" maxlength="5" minlength="2" name="moneyCol" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <center><input type="submit" value="<fmt:message key='usercontrol.addcash'/>"
                                            onclick="return confirm('?')" />
                                    </center>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </form>
            </div>

        </div>

    </div>
    <div class="footer">
        <c:import url="../common/footer.jsp" charEncoding="utf-8" />
    </div>

</body>

</html>