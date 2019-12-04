<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform">

<head>
    <title></title>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="title" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
    <div id="login">
        <form name="menu" class="menu" action="controller" method="post">
            <input type="hidden" name="command" value="user_page" /> <input type="submit"
                value="<fmt:message key='menu.main'/>" /><br />
        </form>
        <form name="menu" class="menu" action="controller" method="post">
            <input type="hidden" name="command" value="control_car_page" /> <input type="submit"
                value="<fmt:message key='menu.carcontrol'/>" /><br />
        </form>
        <form name="menu" class="menu" action="controller" method="post">
            <input type="hidden" name="command" value="all_users" /> <input type="submit"
                value="<fmt:message key='menu.usercontrol'/>" /><br />
        </form>
        <form name="menu" class="menu" action="controller" method="post">
            <input type="hidden" name="command" value="crash_page_admin" /> <input type="submit"
                value="<fmt:message key='menu.allcrashbills'/>" /><br />
        </form>
        <form name="menu" class="menu" action="controller" method="post">
            <input type="hidden" name="command" value="order_page" /> <input type="submit"
                value="<fmt:message key='menu.allorders'/>" /><br />
        </form>

        <form name="menu" class="menu" action="controller" method="post">
            <input type="hidden" name="command" value="log_out" /> <input type="submit"
                value="<fmt:message key='menu.logout'/>" /><br />
        </form>

    </div>
</body>

</html>