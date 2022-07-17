/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;

import Enitiy.Function1;
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
public class DAOFunction extends ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

    public List<Function1> viewFunctionList(int index, int index2) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "where cl.user_id= ? limit 10 offset ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            ps.setInt(2, (index2 - 1) * 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int getTotalList() {
        String sql = "select count(*) from `function` ";

        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Function1> getOwner() {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct f.owner_id, u.user_id, u.fullname from `function` f inner join team t on f.team_id = t.team_id \n"
                + "inner join classuser cl on cl.team_id = t.team_id inner join user u on cl.user_id = u.user_id = f.owner_id";
        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> getFeature(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = " select distinct f.feature_id, f.feature_name from `function` fu join feature f join team t on  t.team_id = f.team_id join classuser c on t.team_id = c.team_id where c.user_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> viewTeam(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = " select distinct fu.team_id, t.team_name \n"
                + "from `function` fu join feature f join team t on  t.team_id = fu.team_id and fu.feature_id = f.feature_id join classuser c \n"
                + "on t.team_id = c.team_id where c.user_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public List<Function1> viewClass(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = " select distinct al.class_id, al.class_code \n"
                + "from `function` fu join feature f join team t on  t.team_id = fu.team_id and fu.feature_id = f.feature_id join class al on al.class_id = t.class_id join classuser c \n"
                + "on t.team_id = c.team_id where c.user_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> getLevel() {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct f.complexity_id, ss.setting_id,ss.setting_title, ss.setting_value from `function` f  inner join subject_setting ss on ss.setting_id = f.complexity_id order by setting_id";
        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int update(Function1 fu) {
        int n = 0;
        String sql = " update `function` af join feature fe \n"
                + " set af.team_id = ? ,af.function_name =? ,af.feature_id = ?, af.access_roles = ?,af.description =?, af.complexity_id = ?, af.owner_id = ?, af.priority= ?, af.status = ?\n"
                + "where af.feature_id = fe.feature_id and af.function_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fu.getTeam_id());
            ps.setString(2, fu.getFunction_name());
            ps.setInt(3, fu.getFeature_id());
            ps.setString(4, fu.getAccess_roles());
            ps.setString(5, fu.getDescription());
            ps.setInt(6, fu.getComplexity_id());
            ps.setInt(7, fu.getOwner_id());
            ps.setString(8, fu.getPriority());
            ps.setInt(9, fu.getStatus());
            ps.setInt(10, fu.getFunction_id());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Function1 getFunction(int function_id) {
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value  from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "	join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "	where fn.function_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, function_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Function1(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16)
                );
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<Function1> viewTeamClass(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct at.team_id, at.team_name, al.class_code from feature af join team at on at.team_id  = af.team_id\n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id\n"
                + "where cl.user_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int addFunction(Function1 fu) {
        int n = 0;
        String sql = "Insert into `function`(team_id, function_name,feature_id, access_roles,description, complexity_id,owner_id,priority,status)"
                + "values (?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fu.getTeam_id());
            ps.setString(2, fu.getFunction_name());
            ps.setInt(3, fu.getFeature_id());
            ps.setString(4, fu.getAccess_roles());
            ps.setString(5, fu.getDescription());
            ps.setInt(6, fu.getComplexity_id());
            ps.setInt(7, fu.getOwner_id());
            ps.setString(8, fu.getPriority());
            ps.setInt(9, fu.getStatus());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public List<Function1> searchByTeam(int index, String team) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "where cl.user_id= ? and fn.team_id = '" + team + "'";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
     public List<Function1> searchByClass(int index, String class_id) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "where cl.user_id= ? and al.class_id = '" + class_id + "'";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> searchByStatus(int index, String status) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "where cl.user_id= ? and fn.status = '" + status + "'";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Function1> searchByName(int index, String function_name) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "where cl.user_id= ? and fn.function_name like '%" + function_name + "%'";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Function1 getDelete(int function_id) {
        String sql = "delete from `function` where function_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, function_id);
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return null;
    }

    public List<Function1> viewFunctionListTranier(int index, int index3) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "join class al on al.class_id = at.class_id join  user u on u.user_id = al.trainer_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "where al.trainer_id= ? limit 10 offset ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            ps.setInt(2, (index3 - 1) * 10);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> getFeatureTrainer(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct f.feature_id, f.feature_name from `function` fu join feature f on fu.feature_id = f.feature_id join team t on  t.team_id = f.team_id join classuser c on t.team_id = c.team_id and fu.owner_id = c.user_id "
                + "where fu.feature_id =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> getOwnerTrainer(int index, int index1) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct f.owner_id, u.user_id, u.fullname from `function` f inner join team t on f.team_id = t.team_id \n"
                + "inner join classuser cl on cl.team_id = t.team_id inner join user u on  u.user_id = f.owner_id where f.owner_id=? and f.function_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);
            ps.setInt(2, index1);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> searchByTeamTrainer(String team_id, int index) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "                join class al on al.class_id = at.class_id join  user u on u.user_id = al.trainer_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "                where at.team_id = '" + team_id + "' and al.trainer_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> viewTeamTrainer(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct at.team_id , at.team_name from `function` f join team at on f.team_id = at.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id \n"
                + "where al.trainer_id = ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> viewClassTrainer(int index) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct at.class_id , al.class_code from `function` f join team at on f.team_id = at.team_id\n"
                + "join class al on al.class_id = at.class_id join user au on al.trainer_id = au.user_id \n"
                + "where al.trainer_id = ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> searchByClassTrainer(String class_id, int index) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "                join class al on al.class_id = at.class_id join  user u on u.user_id = al.trainer_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "                where at.class_id = '" + class_id + "' and al.trainer_id= ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> searchByNameTrainer(int index, String function_name) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "             join class al on al.class_id = at.class_id join  user u on u.user_id = al.trainer_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "              where al.trainer_id= ? and fn.function_name like '%" + function_name + "%'";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Function1> searchByStatusTrainer(int index, String status) {
        List<Function1> list = new ArrayList<>();
        String sql = "select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id \n"
                + "             join class al on al.class_id = at.class_id join  user u on u.user_id = al.trainer_id  join subject_setting ss on ss.setting_id = fn.complexity_id\n"
                + "              where al.trainer_id= ? and fn.status = '" + status + "'";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, index);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Function1(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkFunction(String name) {
        String sql = "select * from `function` where function_name = '" + name + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//   public int insertNewUser(FunctionExcel f) {
//        int n = 0;
//        String sql = " insert into user(roll_number, "
//                + "fullname, email, role_id, "
//                + "username,status ,pass)\n"
//                + " values('" + u.getRollNum() + "', '" + u.getFullName() + "', "
//                + "'" + u.getUsername() + "', 1, '" + u.getUsername() + "', 1, '" + encrypt("abc123") + "')";
//        try {
//            Connection conn = getConnection();
//            Statement s = conn.createStatement();
//            n = s.executeUpdate(sql);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return n;
//    }
    public static void main(String[] args) {
        DAOFunction dao = new DAOFunction();
        // System.out.println(dao.searchByNameTrainer(2, 1, "funE"));
        System.out.println(dao.getLevel());
        // dao.addFunction(new Function1(2, "funAdd", 34, "admin", "detail", 2, 1, "2", 4));
        // dao.update(new Function1(1,2,"funE",31,"role","de",1,2018,"5",2));
    }

}
//select distinct fn.*, af.feature_name, at.team_name, al.class_code, al.class_id, u.fullname,ss.setting_value from `function` fn join feature af on fn.feature_id = af.feature_id join team at on at.team_id  = fn.team_id 
//join class al on al.class_id = at.class_id join classuser cl on cl.team_id = at.team_id join user u on u.user_id = fn.owner_id  join subject_setting ss on ss.setting_id = fn.complexity_id
//where cl.idclassuser = 48
