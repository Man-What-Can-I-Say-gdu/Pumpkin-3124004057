package MyServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;


@WebServlet("/StudentManagementSystem")
public class MyServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) {

    }

    @Override
    public String getServletInfo() {
        return "";
    }

    @Override
    public void destroy() {

    }
}
