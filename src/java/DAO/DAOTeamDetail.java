/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tqbao
 */
public class DAOTeamDetail {
    
    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public void editTeam (int team_id, String class_id, String topic_code, String topic_name, String gitlab_url, int status, String team_name) {
        String sql = "update team set class_id = ?, topic_code = ?, topic_name = ?, gitlab_url = ?, status = ?, team_name = ? \n"
                + "where team_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, class_id);
            ps.setString(2, topic_code);
            ps.setString(3, topic_name);
            ps.setString(4, gitlab_url);
            ps.setInt(5, status);
            ps.setString(6, team_name);
            ps.setInt(7, team_id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
