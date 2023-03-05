<%@ page import="ru.drdrapp.webapp.model.TextSection" %>
<%@ page import="ru.drdrapp.webapp.model.ListSection" %>
<%@ page import="ru.drdrapp.webapp.model.OrganizationsSection" %>
<%@ page import="ru.drdrapp.webapp.util.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean
            id="resume"
            type="ru.drdrapp.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table>
        <tr>
            <th colspan="2">${resume.fullName}</th>
        </tr>
        <c:set var="countContacts" value="${resume.contacts.size()}"/>
        <c:if test="${countContacts > 0}">
            <tr>
                <th colspan="2">Контакты</th>
            </tr>
            <c:forEach var="contactEntry" items="${resume.contacts}">
                <tr>
                    <jsp:useBean
                            id="contactEntry"
                            type="java.util.Map.Entry<ru.drdrapp.webapp.model.ContactType, java.lang.String>"/>
                    <td>
                        <%=contactEntry.getKey().getTitle()%>
                    </td>
                    <td>
                        <%=contactEntry.getValue()%>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean
                    id="sectionEntry"
                    type="java.util.Map.Entry<ru.drdrapp.webapp.model.SectionType, ru.drdrapp.webapp.model.AbstractSection>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean
                    id="section"
                    type="ru.drdrapp.webapp.model.AbstractSection"/>
            <c:if test="${resume.getSection(type) != null}">
                <c:if test="${section != null && section != \"\"}">
                    <tr>
                        <th colspan="2">${type.title}</th>
                    </tr>
                    <c:choose>
                        <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                            <tr>
                                <td colspan="2">
                                    <%=((TextSection) section).getText()%>
                                </td>
                            </tr>
                        </c:when>
                        <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATIONS'}">
                            <tr>
                                <td colspan="2">
                                    <ul style="list-style-type:square">
                                        <c:forEach var="item" items="<%=((ListSection) section).getItems()%>">
                                            <li>${item}</li>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </c:when>
                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <tr>
                                <td colspan="2">
                                    <c:forEach var="organization"
                                               items="<%=((OrganizationsSection) section).getOrganizations()%>">
                                        <c:set var="organizationLink" value="${organization.title.url}"/>
                                        <c:choose>
                                            <c:when test="${empty organizationLink}">
                                                <h4>${organization.title.title}</h4>
                                            </c:when>
                                            <c:otherwise>
                                                <h4><a href="${organizationLink}">${organization.title.title}</a>
                                                </h4>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="period" items="${organization.periods}">
                                            <jsp:useBean
                                                    id="period"
                                                    type="ru.drdrapp.webapp.model.Organization.Period"/>
                                            <table class="simple">
                                                <tr class="simple">
                                                    <td class="simple">
                                                        <%=DateUtils.periodToString(period.getDateFrom(), period.getDateTo())%>
                                                    </td>
                                                    <td class="simple">
                                                        <b>${period.position}</b><br>${period.description}
                                                    </td>
                                                </tr>
                                            </table>
                                        </c:forEach>
                                    </c:forEach>
                                    <br>
                                </td>
                            </tr>
                        </c:when>
                    </c:choose>
                </c:if>
            </c:if>
        </c:forEach>
        <tr>
            <th><a href="resume?uuid=${resume.uuid}&action=edit">Редактировать</a></th>
            <th><a href="resume?uuid=${resume.uuid}&action=delete">Удалить</a></th>
        </tr>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>