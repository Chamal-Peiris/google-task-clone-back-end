package lk.ijse.dep8.tasks.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String appContextPath = req.getContextPath();

        if (req.getRequestURI().matches(appContextPath + "/v1/users/?") && req.getMethod().equals("POST")) {
            chain.doFilter(req, res);
            return;
        }

        String authorization = req.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Basic")) {
            res.setStatus(403);
        }
        String base64Credentials = authorization.replaceAll("Basic", "");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(base64Credentials);




    }


}
