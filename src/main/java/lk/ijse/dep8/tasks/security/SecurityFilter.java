package lk.ijse.dep8.tasks.security;

import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter extends HttpFilter {
    @Resource(name = "java:comp/env/jdbc/pool")
    private volatile DataSource pool;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String appContextPath = req.getContextPath();

        if (req.getRequestURI().matches(appContextPath + "/v1/users/?") && req.getMethod().equals("POST")) {
            chain.doFilter(req, res);
            return;
        }

        String authorization = req.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Basic")) {
            res.setStatus(401);
        }
        String base64Credentials = authorization.replaceAll("Basic ", "");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(base64Credentials);
        String userCredentials = new String(decode);
        String[] split = userCredentials.split(":", 2);
        String username = split[0];
        String password = split[1];


        try (Connection connection = pool.getConnection()) {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM  user WHERE email=?");
            stm.setString(1, username);
            ResultSet rst = stm.executeQuery();
            if (!rst.next()) {
                res.setStatus(401);
                return;
            }

            if (!DigestUtils.sha256Hex(password).equals(rst.getString("password"))) {
                res.setStatus(401);
                return;
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


}
