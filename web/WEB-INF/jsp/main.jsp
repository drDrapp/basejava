<%@ page import="ru.drdrapp.webapp.model.ContactType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>БД "Резюме"</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table>
        <tr>
            <th>Имя</th>
            <th>Email</th>
            <th colspan="2">Действие</th>
        </tr>
        <jsp:useBean
                id="resumes"
                scope="request"
                type="java.util.List"/>
        <c:forEach items="${resumes}" var="resume">
            <jsp:useBean
                    id="resume"
                    type="ru.drdrapp.webapp.model.Resume"/>
            <tr class="flash">
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td><%=ContactType.EMAIL.toHtml(resume.getContact(ContactType.EMAIL))%>
                </td>
                <td><a href="resume?uuid=${resume.uuid}&action=delete">Удалить</a></td>
                <td><a href="resume?uuid=${resume.uuid}&action=edit">Редактировать</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>