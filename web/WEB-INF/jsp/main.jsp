<%@ page import="com.drdrapp.webapp.model.ContactType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Title</title>
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
                    type="com.drdrapp.webapp.model.Resume"/>
            <tr>
                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                <td><%=resume.getContact(ContactType.EMAIL)%>
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