/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;
import Enitiy.Feature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAOFeature extends ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

//    public List<Feature> viewFeatureList() {
//        List<Feature> list = new ArrayList<>();
//        String sql = "select distinct af.*, at.team_name, al.class_code, al.class_id from feature af join team at join class al \n"
//                + "on at.team_id  = af.team_id and al.class_id = at.class_id order by class_id ";
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
    public List<Feature> viewFeatureList(int index, int index2) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*, at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id where cl.user_id= ?  limit 10 offset ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            ps.setInt(2, (index2 - 1) * 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

//    public List<Feature> viewFeatureList(int index, int index1) {
//        List<Feature> list = new ArrayList<>();
//        String sql = "select distinct af.*, at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
//                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id where cl.user_id= ?  order by class_id limit 10 offset ?";
//
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, index);
//            ps.setInt(2, index1);
//          
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
    public int updateStatus(String feId, int status) {
        int n = 0;
        String sql = "update feature \n "
                + "set status = ? \n "
                + "where feature_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, feId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public List<Feature> searchByFeature(String feature) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*,at.team_name, al.class_code, al.class_id  from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id\n"
                + " where af.feature_name like '%" + feature + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> searchClass(String class_id, int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*,at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id\n"
                + "where al.class_id = '" + class_id + "' and cl.user_id= ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> searchTeam(String team_id, int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*,at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id\n"
                + "where af.team_id = '" + team_id + "' and cl.user_id= ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Feature getFeature(int feature_id) {
        String sql = "select distinct af.*,at.team_name, al.class_code, al.class_id from team at join feature af join class al\n"
                + "on at.team_id = af.team_id and at.class_id = al.class_id\n"
                + "where af.feature_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, feature_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Feature(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<Feature> viewClass(int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct at.class_id , al.class_code from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id where cl.user_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> viewTeam(int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.team_id , at.team_name from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id where cl.user_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> viewTeamClass(int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct at.team_id, at.team_name, al.class_code from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id\n"
                + "where cl.user_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void updateFeature(int feature_id, int team_id, String feature_name, int status, String description) {
        String sql = "update feature af join team at join class al set af.team_id =?, af.feature_name =?, af.status = ?, af.description =? "
                + "where  at.team_id  = af.team_id and al.class_id = at.class_id and af.feature_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, team_id);
            ps.setString(2, feature_name);

            ps.setInt(3, status);
            ps.setString(4, description);

            ps.setInt(5, feature_id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int addFeature(Feature fe) {
        int n = 0;
        String sql = "Insert into feature(team_id, feature_name, status, description)"
                + "values (?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, fe.getTeam_id());
            ps.setString(2, fe.getFeature_name());
            ps.setInt(3, fe.getStatus());
            ps.setString(4, fe.getDescription());

            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int getTotalList(int index) {
        String sql = "select count(*) from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id where cl.user_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getTotalListTrainer() {
        String sql = "select count(*) from feature f inner join team t on f.team_id = t.team_id inner join class c on c.class_id = t.class_id  ";
        ResultSet rs = getData(sql);
        try {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

//    public List<Feature> viewFeatureListTranier(int index, int index1) {
//        List<Feature> list = new ArrayList<>();
//        String sql = "select distinct af.*, at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n" +
//"join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id where au.user_id = ? order by class_id limit 10 offset ?";
//
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, index);
//          ps.setInt(2, (index1 - 1) * 10);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
    public List<Feature> viewFeatureListTranier(int index, int index3) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*, at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id where au.user_id = ?  order by class_id limit 10 offset ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            ps.setInt(2, (index3 - 1) * 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> viewClassTrainer(int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct at.class_id , al.class_code from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id where au.user_id = ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> viewTeamTrainer(int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct f.team_id , at.team_name from feature f join team at on f.team_id = at.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id where au.user_id = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> searchClassTrainer(String class_id, int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*,at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id\n"
                + "where al.class_id = '" + class_id + "' and au.user_id= ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Feature> searchTeamTrainer(String team_id, int index) {
        List<Feature> list = new ArrayList<>();
        String sql = "select distinct af.*,at.team_name, al.class_code, al.class_id from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id\n"
                + "where af.team_id = '" + team_id + "' and au.user_id= ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Feature(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DAOFeature dao = new DAOFeature();
//       dao.updateFeature(1, "de", 1, "lala");
        //dao.updateFeature(20, "gt", 1, "ex", "g5", "SE1323");
        System.out.println(dao.updateStatus("2", 2));
    }

}
