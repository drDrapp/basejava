<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
    Данные сформированы на:
    <jsp:useBean id="now" class="java.util.Date"/>
    <fmt:formatDate type="time" value="${now}" pattern="dd.MM.yyyy HH:mm:ss"/>
</header>
<br>
    <table>
        <tr>
            <th colspan="2">Управление БД</th>
        </tr>
        <tr>
            <td><a href="resume">Список резюме</a></td>
            <td><a href="resume?action=create">Создать резюме</a>
            </td>
        </tr>
    </table>
<br>