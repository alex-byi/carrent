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
                            type="hidden" name="page" value="auth"/> <input type="hidden"
                                                                            name="command" value="set_language"/> <input
                            name="language"
                            type="submit" value="RU"/> <input name="language"
                                                              type="submit" value="EN"/>
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
                <fmt:message key="allorders.createaddbill"/>
            </h2>

            <form action="controller" method="post">
                <input type="hidden" name="command" value="add_crash"/> <input
                    type="hidden" name="orderId" value="${orderId}"/> <input
                    type="hidden" name="userId" value="${userId}"/> <input
                    type="hidden" name="carId" value="${carId}"/>
                <table id="usertable">
                    <tr>
                        <th colspan=2><font size=5>
                            <fmt:message
                                    key="crashorders.addcrash"/>
                        </font></th>
                    </tr>
                    <tr>
                        <td valign=top>
                            <fmt:message key="crashorders.damagedescr"/>
                            <input type="text" name="description" value="" required
                                   placeholder="<fmt:message key=" crashorders.damagedescr" /> "
                            size=37 maxlength=50>
                        </td>
                    </tr>
                    <tr>
                        <td valign=top>
                            <fmt:message key="crashorders.amount"/>
                            <input
                                    type="number" min="0" name="amount" value="" required
                                    placeholder="<fmt:message key=" crashorders.amount"/> " size=37
                            maxlength=50>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <center>
                                <input type="submit"
                                       value="<fmt:message key=" crashorders.create"/>" />
                            </center>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </div>

</div>
<div class="footer">
    <c:import url="../common/footer.jsp" charEncoding="utf-8"/>
</div>

</body>
</html>
