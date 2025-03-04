package com.amreshpro;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/hello", loadOnStartup = 1) // Now it will load on server start
public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Servlet Initialized");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("In Service Method");
        PrintWriter out = res.getWriter();
        out.println("<h1>Hello World</h1>");
    }
}
