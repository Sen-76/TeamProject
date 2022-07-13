package DAO;

import ConnectDB.ConnectJDBC;
import Enitiy.SubjectSetting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSubjectSettingDetails extends ConnectJDBC {
    
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public void editSubjectSetting(int setting_id, int subject_id, int type_id, String setting_title, String setting_value, String display_order, int status) {
        String sql = "update subject_setting set subject_id = ?, type_id = ?, setting_title = ?, setting_value = ?, display_order=?, status=?\n"
                + "where setting_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subject_id);
            ps.setInt(2, type_id);
            ps.setString(3, setting_title);
            ps.setString(4, setting_value);
            ps.setString(5, display_order);
            ps.setInt(6, status);
            ps.setInt(7, setting_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public SubjectSetting SearchSetID(String s) {
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "select * from subject_setting where setting_id = " + s;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new SubjectSetting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        DAOSubjectSettingDetails dao = new DAOSubjectSettingDetails();
        System.out.println(dao.SearchSetID("2"));
    }
}
