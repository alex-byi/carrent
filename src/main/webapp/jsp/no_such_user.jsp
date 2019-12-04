<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:fmt="http://www.w3.org/1999/XSL/Transform" xmlns:c="http://www.w3.org/2001/XMLSchema">

<head>
    <fmt:setLocale value="${locale}" scope="session" />
    <fmt:setBundle basename="title" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <fmt:message key="header.title" />
    </title>
    <link rel="stylesheet" href="../css/style.css" type="text/css">
</head>

<body>

    <div class="page-wrapper">
        <div class="header">
            <c:import url="common/header.jsp" charEncoding="utf-8" />
        </div>
        <div class="page-buffer">
            <center>
                <div id="login">

                    <h1>
                        <fmt:message key="registration.nosuchuser" />
                    </h1>

                    <form name='form-login' action="/my-web-project/index.jsp">
                        <h1>
                            <fmt:message key="registration.wrongname" />
                        </h1>
                        <br /> <input type="submit" value="<fmt:message key='registration.tryagain'/>" />

                    </form>
                    <form name='form-login' action="/my-web-project/jsp/registration_user.jsp">

                        <input type="submit" value="<fmt:message key='registration.reg'/>" />
                    </form>
                </div>
            </center>
        </div>
    </div>
    <div class="footer">
        <c:import url="common/footer.jsp" charEncoding="utf-8" />
    </div>
</body>

</html>