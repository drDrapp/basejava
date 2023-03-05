package ru.drdrapp.webapp.web;

import ru.drdrapp.webapp.model.*;
import ru.drdrapp.webapp.storage.Storage;
import ru.drdrapp.webapp.util.Config;
import ru.drdrapp.webapp.util.DateUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ResumeServlet extends HttpServlet {
    private static ServletContext context;
    private static Storage sqlStorage;

    @Override
    public void init() {
        context = getServletContext();
        sqlStorage = Config.getInstance().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", sqlStorage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete" -> {
                sqlStorage.delete(uuid);
                response.sendRedirect("resume");
                return;
            }
            case "view" -> r = sqlStorage.get(uuid);
            case "edit" -> r = sqlStorage.get(uuid);
            case "create" -> {
                r = new Resume();
                action = "edit";
            }
            default -> throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/r_view.jsp" : "/WEB-INF/jsp/r_edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        initProcess(request, response);
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        if (fullName.trim().isEmpty()) {
            fullName = "Имя не указано";
        }
        Resume r;
        final boolean needCreate = (uuid == null || uuid.length() == 0);
        if (needCreate) {
            r = new Resume(fullName);
        } else {
            r = sqlStorage.get(uuid);
        }
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value == null) {
                continue;
            }
            if (value.trim().length() == 0) {
                r.getSections().remove(type);
                continue;
            }
            value = ResumeHtml.escapeHTML(value);
            switch (type) {
                case OBJECTIVE, PERSONAL -> r.addSection(type, new TextSection(value.trim()));
                case ACHIEVEMENT, QUALIFICATIONS ->
                        r.addSection(type, new ListSection(Arrays.stream(value.split("\n")).filter((s) -> !s.trim().isEmpty()).toList()));
            }
        }
        fillSection(request, r, SectionType.EXPERIENCE);
        fillSection(request, r, SectionType.EDUCATION);
        if (needCreate) {
            sqlStorage.save(r);
        } else {
            sqlStorage.update(r);
        }
        response.sendRedirect("resume");
    }

    private void fillSection(HttpServletRequest request, Resume r, SectionType sectionType) {
        List<Organization> organizations = parseSection(request, sectionType);
        if (Objects.equals(organizations.toString(), "[]")) {
            r.getSections().remove(sectionType);
        } else {
            r.addSection(sectionType, new OrganizationsSection(parseSection(request, sectionType)));
        }
    }

    private List<Organization> parseSection(HttpServletRequest request, SectionType type) {
        String prefix = type.name();
        String[] arrOrgID = request.getParameterValues(prefix + "_OrgID");
        String[] arrTitle = request.getParameterValues(prefix + "_title");
        String[] arrUrls = request.getParameterValues(prefix + "_url");
        String[] arrLinkOrgID = request.getParameterValues(prefix + "_LinkOrgID");
        String[] arrDateFrom = request.getParameterValues(prefix + "_dateFrom");
        String[] arrDateTo = request.getParameterValues(prefix + "_dateTo");
        String[] arrPosition = request.getParameterValues(prefix + "_position");
        String[] arrDescription = request.getParameterValues(prefix + "_description");
        List<Organization> organizations = new ArrayList<>();
        if (arrOrgID != null) {
            Map<String, List<Integer>> mapLinkOrgID = new HashMap<>();
            if (arrLinkOrgID != null && arrLinkOrgID.length > 0) {
                for (int i = 0; i < arrLinkOrgID.length; i++) {
                    if (!DateUtils.isEmpty(arrLinkOrgID[i])) {
                        if (mapLinkOrgID.get(arrLinkOrgID[i]) == null) {
                            mapLinkOrgID.put(arrLinkOrgID[i], new ArrayList<>(List.of(i)));
                        } else {
                            mapLinkOrgID.get(arrLinkOrgID[i]).add(i);
                        }
                    }
                }
            }
            for (int i = 0; i < arrOrgID.length; i++) {
                String title = ResumeHtml.escapeHTML(arrTitle[i]);
                if (!DateUtils.isEmpty(title)) {
                    List<Organization.Period> periods = new ArrayList<>();
                    if (!mapLinkOrgID.isEmpty()) {
                        var listPeriods = mapLinkOrgID.get(arrOrgID[i]);
                        if (listPeriods != null) {
                            for (var j : listPeriods) {
                                if (!DateUtils.isEmpty(arrPosition[j])) {
                                    periods.add(new Organization.Period(
                                            ResumeHtml.escapeHTML(arrPosition[j]),
                                            ResumeHtml.escapeHTML(arrDescription[j]),
                                            DateUtils.stringToLocalDate(arrDateFrom[j]),
                                            DateUtils.stringToLocalDate(arrDateTo[j])));
                                }
                            }
                        }
                    }
                    organizations.add(new Organization(title, ResumeHtml.escapeHTML(arrUrls[i]), periods));
                }
            }
        }
        return organizations;
    }

    private void initProcess(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
    }

    public static ServletContext getContext() {
        return context;
    }

}