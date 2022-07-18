package DAO;

import ConnectDB.ConnectJDBC;
import Enitiy.Setting;
import Enitiy.SubjectSetting;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import sun.java2d.d3d.D3DSurfaceData;

/**
 *
 * @author tqbao
 */
public class DAOSubjectSetting extends ConnectJDBC{
    
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public String getSubjectCode(String s) {
        String sql = "SELECT distinct b.subject_code FROM subject_setting a inner join subject b on a.subject_id = b.subject_id where a.subject_id = " + s;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return "";
    }
    
    public String getType(String s) {
        String sql = "SELECT distinct b.setting_title FROM subject_setting a inner join setting b on a.type_id = b.type_id where a.type_id = " + s;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
        return "";
    }
    
    public List<Setting> viewType(){
        List<Setting> list = new ArrayList<>();
        String sql = "select distinct * from `swp391-project`.setting where type_id";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                list.add(new Setting(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public int addSubjectSetting(SubjectSetting ss) {
        int n = 0;
        String sql = "insert into subject_setting (subject_id, type_id, setting_title, setting_value, display_order, status) \n"
                + "values(?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ss.getSubject_id());
            ps.setString(2, ss.getType_id());
            ps.setString(3, ss.getSetting_title());
            ps.setString(4, ss.getSetting_value());
            ps.setString(5, ss.getDisplay_order());
            ps.setInt(6, ss.getStatus());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return n;
    }
    
    public List<SubjectSetting> viewTypeId() {
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "SELECt distinct type_id FROM subject_setting order by type_id ";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new SubjectSetting(rs.getString(1)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<SubjectSetting> viewSubjectSettingList() {
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "select * from subject_setting";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new SubjectSetting(rs.getInt(1), getSubjectCode(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<SubjectSetting> viewSubjectSettingType(String subjectSettingType) {
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "SELECt * FROM subject_setting \n"
                + "where setting_title like '%" + subjectSettingType + "%';";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new SubjectSetting(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public SubjectSetting SearchSetID(String s) {
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "select * from subject_setting where setting_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new SubjectSetting(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<SubjectSetting> searchSetting(int typeId, String title, int status) {
        List<SubjectSetting> list = new ArrayList<>();
        String sql = "SELECt * FROM subject_setting \n"
                + "where type_id=" + typeId + " and setting_title like '%" + title + "%' and status= " + status + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new SubjectSetting(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public int updateStatus(int status, int setting_id) {
        int n = 0;
        try {
            String sql = "update subject_setting \n"
                    + " set status = ? where setting_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, setting_id);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    
    public ArrayList<String> viewAllType(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select distinct type_id from subject_setting";
        try {
            ResultSet rs = getData(sql);
            while(rs.next()){
                String type = rs.getString(1);
                list.add(type);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        DAOSubjectSetting dao = new DAOSubjectSetting();
        //System.out.println(dao.viewSubjectSetting(1));
        System.out.println(dao.viewType());
        //dao.addSubjectSetting(new SubjectSetting(1, 1, "fhouedhf", "ndfijwefb", "fnjkdfbj", 1));
//        System.out.println(dao.viewTypeId());
//        System.out.println(dao.viewSubjectSettingList());
//        System.out.println(dao.viewSubjectSettingType("1"));
//        System.out.println(dao.SearchSetID("1"));
//        dao.searchSetting(1, "oo9o3rw", 1);
//        dao.updateStatus(1, 19);
    }
}
