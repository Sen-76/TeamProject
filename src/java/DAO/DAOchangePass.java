/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConnectDB.ConnectJDBC;
import Enitiy.Class_s;
import Enitiy.Setting;
import Enitiy.Subject;
import Enitiy.*;
import Enitiy.UserExcel;
import Enitiy.loc_evaluation;
import com.mysql.cj.protocol.Resultset;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author ASUS
 */
public class DAOchangePass extends ConnectDB.ConnectJDBC {

    Connection conn = ConnectJDBC.getConnection();

    ResultSet rs = null;
    PreparedStatement ps = null;

    private final static String secretKey = "g1swp";

    public String encrypt(String strToEncrypt) { // mah oa . cai dau tien la mat khau truyen vao
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = secretKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt) { // giai ma
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = secretKey.getBytes("UTF-8");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public Vector viewAll() {
        Vector<User> list = new Vector<>();
        String sql = "select * from user limit 20";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int UpdatePass(String pass, int id) {
        int n = 0;
        String sql = "update user set pass = '" + encrypt(pass) + "'"
                + " where user_id = '" + id + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Class_s> ViewAllClassName(String name, String startFrom, String where) {
        Vector<Class_s> list = new Vector<>();

        String sql = "select * from class where class_code like '%" + name + "%'"
                + where + " LIMIT 20 offset " + startFrom;
        //  System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), ClassYear, ClassTerm, Block5Class, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Class_s viewClassById(String name) {
        Vector<Class_s> list = new Vector<>();

        String sql = "select * from class where class_id like '%" + name + "%'  ";
        //   System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, trainerId,
                        SubjectId, ClassYear, ClassTerm, Block5Class, status);

                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int CountClass(String name, String where) {
        String sql = "select count(*) from class"
                + " where class_code like '%" + name + "%' "
                + where;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {

                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String ShowTeacher(String id) {
        String sql = "select distinct a.fullname, a.user_id from user a\n"
                + "inner join class c on a.user_id = c.trainer_id where a.user_id = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String ShowSubject(String id) {
        String sql = "select distinct b.subject_name from class a"
                + " inner join subject b on a.subject_id "
                + "= b.subject_id where b.subject_id =  " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Vector<User> showAllTeacher() {
        Vector<User> v = new Vector<>();
        String sql = "select * from user where role_id = 2 or role_id = 3";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                v.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public String chuannHoa(String st) {
        try {
            st = st.trim();
            st = st.replaceAll("\\s+", " ");
            String[] temp = st.split(" ");
            st = "";
            for (int i = 0; i < temp.length; i++) {
                st += String.valueOf(temp[i].charAt(0)) + temp[i].substring(1);
                if (i < temp.length - 1) {
                    st += " ";
                }
            }
            return st;
        } catch (Exception e) {
        }
        return "";
    }

    public int addClass(Class_s st) {
        int n = 0;
        String sql = "insert into class(class_code, trainer_id, subject_id, "
                + "class_year, class_term, block5_class, status) \n"
                + "values(?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, st.getClassCode());
            ps.setString(2, st.getTrainerId());
            ps.setString(3, st.getSubjectId());
            ps.setString(4, st.getClassYear());
            ps.setString(5, st.getClassTerm());
            ps.setString(6, st.getBlock5Class());
            ps.setInt(7, st.getStatus());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public void updateLectForClass(String s, String cid) {
        String sql1 = "select * from user where roll_number = '" + s + "'";
        ResultSet rs = getData(sql1);
        int id = 0;
        try {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        System.out.println(id);
        String sql_update = "update class set trainer_id = " + id + " where class_id = " + cid;
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql_update);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateAllClass(Class_s c) {
        String sql_update = "update class set class_code = '" + c.getClassCode() + "', "
                + "trainer_id= " + c.getTrainerId() + ", "
                + "subject_id= " + c.getSubjectId() + ", "
                + "class_year = '" + c.getClassYear() + "',\n"
                + " class_term = " + c.getClassTerm() + ", "
                + "block5_class = " + c.getBlock5Class() + ","
                + " status = " + c.getStatus() + " where class_id = " + c.getId();
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql_update);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Vector<Class_s> viewALlClassBySubject(String s) {
        Vector<Class_s> vect = new Vector<>();
        String sql = "select * from class where subject_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), ClassYear, ClassTerm, Block5Class, status);
                vect.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

    public Vector<Class_s> viewAllClassByTrainer(String s) {
        Vector<Class_s> vect = new Vector<>();
        String sql = "select * from class where trainer_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), ClassYear, ClassTerm, Block5Class, status);
                vect.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

    public void checkFormat(String s) {
        String pattern = "^[a-zA-Z]+^[a-zA-Z]+$";

        System.out.println(" amen " + Pattern.matches(pattern, s));
    }

    public String RandomChar(int a) {
        Random random = new Random();
        String rs = "";
        for (int i = 0; i < a; i++) {
            char randomizedCharacter = (char) (random.nextInt(26) + 'a');
            rs += randomizedCharacter;
        }
        return rs;
    }

    public String RandomBullSh() {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int a = rand.nextInt(10);
            result = result + a;
        }
        return result;
    }

    public Vector<Class_s> viewClassByStudent(String s) {
        Vector<Class_s> vect = new Vector<>();
        String sql = "select * from class a inner join classuser b "
                + "on a.class_id = b.class_id where b.user_id = " + s;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String classID = rs.getString(5);
                String picT = rs.getString(3);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), classID, picT, Block5Class, status);
                vect.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

    public boolean checkClassCode(String name) {
        String sql = " select * from class where class_code like '" + name + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkExistUser(String name) {
        String sql = " select * from user where roll_number like '" + name + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public int insertNewUser(UserExcel u) {
        int n = 0;
        String sql = " insert into user(roll_number, "
                + "fullname, email, role_id, "
                + "username,status ,pass)\n"
                + " values('" + u.getRollNum() + "', '" + u.getFullName() + "', "
                + "'" + u.getUsername() + "', 1, '" + u.getUsername() + "', 1, '" + encrypt("abc123") + "')";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        return n;
    }

    public String getteamID(String cid, String top) {
        String sql = " select team_id from team where class_id = " + cid + " and team_name = " + top + "";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    public int insertNewTeam(String classid, String top) {
        int n = 0;
        String sql = "insert into team(class_id, team_name,status)\n"
                + " values(" + classid + ",'" + top + "' ,1);";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    public int insertClassUser(String classid, UserExcel u) {
        int n = 0;
        String tid = getteamID(classid, "" + u.getGroupID());
        String sql = "insert into classuser(class_id, team_id, user_id, team_leader, status) \n"
                + " values(" + classid + "," + tid + ", " + getUserID(u.getRollNum())
                + ", " + isLead(u.getLeader()) + ","
                + "1)";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        return n;
    }

    public String isLead(String s) {
        if (s == null) {
            return "0";
        }
        if (s.equalsIgnoreCase("true")) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getUserID(String s) {
        String sql = "select user_id from user where roll_number like '" + s + "'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public int insertLOC(loc_evaluation loc) {
        int n = 0;
        if (loc.getEvaluation_note() == null) {
            loc.setEvaluation_note("");
        }

        String sql = "insert into loc_evaluation"
                + "(evaluation_time, evaluation_note, "
                + "complexity_id, quality_id, tracking_id)\n"
                + "values('" + loc.getEvaluation_time() + "', '" + loc.getEvaluation_note() + "',"
                + " " + loc.getComplexity_id() + ", " + loc.getQuality_id() + ", "
                + "" + loc.getTracking_id() + ")";

        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }

        return n;
    }

    public int updateLOC(loc_evaluation loc) {
        int n = 0;
        if (loc.getEvaluation_note() == null) {
            loc.setEvaluation_note("");
        }

        String sql = "update loc_evaluation "
                + "set evaluation_time = '" + loc.getEvaluation_time() + "', "
                + "evaluation_note = '" + loc.getEvaluation_note() + "', "
                + "complexity_id = " + loc.getComplexity_id() + ", quality_id = " + loc.getQuality_id() + " \n"
                + "where evaluation_id = " + loc.getEvaluation_id() + "";

        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }

        return n;
    }

    public Vector<Setting> ViewComplexity() {
        Vector<Setting> vect = new Vector<>();
        String sql = "select * from setting where setting_title = 'complexity'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                vect.add(new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

    public Vector<Setting> ViewQuality() {
        Vector<Setting> vect = new Vector<>();
        String sql = "select * from setting where setting_title = 'quality'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                vect.add(new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
    }

    public Vector<ThanhTracking> viewAllLocByStudent(String s) {
        Vector<ThanhTracking> list = new Vector<>();
        String sql;
        if (s.equals("0")) {
            sql = " select * from loc_evaluation";
        } else {
            sql = "select * from tracking where assignee_id = " + s;
        }
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new ThanhTracking(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public loc_evaluation getLOCEva(String id) {
        String sql = " select * from loc_evaluation where tracking_id = " + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                loc_evaluation l = new loc_evaluation(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                return l;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean allTrackingGotMark(String id) {
        Vector<String> v = new Vector<>();
        String sql = "select * from loc_evaluation a inner join tracking b on \n"
                + "a.tracking_id = b.tracking_id where a.tracking_id = " + id;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        return false;
    }

    public Vector<loc_evaluation> getMemEva(String id, String classid) {
        Vector<loc_evaluation> v = new Vector<>();
        String sql = "select * from loc_evaluation a right join tracking b on  \n"
                + "a.tracking_id = b.tracking_id\n"
                + "inner join team c on b.team_id = c.team_id\n"
                + "inner join class d on c.class_id = d.class_id\n"
                + " where b.assignee_id = " + id + " and c.class_id = " + classid;
        ResultSet rs = getData(sql);
        try {
            loc_evaluation loc = new loc_evaluation();
            while (rs.next()) {
                loc = new loc_evaluation(rs.getString(1), ConvertDateFormat(rs.getString(2)),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(8), getMile(rs.getString(9)), getFunc(rs.getString(10)), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15));
                v.add(loc);
            }
            return v;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String ConvertDateFormat(String s) {
        //neu can this hay sua thang nay
        final String temp = "yyyy-MM-dd";
        Date date1 = new Date();
        if (s == null || s.equals("")) {
            return "not yet";
        }
        try {
            date1 = new SimpleDateFormat(temp).parse(s);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(date1);
            return strDate;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return "not yet";
        }
    }

    public Vector<Class_s> allClass() {
        Vector<Class_s> list = new Vector<>();

        String sql = "select * from class ";
        //  System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String ClassCode = rs.getString(2);
                String trainerId = rs.getString(3);
                String SubjectId = rs.getString(4);
                String ClassYear = rs.getString(5);
                String ClassTerm = rs.getString(6);
                String Block5Class = rs.getString(7);
                int status = rs.getInt(8);
                Class_s u = new Class_s(id, ClassCode, ShowTeacher(trainerId), ShowSubject(SubjectId), ClassYear, ClassTerm, Block5Class, status);
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Vector<Team> viewTeamByClass(String id) {
        Vector<Team> list = new Vector<>();
        String sql = "select * from team where class_id = " + id;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean checkTeamExist(String clas, String team) {
        String sql = "select * from "
                + "team where class_id = " + clas + " and team_name = " + team;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        return false;
    }

    public Vector<Tracking> AllTracking() {
        Vector<Tracking> list = new Vector<>();
        String sql = "SELECT * FROM tracking a\n"
                + "left join team b on a.team_id = b.team_id\n"
                + "left join milestone c on a.milestone_id = c.milestone_id\n"
                + "left join `function` d on d.function_id = a.function_id\n"
                + "left join user e on e.user_id = a.assigner_id\n"
                + "left join user f on f.user_id = a.assignee_id\n";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Tracking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(16), rs.getString(23), rs.getString(26), rs.getString(36), rs.getString(50)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getLocID(String id) {
        String sql = "select * from loc_evaluation where tracking_id = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMile(String s) {
        String sql = "select milestone_name from milestone where milestone_id = " + s;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public String getFunc(String s) {
        String sql = "select function_name from `function` where function_id = " + s;
        try {
            ResultSet rs = getData(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Vector<String> allMile(String clas) {
        Vector<String> v = new Stack<>();
        String sql = "select milestone_name from milestone"
                + " where class_id = " + clas;
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                v.add(rs.getString(1));
            }
        } catch (Exception e) {
        }
        return v;
    }

    public Vector<String> allFunct() {
        Vector<String> v = new Stack<>();
        String sql = "select function_name from `function` ";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                v.add(rs.getString(1));
            }
        } catch (Exception e) {
        }
        return v;
    }

    public Vector<User> allTrainer() {
        Vector<User> v = new Stack<>();
        String sql = "select * from user where (role_id = 3 or \n"
                + "role_id = 2) and status = 1";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(3), "", "");
                v.add(u);
            }
        } catch (Exception e) {
        }
        return v;
    }

    public static void main(String[] args) {
        DAOchangePass dao = new DAOchangePass();
//        Vector<loc_evaluation> v = dao.getMemEva("2017", "4");
//        for (loc_evaluation o : v) {
//            System.out.println(o);
//        }
//        Vector<Class_s> v = dao.ViewAllClassName("", "0", "and trainer_id = 2");
//        Vector<Class_s> v0 = new Vector<>();
//        for (Class_s o : v) {
//            System.out.println(o);
//        }
        Vector<User> v = dao.allTrainer();
        System.out.println(v);
        for (User o : v) {
            System.out.println(o);
        }
    }
}
