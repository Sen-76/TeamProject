/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;
import static ConnectDB.ConnectJDBC.getConnection;
import Enitiy.ClassSetting;
import Enitiy.Function;
import Enitiy.Issue;
import Enitiy.IssueAPI;
import Enitiy.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tqbao
 */
public class DAOLabelSync extends ConnectJDBC{
    
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    private Setting st;
    
    
    public String getProject_id(int team_id) {
        String sql = "SELECT project_id FROM team where team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, team_id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getTeam_tokent(int team_id) {
        String sql = "SELECT token FROM team where team_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, team_id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int getClassID(int teamID) {
        int n = 0;
        String sql = "select distinct class_id from classuser where team_id = " + teamID + "";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                n = rs.getInt(1);
            }
//            PreparedStatement pre = getConnection().prepareStatement(sql);
//            pre.setInt(1, teamID);
//            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public boolean checkSetting(int id) {
        String sql = "SELECT * FROM class_setting where idclass_setting = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public int addSetting(Setting st) {
        int n = 0;
        String sql = "insert into setting(type_id, setting_title, setting_value, display_order,status,note)\n"
                + "values(?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, st.getType_id());
            ps.setString(2, st.getSetting_title());
            ps.setString(3, st.getSetting_value());
            ps.setString(4, st.getDisplay_order());
            ps.setInt(5, st.getStatus());
            ps.setString(6, st.getNote());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    
    public int syncLabel(String project_id, String token, int team_id, int classID) {
        JSONArray jsonLabel;
        JSONArray jsonLabelLinks;
        int n = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ClassSetting set = new ClassSetting();
        jsonLabel = IssueAPI.getData("projects", project_id, token);
        Function f = new Function();
        if (jsonLabel != null) {
            for (int i = 0; i < jsonLabel.length(); i++) {
                try {
                    JSONObject json = jsonLabel.getJSONObject(i);
                    JSONArray labels = (JSONArray) json.get("labels");
                    if (labels.length() != 0) {
                        jsonLabelLinks = IssueAPI.getDataLinks("projects", project_id, token, json.getInt("iid"), "links");
                        JSONObject json2 = jsonLabelLinks.getJSONObject(0);
                        set.setClass_id(json.getInt("sid"));
                        set.setType_id(json.getInt("type_id"));
                        set.setTitle_type((String) json.get("title"));
                        set.setType_value(json.getInt("value"));
                        set.setClass_id(json.getInt("class_id"));
                        set.setColor((String) json.get("color"));
                        set.setDescription((String) json.getString("description"));
                        String date = (String) json.get("created_at");
                        String[] result = date.split("T");
                        set.setCreated_at(result[0]);
                        if (json.isNull("due_date")) {
                            set.setDue_date(now + "");
                        } else {
                            set.setDue_date((String) json.get("due_date"));
                            set.setDue_date(now + "");
                        }
                        String label1 = "";
                        for (int j = 0; j < labels.length(); j++) {
                            label1 += labels.getString(j) + ",";
                        }
                        set.setLabel(label1.substring(0, label1.length() - 1));

                        if (!checkSetting(json.getInt("sid"))) {
                            n = addSetting(st);
                        } else {
                        }

                    }
                } catch (JSONException ex) {
                    f.toString();
                    ex.printStackTrace();
                }
            }
        }
        return n;
    }
    
    public Vector<ClassSetting> listSettingByClass(int class_id) {
        Vector<ClassSetting> ve = new Vector<>();
        String sql = "select * from class_setting where class_id = " + class_id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new ClassSetting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ve;
    }
}
