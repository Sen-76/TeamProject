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
import Enitiy.User;
import Enitiy.UserExcel;
import Enitiy.loc_evaluation;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.Vector;
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
        String sql = "update user set pass = '" + encrypt(pass) + "' where user_id = '" + id + "'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Class_s> ViewAllClassName(String name, String startFrom) {
        Vector<Class_s> list = new Vector<>();

        String sql = "select * from class where class_code like '%" + name + "%' LIMIT 20 offset " + startFrom;
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
    
    public Class_s viewClassByCode(String name) {
        Vector<Class_s> list = new Vector<>();

        String sql = "select * from class where class_code like '%" + name + "%'  ";
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

    public int CountClass(String name) {
        String sql = "select count(*) from class where class_code like '%" + name + "%'";
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
        String sql_update = "update class set class_code = '"+c.getClassCode()+"', "
                + "trainer_id= "+c.getTrainerId()+", "
                + "subject_id= "+c.getSubjectId()+", "
                + "class_year = '"+c.getClassYear()+"',\n"
                + " class_term = "+c.getClassTerm()+", "
                + "block5_class = "+c.getBlock5Class()+","
                + " status = "+c.getStatus()+" where class_id = " + c.getId();
        try {
            Statement state = conn.createStatement();
            state.executeUpdate(sql_update);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Vector<Class_s> viewALlClassBySubject(String s){
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
    
    public Vector<Class_s> viewAllClassByTrainer(String s){
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
    
    public  String RandomChar(int a) {
        Random random = new Random();
        String rs = "";
        for (int i = 0; i < a; i++) {
            char randomizedCharacter = (char) (random.nextInt(26) + 'a');
            rs += randomizedCharacter;
        }
        return  rs;
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
    
    public Vector<Class_s> viewClassByStudent(String s){
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
    
    public boolean checkClassCode(String name){
        String sql = " select * from class where class_code like '"+name+"'";
        ResultSet rs = getData(sql);
        try {
            if(rs.next())
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean checkExistUser(String name){
        String sql = " select * from user where roll_number like '"+name+"'";
        ResultSet rs = getData(sql);
        try {
            if(rs.next())
                return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public int insertNewUser(UserExcel u){
        int n = 0;
        String sql = " insert into user(roll_number, "
                + "fullname, email, role_id, "
                + "username,status ,pass)\n" +
" values('"+u.getRollNum()+"', '"+u.getFullName()+"', "
                + "'"+u.getUsername()+"', 1, '"+u.getUsername()+"', 1, '"+encrypt("abc123")+"')";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public String getteamID(String cid, String top){
        String sql= " select team_id from team where class_id = "+cid+" and topic_code = '"+top+"'";
        ResultSet rs = getData(sql);
        try {
            if(rs.next())
                return rs.getString(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
    
    public int insertNewTeam(String classid, String top){
        int n = 0;
        String sql = "insert into team(class_id, topic_code,status)\n" +
" values("+classid+",'Topic: "+top+"' ,1);";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int insertClassUser(String classid, UserExcel u){
        int n = 0;
        String tid = getteamID(classid, "Topic: " + u.getGroupID());
        String sql = "insert into classuser(class_id, team_id, user_id, team_leader, status) \n" +
" values("+classid+","+tid+", "+getUserID(u.getRollNum())+
                ", "+ isLead(u.getLeader())+","
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
    
    public String isLead(String s){
        if(s == null)
            return "0";
        if(s.equalsIgnoreCase("true"))
            return "1";
        else
            return "0";
    }
    
    public String getUserID(String s){
        String sql = "select user_id from user where roll_number like '"+s+"'";
        ResultSet rs = getData(sql);
        try {
            if(rs.next())
                return rs.getString(1);
        } catch (Exception e) {
        }
        return "";
    }
    
    public int insertLOC(loc_evaluation loc){
        int n = 0;
        if(loc.getEvaluation_note() == null){
            loc.setEvaluation_note("");
        }
            
        String sql = "insert into loc_evaluation"
                + "(evaluation_time, evaluation_note, "
                + "complexity_id, quality_id, tracking_id)\n" +
"values('"+loc.getEvaluation_time()+"', '"+loc.getEvaluation_note()+"',"
                + " "+loc.getComplexity_id()+", "+loc.getQuality_id()+", "
                + ""+loc.getTracking_id()+")";
        
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

    public Vector<loc_evaluation> viewAllLoc(String s) {
        Vector<loc_evaluation> vect = new Vector<>();
        String sql;
        if (s.equals("0")) {
            sql = " select * from loc_evaluation";
        } else {
            sql = " select * from loc_evaluation where tracking_id = " + s;
        }
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                loc_evaluation l = new loc_evaluation(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                vect.add(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vect;
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
        String sql = "select * from loc_evaluation a right join tracking b on  \n" +
"a.tracking_id = b.tracking_id\n" +
"inner join team c on b.team_id = c.team_id\n" +
"inner join class d on c.class_id = d.class_id\n" +
" where b.assignee_id = "+id+" and c.class_id = " + classid;
        ResultSet rs = getData(sql);
        try {
            loc_evaluation loc = new loc_evaluation();
            while (rs.next()) {
                loc = new loc_evaluation(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                        rs.getString(13), rs.getString(14), rs.getString(15));
                v.add(loc);
            }
            return v;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DAOchangePass dao = new DAOchangePass();
        Vector<Setting> v = dao.ViewComplexity();
        for (Setting o : v) {
            System.out.println(o);
        }

    }

}
