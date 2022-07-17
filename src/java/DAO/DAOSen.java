package DAO;

import Enitiy.User;
import ConnectDB.ConnectJDBC;
import Enitiy.ClassUser;
import Enitiy.Class_s;
import Enitiy.Function;
import Enitiy.Milestone;
import Enitiy.Setting;
import Enitiy.Subject;
import Enitiy.Team;
import Enitiy.Tracking;
import java.security.MessageDigest;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;

public class DAOSen extends ConnectJDBC {

    public String RandomBullSh() {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            int a = rand.nextInt(10);
            result = result + a;
        }
        return result;
    }

    public static void send(String to, String sub,
            String msg, final String user, final String pass) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public User Login(String username, String password) {
        String sql = "select * from user where username = '" + username + "'  and pass = '" + password + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> AllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> AllAuthor() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user where role_id = 3;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void UpdateClassUserDetail(String class_id, String team_id, String user_id, String team_leader,
            String dropout_date, String user_notes, String ongoing_eval, String final_pres_eval, String final_topic_eval, String status) {
        String sql = "UPDATE classuser "
                + "SET `dropout_date` = '" + dropout_date + "', `user_notes` = '" + user_notes + "', "
                + "`ongoing_eval` = '" + ongoing_eval + "', `final_pres_eval` = '" + final_pres_eval + "', `final_topic_eval` = '" + final_topic_eval + "'"
                + ", `team_id` = '" + team_id + "', `team_leader` = '" + team_leader + "'"
                + " WHERE (`class_id` = '" + class_id + "' and `user_id` = '" + user_id + "');";
        System.out.println(sql);
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<ClassUser> AllClassUser(int userid) {
        List<ClassUser> list = new ArrayList<>();
        String sql = "SELECT * FROM classuser a\n"
                + "join class b on a.class_id = b.class_id\n"
                + "join user c on a.user_id = c.user_id\n"
                + "join subject d on b.subject_id = d.subject_id \n"
                + "join user e on d.author_id = e.user_id\n"
                + "join user f on b.trainer_id = f.user_id\n"
                + "where a.user_id = " + userid + ";";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new ClassUser(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getInt(11), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(23),
                        rs.getString(36), rs.getString(37), rs.getString(43), rs.getString(57), rs.getString(60), rs.getString(16)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ClassUser> AllClassUserIter(String classid) {
        List<ClassUser> list = new ArrayList<>();
        String sql = "select * from class a join subject b on a.subject_id = b.subject_id\n"
                + "join iteration c on b.subject_id = c.subject_id where a.class_id = " + classid + "";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new ClassUser(rs.getString(2), rs.getString(11), rs.getString(12), rs.getString(18), rs.getString(19), rs.getInt(20), rs.getInt(16)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> StudentInClass(String classid) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM classuser a\n"
                + "join class b on a.class_id = b.class_id\n"
                + "join user c on a.user_id = c.user_id\n"
                + "join subject d on b.subject_id = d.subject_id \n"
                + "join user e on d.author_id = e.user_id\n"
                + "join user f on b.trainer_id = f.user_id left join team g on a.team_id = g.team_id\n"
                + "where a.class_id = " + classid + " and c.role_id = 1";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(21), rs.getString(22), rs.getString(23), rs.getInt(24),
                        rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28),
                        rs.getString(29), rs.getInt(30), rs.getInt(31), rs.getString(32), rs.getString(33), rs.getInt(1), rs.getString(75), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> SearchStudentInClass(String classid, String filter) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM classuser a\n"
                + "join class b on a.class_id = b.class_id\n"
                + "join user c on a.user_id = c.user_id\n"
                + "join subject d on b.subject_id = d.subject_id \n"
                + "join user e on d.author_id = e.user_id\n"
                + "join user f on b.trainer_id = f.user_id left join team g on a.team_id = g.team_id\n"
                + "where a.class_id = " + classid + " and c.role_id = 1 " + filter + "";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(21), rs.getString(22), rs.getString(23), rs.getInt(24),
                        rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28),
                        rs.getString(29), rs.getInt(30), rs.getInt(31), rs.getString(32), rs.getString(33), rs.getInt(1), rs.getString(75), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Team> Team(String classid) {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT distinct * FROM team where class_id = " + classid + ";";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), rs.getString(1), rs.getString(1), rs.getString(1), rs.getString(1), rs.getInt(1), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Team> Teama(String teamid) {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT * FROM team a left join classuser b on a.team_id = b.team_id\n"
                + "where a.team_id = " + teamid + ";";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(12), rs.getString(1), rs.getString(1), rs.getString(1), rs.getString(1), rs.getInt(8), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ClassUser OneClass(String classid) {
        String sql = "SELECT * FROM class b \n"
                + "join subject d on b.subject_id = d.subject_id \n"
                + "join user e on d.author_id = e.user_id\n"
                + "join user f on b.trainer_id = f.user_id\n"
                + "where b.class_id = " + classid + "";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new ClassUser(rs.getInt(1), rs.getInt(1), rs.getInt(1), rs.getInt(1), rs.getInt(1),
                        rs.getString(1), rs.getString(1), rs.getString(1), rs.getString(1), rs.getString(1),
                        rs.getInt(1), rs.getString(2), rs.getString(1), rs.getString(1), rs.getString(1),
                        rs.getString(11), rs.getString(12), rs.getString(18), rs.getString(32), rs.getString(1), rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ClassUser OneClassUser(int userid, String classid) {
        String sql = "SELECT * FROM classuser a\n"
                + "join class b on a.class_id = b.class_id\n"
                + "join user c on a.user_id = c.user_id\n"
                + "join subject d on b.subject_id = d.subject_id \n"
                + "join user e on d.author_id = e.user_id\n"
                + "join user f on b.trainer_id = f.user_id\n"
                + "where a.user_id = " + userid + " and a.class_id = " + classid + "";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new ClassUser(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
                        rs.getInt(11), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(23),
                        rs.getString(36), rs.getString(37), rs.getString(43), rs.getString(57), rs.getString(60), rs.getString(16));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int DeleteClassUser(String id) {
        int n = 0;
        String sql = "DELETE FROM classuser WHERE idclassuser = " + id + "";
        System.out.println(sql);
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public List<Subject> AllSubject(int index, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id order by " + order + " limit 12 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> StudentNotInClass(String classid, int index, String filter) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * from user \n"
                + "where user_id not in(\n"
                + "SELECT a.user_id from user a \n"
                + "join classuser b on a.user_id = b.user_id\n"
                + "where b.class_id = " + classid + "\n"
                + ") and role_id = 1 " + filter + " limit 20 offset " + (index - 1) * 20 + "\n";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void UpdateClassUser(int idclassuser) {
        String sql = "UPDATE classuser SET `team_leader` = 0 WHERE (`idclassuser` = '" + idclassuser + "'); ";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int CountHiHi(String classid, String filter) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT count(*) from user \n"
                + "where user_id not in(\n"
                + "SELECT a.user_id from user a \n"
                + "join classuser b on a.user_id = b.user_id\n"
                + "where b.class_id = " + classid + "\n"
                + ") and role_id = 1" + filter + "";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void AddClassUser(String class_id, String user_id) {
        String sql = "INSERT INTO classuser (class_id, user_id) VALUES (" + class_id + ", " + user_id + ");";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Subject> AllSubject2(int index, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id where a.status = 1 order by " + order + " limit 12 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> AllSubjecta() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> AllSubjecta2() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id where a.status = 1;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Subject IdSubject(String subject_id) {
        String sql = "SELECT * FROM subject a left join user b on a.author_id = b.user_id where subject_id = " + subject_id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(8), rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void AddUser(String mail, String user, String pass, String name) {
        String sql = "INSERT INTO user (email, username, pass, fullname) VALUES ('" + mail + "', '" + user + "', '" + pass + "', '" + name + "');";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int DeleteSubject(String id) {
        int n = 0;
        String sql = "delete from iteration where subject_id = " + id + ";\n"
                + "delete from class where subject_id = " + id + ";\n"
                + "delete from subject_setting where subject_id = " + id + ";\n"
                + "delete from subject where subject_id = " + id + ";";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public void AddSubject(String subject_code, String subject_name, String author_id, String status) {
        String sql = "INSERT INTO subject (subject_code, subject_name, author_id, status)"
                + " VALUES ('" + subject_code + "', '" + subject_name + "', '" + author_id + "', '" + status + "');";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int UpdateSubject(String subject_id, String subject_name, String author_id, String status, String subject_code) {
        int n = 0;
        String sql = "UPDATE subject\n"
                + "SET subject_code = '" + subject_code + "', subject_name = '" + subject_name + "', status = " + status + ", author_id = " + author_id + "\n"
                + "WHERE subject_id = " + subject_id + ";";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            n = s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public List<Subject> SearchSubject(String code, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b\n"
                + "on a.author_id = b.user_id where subject_code\n"
                + "like '%" + code + "%' or subject_name like '%" + code + "%'\n"
                + "order by " + order + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> SearchSubjecta(String code, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b\n"
                + "on a.author_id = b.user_id where subject_code\n"
                + "like '%" + code + "%' and a.status = 1 or subject_name like '%" + code + "%' and a.status = 1 \n"
                + "order by " + order + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> SearchSubject2(String code, int index, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b\n"
                + "on a.author_id = b.user_id where subject_code\n"
                + "like '%" + code + "%' or subject_name like '%" + code + "%'\n"
                + "order by " + order + " limit 12 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Subject> SearchSubject3(String code, int index, String order) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subject a left join user b\n"
                + "on a.author_id = b.user_id where subject_code\n"
                + "like '%" + code + "%' and a.status = 1 or subject_name like '%" + code + "%'\n"
                + "and a.status = 1 order by " + order + " limit 12 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Subject(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(8), rs.getInt(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User Login1(String mail, String password) {
        String sql = "select * from user where email = '" + mail + "'  and pass = '" + password + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User EmailLogin(String mail) {
        String sql = "select * from user where email = '" + mail + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User Loged(String user_id) {
        String sql = "select * from user where user_id = '" + user_id + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User Userr(String user_id) {
        String sql = "SELECT * FROM classuser a\n"
                + "join user c on a.user_id = c.user_id\n"
                + "where a.idclassuser = '" + user_id + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getString(13), rs.getString(14));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User EmailExist(String mail) {
        String sql = "select * from user where email = '" + mail + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ChangePassbyEmail(String mail, String repass) {
        String sql = "UPDATE user\n"
                + " SET pass = '" + repass + "'\n"
                + " WHERE email = '" + mail + "';";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

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

    public List<Setting> viewSettingList(int index) {
        List<Setting> list = new ArrayList<>();
        String sql = "select * from setting limit 5 offset " + index + ";";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Setting(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Tracking> AllTracking(int index, String filter, String order) {
        List<Tracking> list = new ArrayList<>();
        String sql = "SELECT * FROM tracking a\n"
                + "left join team b on a.team_id = b.team_id\n"
                + "left join milestone c on a.milestone_id = c.milestone_id\n"
                + "left join `function` d on d.function_id = a.function_id\n"
                + "left join user e on e.user_id = a.assigner_id\n"
                + "left join user f on f.user_id = a.assignee_id\n"
                + "left join class g on g.class_id = b.class_id\n"
                + "where a.status <> 0 " + filter + " " + order + "\n"
                + "limit 10 offset " + (index - 1) * 10 + "";
        System.out.println(sql);
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

    public int CountHaHa(String filter) {
        String sql = "SELECT COUNT(*) as COUNT FROM tracking a\n"
                + "left join team b on a.team_id = b.team_id\n"
                + "left join milestone c on a.milestone_id = c.milestone_id\n"
                + "left join `function` d on d.function_id = a.function_id\n"
                + "left join user e on e.user_id = a.assigner_id\n"
                + "left join user f on f.user_id = a.assignee_id\n"
                + "left join class g on g.class_id = b.class_id\n"
                + "where a.status <> 0 " + filter + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Tracking OneTracking(String tracking_id) {
        String sql = "SELECT * FROM tracking a\n"
                + "left join team b on a.team_id = b.team_id\n"
                + "left join milestone c on a.milestone_id = c.milestone_id\n"
                + "left join `function` d on d.function_id = a.function_id\n"
                + "left join user e on e.user_id = a.assigner_id\n"
                + "left join user f on f.user_id = a.assignee_id\n"
                + "where a.tracking_id = " + tracking_id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return new Tracking(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(16), rs.getString(23), rs.getString(26), rs.getString(36), rs.getString(50));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Team> Team() {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT * FROM team where status = 1;";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String TeamOfLoged(String user_id) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT distinct a.team_id FROM team a\n"
                + "join classuser b on a.team_id = b.team_id\n"
                + "join user c on b.user_id = c.user_id where a.team_id =(\n"
                + "SELECT a.team_id FROM team a\n"
                + "join classuser b on a.team_id = b.team_id\n"
                + "join user c on b.user_id = c.user_id where c.user_id = " + user_id + ")";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Milestone> Milestone() {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT * FROM milestone where status = 1;";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Class_s> Class() {
        List<Class_s> list = new ArrayList<>();
        String sql = "select * from class where status = 1;";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Class_s(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Milestone> Milestone2(String tracking_id) {
        List<Milestone> list = new ArrayList<>();
        String sql = "SELECT * from milestone m left join class n on m.class_id = n.class_id\n"
                + "where n.class_id = (\n"
                + "SELECT a.class_id FROM class a\n"
                + "left join team c on a.class_id = c.class_id where c.team_id = (\n"
                + "SELECT a.team_id FROM tracking a\n"
                + "left join team b on a.team_id = b.team_id\n"
                + "left join milestone c on a.milestone_id = c.milestone_id\n"
                + "left join `function` d on d.function_id = a.function_id\n"
                + "left join user e on e.user_id = a.assigner_id\n"
                + "left join user f on f.user_id = a.assignee_id\n"
                + "where a.tracking_id = " + tracking_id + "\n"
                + "))";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Milestone(rs.getInt(1), rs.getInt(1), rs.getInt(1), rs.getString(7), rs.getString(7), rs.getInt(1), rs.getString(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Function> Function() {
        List<Function> list = new ArrayList<>();
        String sql = "SELECT * FROM `function` where status = 1;";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new Function(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> Student(String tracking_id) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT a.user_id, a.fullname FROM user a left join classuser b on a.user_id = b.user_id\n"
                + "left join team c on b.team_id = c.team_id where c.team_id = (\n"
                + "SELECT a.team_id FROM tracking a\n"
                + "left join team b on a.team_id = b.team_id\n"
                + "left join milestone c on a.milestone_id = c.milestone_id\n"
                + "left join `function` d on d.function_id = a.function_id\n"
                + "left join user e on e.user_id = a.assigner_id\n"
                + "left join user f on f.user_id = a.assignee_id\n"
                + "where a.tracking_id = " + tracking_id + "\n"
                + ") and a.role_id = 1";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(2), rs.getInt(1), rs.getString(2), rs.getString(2), rs.getString(2), rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> Student2(String classid) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user a join classuser b on a.user_id = b.user_id\n"
                + "where a.status = 1 and a.role_id = 1 and b.class_id = " + classid + "";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(3), rs.getString(3), rs.getInt(1), rs.getString(3), rs.getString(3), rs.getString(3), rs.getInt(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<User> NotStudent(String classid) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user where user_id = (\n"
                + "select trainer_id from class where class_id = " + classid + "\n"
                + ")";
        System.out.println(sql);
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void AddTracking(String team_id, String milestone_id, String function_id, String assigner_id, String assignee_id, String tracking_note, String updates, String status) {
        String sql = "INSERT INTO tracking (team_id, milestone_id, function_id, assigner_id, assignee_id, tracking_note, updates, status) \n"
                + "VALUES (" + team_id + ", " + milestone_id + ", " + function_id + ", " + assigner_id + ", " + assignee_id + ", '" + tracking_note + "', '" + updates + "', " + status + ");";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void UpdateTracking(String tracking_id, String team_id, String milestone_id, String function_id, String assigner_id, String assignee_id, String tracking_note, String updates, String status) {
        String sql = "UPDATE tracking SET `status` = '" + status + "', `team_id` = '" + team_id + "', `milestone_id` = '" + milestone_id + "', `function_id` = '" + function_id + "', "
                + "`assigner_id` = '" + assigner_id + "', `assignee_id` = '" + assignee_id + "', `tracking_note` = '" + tracking_note + "', `updates` = '" + updates + "' "
                + "WHERE (`tracking_id` = '" + tracking_id + "');\n";
        System.out.println(sql);
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void UpdateTracking(String tracking_id, String status) {
        String sql = "UPDATE tracking SET `status` = '" + status + "' WHERE (`tracking_id` = '" + tracking_id + "'); ";
        try {
            Connection conn = getConnection();
            Statement s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOSen dao = new DAOSen();
        List<Class_s> lst = dao.Class();
        for (Class_s class_s : lst) {
            System.out.println(class_s);
        }
    }
}
