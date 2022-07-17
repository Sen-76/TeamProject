/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;
import Enitiy.Criteria;
import Enitiy.Setting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DAOCriteria extends ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<Criteria> viewCriteriaList(int index) {
        List<Criteria> list = new ArrayList<>();
        String sql = "select  ar.*,ac.iteration_name, at.subject_code,at.subject_id "
                + "from evaluation_criteria ar join iteration ac join subject at on ar.iteration_id = ac.iteration_id and ac.subject_id = at.subject_id "
                + "Order by criteria_id limit 10 offset ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (index - 1) * 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Criteria(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Criteria> viewSubjectId() {
        List<Criteria> list = new ArrayList<>();
        String sql = "select distinct at.subject_id,at.subject_code "
                + "from evaluation_criteria ar join iteration ac join subject at "
                + "on ar.iteration_id = ac.iteration_id and ac.subject_id=at.subject_id order by subject_name";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Criteria(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int addCriteria(Criteria cr) {
        int n = 0;
        String sql = "Insert into evaluation_criteria(iteration_id, evaluation_title, evaluation_weight, team_evaluation, criteria_order,max_loc,status,description)"
                + "values (?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cr.getIteration_id());
            ps.setString(2, cr.getEvaluation_title());
            ps.setString(3, cr.getEvaluation_weight());
            ps.setBoolean(4, cr.isTeam_evaluation());
            ps.setString(5, cr.getCriteria_order());
            ps.setInt(6, cr.getMax_loc());
            ps.setInt(7, cr.getStatus());
            ps.setString(8, cr.getDescription());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public List<Criteria> searchLoc(String loc) {
        List<Criteria> list = new ArrayList<>();
        String sql = "select  ar.*,ac.iteration_name,at.subject_code,at.subject_id from evaluation_criteria ar join iteration ac join subject at\n"
                + "on ar.iteration_id = ac.iteration_id and ac.subject_id = at.subject_id\n"
                + "where max_loc like '%" + loc + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Criteria(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Criteria> searchSubject(String subject) {
        List<Criteria> list = new ArrayList<>();
        String sql = "select  ar.*,ac.iteration_name,at.subject_code, at.subject_id from evaluation_criteria ar join iteration ac join subject at\n"
                + "on ar.iteration_id = ac.iteration_id and ac.subject_id = at.subject_id\n"
                + "where ac.subject_id = '" + subject + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Criteria(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Criteria getCriteria(int criteria_id) {
        String sql = "select * from evaluation_criteria where criteria_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, criteria_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Criteria(rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getString(9)
                );
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void updateCriteria(int criteria_id, int iteration_id, String evaluation_title, double evaluation_weight, boolean team_evaluation, String criteria_order, int max_loc, int status, String description) {
        String sql = "update evaluation_criteria ar, iteration ac set ar.iteration_id = ? ,ar.evaluation_title =?, ar.evaluation_weight = ? , ar.team_evaluation= ?, ar.criteria_order= ? , ar.max_loc= ? , ar.status= ?, ar.description=?\n"
                + "where ar.iteration_id = ac.iteration_id\n"
                + "And ar.criteria_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, iteration_id);
            ps.setString(2, evaluation_title);
            ps.setDouble(3, evaluation_weight);
            ps.setBoolean(4, team_evaluation);
            ps.setString(5, criteria_order);
            ps.setInt(6, max_loc);
            ps.setInt(7, status);
            ps.setString(8, description);
            ps.setInt(9, criteria_id);

            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Criteria> searchByIterName(String c) {
        List<Criteria> list = new ArrayList<>();
        String sql = "select  ar.*,ac.iteration_name, at.subject_code,at.subject_id\n"
                + " from evaluation_criteria ar join iteration ac join subject at\n"
                + " on ar.iteration_id = ac.iteration_id and ac.subject_id = at.subject_id\n"
                + " where iteration_name like '%" + c + "%'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Criteria(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Criteria> viewIterName() {
        List<Criteria> list = new ArrayList<>();
        String sql = "select distinct s.subject_id, i.iteration_id, i.iteration_name, s.subject_code \n"
                + "from subject s join iteration i on i.subject_id = s.subject_id order by s.subject_id";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Criteria(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

//     public List<Criteria> viewSubjectCode() {
//        List<Criteria> list = new ArrayList<>();
//        String sql = "select * from subject";
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                list.add(new Criteria(rs.getInt(1),rs.getString(2), rs.getString(3)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
    public Criteria getDelete(int criteria_id) {
        String sql = "delete from  evaluation_criteria where criteria_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, criteria_id);
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return null;
    }

    public int updateStatus(String criId, int status) {
        int n = 0;
        String sql = "update evaluation_criteria \n "
                + "set status = ? \n "
                + "where criteria_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setString(2, criId);
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int getTotalList() {
        String sql = "select count(*) from evaluation_criteria";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DAOCriteria dao = new DAOCriteria();
        System.out.print(dao.updateStatus("5", 2));
//      dao.getDelete(2);
//       int n = dao.addCriteria(new Criteria(1,2,true,"1",120,1));
//        if (n > 0) {
//            System.out.println("success");
//        } else {
//            System.out.println("wrong");
//        }
//       List<Criteria> list = dao.searchSubject("PRN");
//       for(Criteria c : list)
//       System.out.println(c);
    }
}
