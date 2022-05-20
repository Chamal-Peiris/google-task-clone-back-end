package lk.ijse.dep8.tasks.api;

import com.mysql.cj.log.Log;
import lk.ijse.dep8.tasks.util.HttpServlet2;
import lk.ijse.dep8.tasks.util.ResponseStatusException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "TaskListServlet")
public class TaskListServlet extends HttpServlet2 {
private final Logger logger= Logger.getLogger(TaskListServlet.class.getName());

    private AtomicReference<DataSource> pool;

    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java/comp/env/jdbc/pool");
            pool = new AtomicReference<>(ds);
        } catch (NamingException e) {
            logger.log(Level.SEVERE,"Failed to locate the JNDI pool",e);
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getContentType() == null || !request.getContentType().startsWith("application/json")) {
            throw new ResponseStatusException(415, "Invalid content type or content type is empty");
        }

        String pattern = "/([A-Fa-f0-9\\-]{36})/lists/?";
        if (!request.getPathInfo().matches(pattern)) {
            throw new ResponseStatusException(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Invalid end point for post request");
        }
        Matcher matcher = Pattern.compile(pattern).matcher(request.getPathInfo());
        matcher.find();
        String userId = matcher.group(1);

        try(Connection connection = pool.get().getConnection()){
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            stm.setString(1,userId);
            ResultSet rst = stm.executeQuery();
            if(!rst.next()){
                throw new  ResponseStatusException(404,"Invalid user id");
            }else{

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
