package lk.ijse.dep8.tasks.api;

import lk.ijse.dep8.tasks.util.HttpServlet2;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = {"/users/*"})
public class UserServlet extends HttpServlet2 {
    @Resource(name = "java:comp/env/jdbc/pool")
    private volatile DataSource pool;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if(request.getContentType()==null||request.getContentType().startsWith("multipart/form-data")){
        response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,"Invalid content type or no content type is provided");
        return;
    }

    }
}
