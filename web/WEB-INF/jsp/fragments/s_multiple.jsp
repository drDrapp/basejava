<%@ page import="ru.drdrapp.webapp.util.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="resume" type="ru.drdrapp.webapp.model.Resume" scope="request"/>
<tr>
    <th colspan="2">
        ${param.sectionTitle}
    </th>
</tr>
<c:if test="${resume.getSection(param.sectionName) != null}">
    <c:set var="section" value="${resume.getSection(param.sectionName)}"/>
    <jsp:useBean id="section" type="ru.drdrapp.webapp.model.OrganizationsSection"/>
    <%int orgId = 0;%>
    <c:forEach var="organization" items="<%=(section.getOrganizations())%>">
        <tr>
            <td colspan="2">
                <input name="${param.sectionName}_OrgID"
                       value="<%=++orgId%>"
                       id="${param.sectionName}_OrgID<%=orgId%>"
                       type="hidden">
                <table class="simple">
                    <tr>
                        <td colspan="2">
                            <table class="simple">
                                <tr class="simple">
                                    <th class="simple">Название учреждения</th>
                                    <th class="simple">
                                        <label>
                                            <input type="text"
                                                   name="${param.sectionName}_title"
                                                   size="100"
                                                   value="${organization.title.title}">
                                        </label>
                                    </th>
                                </tr>
                                <tr>
                                    <td class="simple">Сайт учреждения</td>
                                    <td class="simple">
                                        <label>
                                            <input type="text"
                                                   name="${param.sectionName}_url"
                                                   size="100"
                                                   value="${organization.title.url}">
                                        </label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="simple">
                            <c:forEach var="period" items="${organization.periods}">
                                <jsp:useBean id="period"
                                             type="ru.drdrapp.webapp.model.Organization.Period"/>
                                <input name="${param.sectionName}_LinkOrgID"
                                       value="<%=orgId%>"
                                       type="hidden">
                                <table class="simple">
                                    <tr class="simple">
                                        <td class="simple">Дата с</td>
                                        <td>
                                            <label>
                                                <input type="text"
                                                       name="${param.sectionName}_dateFrom"
                                                       size="10"
                                                       value="<%=DateUtils.localDateToString(period.getDateFrom())%>"
                                                       placeholder="MM/yyyy">
                                            </label>
                                        </td>
                                    </tr>
                                    <tr class="simple">
                                        <td class="simple">Дата по</td>
                                        <td>
                                            <label>
                                                <input type="text"
                                                       name="${param.sectionName}_dateTo"
                                                       size="10"
                                                       value="<%=DateUtils.localDateToString(period.getDateTo())%>"
                                                       placeholder="MM/yyyy">
                                            </label>
                                        </td>
                                    </tr>
                                    <tr class="simple">
                                        <td class="simple">Должность</td>
                                        <td>
                                            <label>
                                                <input type="text"
                                                       name="${param.sectionName}_position"
                                                       size="100"
                                                       value="<%=period.getPosition()%>">
                                            </label>
                                        </td>
                                    </tr>
                                    <tr class="simple">
                                        <td class="simple">Описание</td>
                                        <td>
                                            <label>
                                                <input type="text"
                                                       name="${param.sectionName}_description"
                                                       size="100"
                                                       value="<%=period.getDescription()%>">
                                            </label>
                                        </td>
                                    </tr>
                                </table>
                                <br>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr sectionLink="${param.sectionName}_OrgID<%=orgId%>" sectionType="${param.sectionName}">
                        <td>
                            <div>
                                <button type="button" onclick="clonePeriod(this)">
                                    Добавить период
                                </button>
                            </div>
                            <br>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </c:forEach>
</c:if>
<tr id="${param.sectionName}organization_template" style="display: none">
    <td colspan="2">
        <input name="${param.sectionName}_OrgID"
               value=""
               id="${param.sectionName}_OrgID"
               type="hidden">
        <table class="simple">
            <tbody>
            <tr>
                <td colspan="2">
                    <table class="simple">
                        <tr class="simple">
                            <th class="simple">Название учреждения</th>
                            <th class="simple">
                                <label>
                                    <input type="text"
                                           name='${param.sectionName}_title'
                                           size="100"
                                           value="">
                                </label>
                            </th>
                        </tr>
                        <tr class="simple">
                            <td class="simple">Сайт учреждения</td>
                            <td class="simple">
                                <label>
                                    <input type="text"
                                           name='${param.sectionName}_url'
                                           size="100"
                                           value="">
                                </label>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr id="${param.sectionName}period_template" style="display: none">
                <td class="simple" colspan="2">
                    <input name="${param.sectionName}_LinkOrgID"
                           value=""
                           id=""
                           type="hidden">
                    <table class="simple">
                        <tr class="simple">
                            <td class="simple">Дата с</td>
                            <td>
                                <label>
                                    <input type="text"
                                           name="${param.sectionName}_dateFrom"
                                           size="10"
                                           value=""
                                           placeholder="MM/yyyy">
                                </label>
                            </td>
                        </tr>
                        <tr class="simple">
                            <td class="simple">Дата по</td>
                            <td>
                                <label>
                                    <input type="text"
                                           name="${param.sectionName}_dateTo"
                                           size="10"
                                           value=""
                                           placeholder="MM/yyyy">
                                </label>
                            </td>
                        </tr>
                        <tr class="simple">
                            <td class="simple">Должность</td>
                            <td>
                                <label>
                                    <input type="text"
                                           name="${param.sectionName}_position"
                                           size="100"
                                           value="">
                                </label>
                            </td>
                        </tr>
                        <tr class="simple">
                            <td class="simple">Описание</td>
                            <td>
                                <label>
                                    <input type="text"
                                           name="${param.sectionName}_description"
                                           size="100"
                                           value="">
                                </label>
                            </td>
                        </tr>
                    </table>
                    <br>
                </td>
            </tr>
            <tr id="trLink" sectionLink="" sectionType="${param.sectionName}" >
                <td colspan="2">
                    <div>
                        <button type="button" onclick="clonePeriod(this)">
                            Добавить период
                        </button>
                    </div>
                    <br>
                </td>
            </tr>
            </tbody>
        </table>
    </td>
</tr>
<tr sectionType="${param.sectionName}">
    <td colspan="2">
        <div>
            <button type="button" onclick="cloneOrganization(this)">
                Добавить учреждение
            </button>
        </div>
    </td>
</tr>