package lk.ijse.dep8.tasks.listener;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class DbInitializer implements ServletContextListener {

    private Logger logger = Logger.getLogger(DbInitializer.class.getName());
    @Resource(name = "java:comp/env/jdbc/pool")
    private DataSource pool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        final String[] tables = {"sub_task", "task", "task_list", "user"};


        try (Connection connection = pool.getConnection()) {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SHOW TABLES");
            List<String> tableList=new ArrayList<>();
            while (rst.next()) {
            tableList.add(rst.getString(1));
            }
            System.out.println(tableList);

            Arrays.sort(tables);
           if(! Arrays.equals(tables,tableList.toArray())){
               InputStream is = this.getClass().getResourceAsStream("/dbScript.sql");
               byte[] bytes=new byte[is.available()];
               is.read(bytes);
               String sqlScript=new String(bytes);
               stm.execute(sqlScript);

           }


        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
