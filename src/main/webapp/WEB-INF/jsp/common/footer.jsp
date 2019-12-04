<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Footer</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
<center>
    <h2> Version:
        <c:import url="http://localhost:8090/job/carrent/lastBuild/buildNumber"/>
        timestamp:
        <c:import url="http://localhost:8090/job/carrent/lastBuild/buildTimestamp"/>
    </h2>
</center>
<div>

    <a href="mailto:alex-byi@mail.ru">Написать нам</a>
</div>
</body>
</html>
