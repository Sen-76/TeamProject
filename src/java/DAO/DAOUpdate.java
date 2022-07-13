package DAO;

import Enitiy.User;
import ConnectDB.ConnectJDBC;
import static ConnectDB.ConnectJDBC.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUpdate extends ConnectJDBC {

    public int Update(String user_id, String fullname, String gender, String date_of_birth, String email, String mobile, String facebook_link) {
        int n = 0;
        String sql = "update user set fullname = '" + fullname + "', gender = '" + gender + "', date_of__birth = '" + date_of_birth
                + "', email = '" + email + "', mobile = '" + mobile + "', facebook_link = '" + facebook_link + "' where user_id = '" + user_id + "';";
        try {
            System.out.println(sql);
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public void UpdateAvatar(String user_id, String avatar_link) {
        String sql = "update user set avatar_link = '" + avatar_link + "'where user_id = '" + user_id + "';";
        try {
            System.out.println(sql);
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOUpdate dao = new DAOUpdate();
//        dao.Update("1", "fbt", "2", "2020-11-11", "hgura", "1687465",
//                "dnfndw");
        dao.UpdateAvatar("1", "fbt");
    }
}
