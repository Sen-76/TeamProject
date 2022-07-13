
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectJDBC {


    private static final String DB_URL = "jdbc:mysql://localhost:3306/swp391-project?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";


    Connection conn = getConnection();

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }
    
    public static void main(String[] args) {
        getConnection();
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
