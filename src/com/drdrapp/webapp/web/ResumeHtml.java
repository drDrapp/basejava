package com.drdrapp.webapp.web;

import com.drdrapp.webapp.model.Resume;

import java.util.List;

public class ResumeHtml {
    public static String MainPage(StringBuilder pageBody) {
        return "<!DOCTYPE html>" +
                "<html lang=\"ru\">" +
                "<head>" +
                "<meta charset=\"utf-8\">" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">" +
                "<title>БД \"Резюме\"</title>" +
                "</head>" +
                "<body>" +
                pageBody +
                "</body>" +
                "</html>";
    }

    public static StringBuilder tableResumes(List<Resume> resumes, String rootUrl) {
        StringBuilder htmlPart = new StringBuilder();
        htmlPart.append("<table class=\"maintable\">")
                .append("<tr>")
                .append("<th>Список Резюме</th>")
                .append("</tr>");
        for (var r : resumes) {
            htmlPart.append("<tr><td>");
            htmlPart.append("<a href=\"")
                    .append(rootUrl)
                    .append("?uuid=")
                    .append(r.getUuid())
                    .append("\">")
                    .append(r.getFullName())
                    .append("</a>");
            htmlPart.append("</td></tr>");
        }
        htmlPart.append("</table>");
        return htmlPart;
    }

    public static StringBuilder tableResume(Resume r, String rootUrl) {
        StringBuilder htmlPart = new StringBuilder();
        htmlPart.append("<table class=\"maintable\">");
        htmlPart.append("<tr>");
        htmlPart.append("<th colspan=\"2\">");
        htmlPart.append(r.getFullName());
        htmlPart.append("</th>");
        htmlPart.append("</tr>");
        for (var contact : r.getContacts().entrySet()) {
            htmlPart.append("<tr>");
            htmlPart.append("<td>")
                    .append(contact.getKey().getTitle())
                    .append("</td>");
            htmlPart.append("<td>")
                    .append(contact.getValue())
                    .append("</td>");
            htmlPart.append("</tr>");
        }
        for (var section : r.getSections().entrySet()) {
            htmlPart.append("<tr>");
            htmlPart.append("<td>")
                    .append(section.getKey().getTitle())
                    .append("</td>");
            htmlPart.append("<td>")
                    .append(section.getValue())
                    .append("</td>");
            htmlPart.append("</tr>");
        }
        htmlPart.append("<tr>");
        htmlPart.append("<th colspan=\"2\" align=\"center\"><a href=\"")
                .append(rootUrl)
                .append("\">Назад</a></th>");
        htmlPart.append("</tr>");
        htmlPart.append("</table>");
        return htmlPart;
    }

    public static String escapeHTML(String s) {
        StringBuilder out = new StringBuilder(Math.max(16, s.length()));
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 127 || c == '"' || c == '\'' || c == '<' || c == '>' || c == '&') {
                out.append("&#");
                out.append((int) c);
                out.append(';');
            } else {
                out.append(c);
            }
        }
        return out.toString();
    }}