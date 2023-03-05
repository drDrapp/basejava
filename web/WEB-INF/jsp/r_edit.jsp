<%@ page import="ru.drdrapp.webapp.model.ContactType" %>
<%@ page import="ru.drdrapp.webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean
            id="resume"
            type="ru.drdrapp.webapp.model.Resume"
            scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<%request.setCharacterEncoding("utf-8");%>
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
                <td>
                    <label>
                        <input type="text" name="fullName" size="60" value="${resume.fullName}" required>
                    </label>
                </td>
            </tr>
            <tr>
                <th colspan="2">Контакты</th>
            </tr>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <tr>
                    <td>${type.title}</td>
                    <td>
                        <label>
                            <input type="text" name="${type.name()}" size="40" value="${resume.getContact(type)}">
                        </label>
                    </td>
                </tr>
            </c:forEach>

            <jsp:include page="fragments/s_simple.jsp">
                <jsp:param name="sectionName" value="<%=SectionType.OBJECTIVE.name()%>"/>
                <jsp:param name="sectionTitle" value="<%=SectionType.OBJECTIVE.getTitle()%>"/>
                <jsp:param name="sectionText" value="${resume.getSection(SectionType.OBJECTIVE.name())}"/>
            </jsp:include>

            <jsp:include page="fragments/s_simple.jsp">
                <jsp:param name="sectionName" value="<%=SectionType.PERSONAL.name()%>"/>
                <jsp:param name="sectionTitle" value="<%=SectionType.PERSONAL.getTitle()%>"/>
                <jsp:param name="sectionText" value="${resume.getSection(SectionType.PERSONAL.name())}"/>
            </jsp:include>

            <jsp:include page="fragments/s_text.jsp">
                <jsp:param name="sectionName" value="<%=SectionType.ACHIEVEMENT.name()%>"/>
                <jsp:param name="sectionTitle" value="<%=SectionType.ACHIEVEMENT.getTitle()%>"/>
                <jsp:param name="sectionText" value="${resume.getSection(SectionType.ACHIEVEMENT.name())}"/>
            </jsp:include>

            <jsp:include page="fragments/s_text.jsp">
                <jsp:param name="sectionName" value="<%=SectionType.QUALIFICATIONS.name()%>"/>
                <jsp:param name="sectionTitle" value="<%=SectionType.QUALIFICATIONS.getTitle()%>"/>
                <jsp:param name="sectionText" value="${resume.getSection(SectionType.QUALIFICATIONS.name())}"/>
            </jsp:include>

            <jsp:include page="fragments/s_multiple.jsp">
                <jsp:param name="sectionName" value="<%=SectionType.EXPERIENCE.name()%>"/>
                <jsp:param name="sectionTitle" value="<%=SectionType.EXPERIENCE.getTitle()%>"/>
            </jsp:include>

            <jsp:include page="fragments/s_multiple.jsp">
                <jsp:param name="sectionName" value="<%=SectionType.EDUCATION.name()%>"/>
                <jsp:param name="sectionTitle" value="<%=SectionType.EDUCATION.getTitle()%>"/>
            </jsp:include>
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

<script>
    function clonePeriod(butt) {
        let element_place = butt.closest("tr");
        let sectionLink = '#' + element_place.getAttribute('sectionLink');
        let sectionType = element_place.getAttribute('sectionType');
        let textTemplate = '#' + sectionType + 'period_template';
        let element_template = document.querySelector(textTemplate);
        let element_clone = element_template.cloneNode(true);
        let element_OrgID = document.querySelector(sectionLink);
        element_clone.removeAttribute('id');
        element_clone.style.display = '';
        for (let child of element_clone.children) {
            for (let childChild of child.children) {
                if (childChild.nodeName === 'INPUT') {
                    childChild.value = element_OrgID.value;
                }
            }
        }
        element_place.parentNode.insertBefore(element_clone, element_place);
    }

    function cloneOrganization(butt) {
        let element_place = butt.closest("tr");
        let sectionType = element_place.getAttribute('sectionType');
        let textTemplate = '#' + sectionType + 'organization_template';
        let element_template = document.querySelector(textTemplate);
        let element_clone = element_template.cloneNode(true);
        element_clone.style.display = '';

        let OrgID = Date.now();
        let linkOrgID = sectionType + '_OrgID' + OrgID;
        for (let children1 of element_clone.children) {
            for (let children2 of children1.children) {
                if (children2.nodeName === 'INPUT') {
                    children2.value = OrgID;
                    children2.setAttribute('id', linkOrgID);
                    children2.setAttribute('name', sectionType + '_OrgID');
                }
                if (children2.nodeName === 'TABLE') {
                    for (let children3 of children2.children) {
                        for (let children4 of children3.children) {
                            if (children4.getAttribute('id') === 'trLink') {
                                children4.removeAttribute('id');
                                children4.setAttribute('sectionLink', linkOrgID);
                            }
                        }
                    }
                }
            }
        }
        element_place.parentNode.insertBefore(element_clone, element_place);
    }
</script>
</body>
</html>