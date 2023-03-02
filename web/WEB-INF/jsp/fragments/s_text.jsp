<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tr>
    <th colspan="2">
        ${param.sectionTitle}
    </th>
</tr>
<tr>
    <td colspan="2">
        <label>
            <textarea name='${param.sectionName}' cols="75" rows="5">${param.sectionText}</textarea>
        </label>
    </td>
</tr>