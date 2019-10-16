<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
    <fmt:setLocale value="${locale}" scope="session"/>
    <fmt:setBundle basename="title"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id="login">
    <form name="menu" class="menu" action="controller" method="post">
        <input type="hidden" name="command" value="user_page"/> <input
            type="submit" value="<fmt:message key=" menu.main" />" /><br/>
    </form>
    <form name="menu" class="menu" action="controller" method="post">
        <input type="hidden" name="command" value="order_page_user"/> <input
            type="submit" value="<fmt:message key=" menu.neworder" />" /><br/>
    </form>
    <form name="menu" class="menu" action="controller" method="post">
        <input type="hidden" name="command" value="user_orders_page"/> <input
            type="submit" value="<fmt:message key=" menu.orders" />" /><br/>
    </form>
    <form name="menu" class="menu" action="controller" method="post">
        <input type="hidden" name="command" value="USER_CRASH_PAGE"/> <input
            type="submit" value="<fmt:message key=" menu.crashbills" />" /><br/>
    </form>

    <form name="menu" class="menu" action="controller" method="post">
        <input type="hidden" name="command" value="log_out"/> <input
            type="submit" value="<fmt:message key=" menu.logout" />" /><br/>
    </form>
</div>
</body>
</html>
