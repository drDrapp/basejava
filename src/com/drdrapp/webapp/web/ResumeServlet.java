package com.drdrapp.webapp.web;

import com.drdrapp.webapp.Config;
import com.drdrapp.webapp.storage.Storage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private static ServletContext context;

    @Override
    public void init() throws ServletException {
        context = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        Storage sqlStorage = Config.getInstance().getStorage();
        if (uuid == null) {
            response.getWriter().write(
                    ResumeHtml.MainPage(
                            ResumeHtml.tableResumes(sqlStorage.getAllSorted(), request.getRequestURL().toString())));
        } else {
            response.getWriter().write(
                    ResumeHtml.MainPage(
                            ResumeHtml.tableResume(sqlStorage.get(uuid), request.getRequestURL().toString())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static ServletContext getContext() {
        return context;
    }

}