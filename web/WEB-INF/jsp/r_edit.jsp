<%@ page import="com.drdrapp.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean
            id="resume"
            type="com.drdrapp.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <table>
            <tr>
                <th colspan="2">Резюме</th>
            </tr>
            <tr>
                <td>Полное имя:</td>
                <td><label>
                    <input type="text" name="fullName" size=60 value="${resume.fullName}">
                </label></td>
            </tr>
            <tr>
                <th colspan="2">Контакты</th>
            </tr>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <tr>
                    <td>${type.title}</td>
                    <td><label>
                        <input type="text" name="${type.name()}" size=40 value="${resume.getContact(type)}">
                    </label></td>
                </tr>
            </c:forEach>
            <tr>
                <th colspan="2">Секции</th>
            </tr>
            <tr>
                <td colspan="2"><label>
                    <input type="text" name="section" size=115 value="1">
                </label></td>
            </tr>
            <tr>
                <td colspan="2"><label>
                    <input type="text" name="section" size=115 value="1">
                </label></td>
            </tr>
            <tr>
                <td colspan="2"><label>
                    <input type="text" name="section" size=115 value="1">
                </label></td>
            </tr>
            <tr>
                <th>
                    <button type="submit">Сохранить</button>
                </th>
                <th>
                    <button onclick="window.history.back()">Отменить</button>
                </th>
            </tr>
        </table>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>