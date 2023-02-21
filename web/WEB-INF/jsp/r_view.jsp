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
    <table>
        <tr>
            <th colspan="2">${resume.fullName}</th>
        </tr>
        <%
            int countContacts = resume.getContacts().size();
            if (countContacts > 0) {
        %>
        <tr>
            <th colspan="2">Контакты</th>
        </tr>
        <%
            }
        %>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <tr>
                <jsp:useBean
                        id="contactEntry"
                        type="java.util.Map.Entry<com.drdrapp.webapp.model.ContactType, java.lang.String>"/>
                <td>
                    <%=contactEntry.getKey().getTitle()%>
                </td>
                <td>
                    <%=contactEntry.getValue()%>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <th><a href="resume?uuid=${resume.uuid}&action=delete">Удалить</a></th>
            <th><a href="resume?uuid=${resume.uuid}&action=edit">Редактировать</a></th>
        </tr>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>