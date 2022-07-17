package DAO.Issue;

import ConnectDB.ConnectJDBC;
import Enitiy.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IssueDAO extends ConnectJDBC {

    public Vector<Issue> listIssueByTeam(int team_id) {
        Vector<Issue> ve = new Vector<>();
        String sql = "select * from issue where team_id = " + team_id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new Issue(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ve;
    }

    public Vector<Milestone> getAllMilestones(int id) {
        String sql = "select * from milestone where class_id = " + id + "";
        Vector<Milestone> veM = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veM.add(new Milestone(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veM;
    }

    public int addIssue(Issue iu) {
        int n = 0;
        String sql = "insert into issue(issue_id, assignee_id, issue_title, description, "
                + "gitlab_url, created_at, due_date,team_id, milestone_id, function_ids, lables, status)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, iu.getIssue_id());
            pre.setInt(2, iu.getAssignee_id());
            pre.setString(3, iu.getIssue_title());
            pre.setString(4, iu.getDescription());
            pre.setString(5, iu.getGitlab_url());
            pre.setString(6, iu.getCreated_at());
            pre.setString(7, iu.getDue_date());
            pre.setInt(8, iu.getTeam_id());
            pre.setInt(9, iu.getMilestone_id());
            pre.setString(10, iu.getFunction_ids());
            pre.setString(11, iu.getLabel());
            pre.setInt(12, iu.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<User> getAllUser() {
        Vector<User> veU = new Vector<>();
        String sql = "select * from user";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veU.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veU;
    }

    public int updateIssue(Issue iu) {
        int n = 0;
        String sql = "UPDATE issue SET assignee_id = ?, issue_title = ?, description = ?, gitlab_url = ?, "
                + "created_at = ?, due_date = ?, milestone_id = ?, function_ids = ?, "
                + "lables = ?, status = ? WHERE issue_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, iu.getAssignee_id());
            pre.setString(2, iu.getIssue_title());
            pre.setString(3, iu.getDescription());
            pre.setString(4, iu.getGitlab_url());
            pre.setString(5, iu.getCreated_at());
            pre.setString(6, iu.getDue_date());
            pre.setInt(7, iu.getMilestone_id());
            pre.setString(8, iu.getFunction_ids());
            pre.setString(9, iu.getLabel());
            pre.setInt(10, iu.getStatus());
            pre.setInt(11, iu.getIssue_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(sql);
            ex.printStackTrace();
        }
        return n;
    }

    public int removeIssue(int id) {
        int n = 0;
        String sql = "delete from issue where issue_id = " + id + "";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Function> getAllFunction(int id) {
        Vector<Function> ve = new Vector<>();
        String sql = "select * from `function` where team_id = " + id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new Function(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ve;
    }
//    public String showNameFuncton(String s){
//        String sql = "select func_name from `function` where function_id = "+s+"";
//        ResultSet rs = getData(sql);
//        try {
//            if (rs.next()) {
//                return rs.getString(1);
//            }
//        }catch (SQLException ex){
//            ex.printStackTrace();
//        }
//        return null;
//    }

    public Vector<Issue> listIssueByUser(int id) {
        Vector<Issue> ve = new Vector<>();
        String sql = "select * from issue where assignee_id = " + id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new Issue(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ve;
    }

    public Vector<Assignee> getAllAssignee(String class_id, String team_id) {
        Vector<Assignee> veA = new Vector<>();
        String sql = "select d.user_id, d.fullname, b.team_id, a.class_id from class a inner join team b "
                + "on a.class_id = b.class_id inner join classuser c on b.team_id = c.team_id "
                + "inner join user d on c.user_id = d.user_id where a.class_id = " + class_id + " and b.team_id = " + team_id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                veA.add(new Assignee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veA;
    }

    public Issue listIssueByID(int id) {
        Issue iu;
        String sql = "select * from issue where issue_id = " + id + "";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                iu = new Issue(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13));
                return iu;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int countIssueUser(int id) {
        String sql = "select COUNT(*) from issue where assignee_id = " + id + "";
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

    public int changeStatus(int status, int id) {
        int n = 0;
        String sql = "update issue set status = " + status + " where issue_id = " + id + "";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

//    public List<Issue> listMilestone() {
//        List<Issue> ve = new ArrayList<>();
//        String sql = "select a.milestone_id, b.milestone_name from issue a inner join milestone b on a.milestone_id = b.milestone_id";
//        System.out.println(sql);
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                ve.add(new Issue(rs.getInt(1), rs.getString(2)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return ve;
//    }
//    public List<Issue> listFunction() {
//        List<Issue> ve = new ArrayList<>();
//        String sql = "select a.function_ids, b.func_name from issue a inner join `function` b on a.function_ids = b.function_id";
//        ResultSet rs = getData(sql);
//        try {
//            while (rs.next()) {
//                ve.add(new Issue(rs.getInt(1), rs.getString(2)));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return ve;
//    }
    public Vector<Issue> searchByStatus(int status, int id) {
        Vector<Issue> ve = new Vector<>();
        String sql = "select * from issue where status = " + status + " and team_id = " + id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new Issue(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ve;
    }

    public List<Issue> listUserBiIssue(int id) {
        List<Issue> lt = new ArrayList<>();
        String sql = "select b.fullname from issue a inner join user b on a.assignee_id = b.user_id"
                + " where a.team_id = " + id + "";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                lt.add(new Issue(rs.getInt(1), rs.getString(2)));
            }
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lt;
    }

    public int changeAssign(int issueID, int assID) {
        int n = 0;
        String sql = "update issue set assignee_id = " + assID + " where issue_id = " + issueID + "";
        try {
            Statement state = getConnection().createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Issue> listIssueUrl(String url) {
        Vector<Issue> ve = new Vector<>();
        String sql = "select * from issue where gitlab_url <> '" + url + "'";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                ve.add(new Issue(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ve;
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

    public Milestone searchMilestone(String title, int classID) {
        Milestone mi = new Milestone();
        String sql = "select * from milestone where milestone_name like ? "
                + "and class_id = ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setString(1, title);
            pre.setInt(2, classID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                mi.setMilestone_id(rs.getInt("milestone_id"));
                mi.setMilestone_name(rs.getString("milestone_name"));
                mi.setInteration_id(rs.getInt("interation_id"));
                mi.setClass_id(rs.getInt("class_id"));
                mi.setFrom_date(rs.getString("from_date"));
                mi.setTo_date(rs.getString("to_date"));
                mi.setStatus(rs.getInt("status"));
                mi.setNote(rs.getString("note"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mi;
    }

    public Function searchFuntion(int team_id, String title) {
        Function f = new Function();
        String sql = "select * from `function` where team_id = ? and "
                + "function_name like ?";
        try {
            PreparedStatement pre = getConnection().prepareStatement(sql);
            pre.setInt(1, team_id);
            pre.setString(2, title);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                f.setFunction_id(rs.getString("function_id"));
                f.setTeam_id(rs.getString("team_id") + "");
                f.setFeatures_id(rs.getString("feature_id") + "");
                f.setOwn_id(rs.getString("owner_id") + "");
                f.setStatus(rs.getString("status") + "");
                f.setComplexity_id(rs.getString("complexity_id"));
                f.setFunction_name(rs.getString("function_name"));
                f.setAccess_roles(rs.getString("access_roles"));
                f.setPriority(rs.getString("priority"));
                f.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public int syncIssue(String project_id, String token, int team_id, int classID) {
        JSONArray jsonIssue;
        JSONArray jsonIssueLinks;
        int n = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Issue is = new Issue();
        jsonIssue = IssueAPI.getData("projects", project_id, token);
        Function f = new Function();
        if (jsonIssue != null) {
            for (int i = 0; i < jsonIssue.length(); i++) {
                try {
                    JSONObject json = jsonIssue.getJSONObject(i);
                    JSONArray labels = (JSONArray) json.get("labels");
                    if (labels.length() != 0) {
                        JSONObject milestone = (JSONObject) json.get("milestone");
                        jsonIssueLinks = IssueAPI.getDataLinks("projects", project_id, token, json.getInt("iid"), "links");
                        JSONObject json2 = jsonIssueLinks.getJSONObject(0);
                        f = searchFuntion(team_id, (String) json2.get("title"));
                        Milestone m = searchMilestone((String) milestone.get("title"), classID);
                        is.setIssue_id(json.getInt("iid"));
                        is.setIssue_title((String) json.get("title"));
//                        is.setAssignee_id(Integer.parseInt(f.getOwn_id()));
                        is.setAssignee_id(Integer.parseInt(f.getOwn_id()));
                        is.setDescription((String) json.get("description"));
                        is.setGitlab_id(json.getInt("project_id"));
                        is.setGitlab_url((String) json.get("web_url"));
                        String date = (String) json.get("created_at");
                        String[] result = date.split("T");
                        is.setCreated_at(result[0]);
                        if (json.isNull("due_date")) {
                            is.setDue_date(now + "");
                        } else {
                            is.setDue_date((String) json.get("due_date"));
                            is.setDue_date(now + "");
                        }
                        is.setTeam_id(team_id);
                        is.setMilestone_id(m.getMilestone_id());
                        is.setFunction_ids(f.getFunction_id());
                        String label1 = "";
                        for (int j = 0; j < labels.length(); j++) {
                            label1 += labels.getString(j) + ",";
                        }
                        is.setLabel(label1.substring(0, label1.length() - 1));

                        if (!checkIssu(json.getInt("iid"))) {
                            n = addIssue(is);
                        } else {
                            n = updateIssue(is);
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

    public boolean checkIssu(int id) {
        String sql = "SELECT * FROM issue where issue_id = ?";
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

    public static void main(String[] args) {
        IssueDAO dao = new IssueDAO();

        
        System.out.println(dao.getAllAssignee("4", "4"));
//        int n = dao.getClassID(4);
//        System.out.println(n);
        System.out.println(dao.searchFuntion(4, "Function 1"));
//        int n = dao.syncIssue("37581416", "glpat-Jco-Wu1xsAaycxsbFzby", 1, 4);
//        if (n != 0) {
//            System.out.println("Het bug roi, di ngu thoi!");
//        } else {
//            System.out.println("sai roi");
//        }
//        System.out.println(java.time.LocalDate.now());
//        System.out.println(dao.getAllAssignee("1", "1"));
//        int n = dao.addIssue(new Issue(1, "abc", "abc", "abc", "2022/2/10", "2022/2/10", 1, 2, "2", "nbn", 2));
//        System.out.println(dao.listIssueUrl("bbbc"));
//        System.out.println(dao.searchFuntion(1, "Chia 2 so"));
//        int n = dao.removeIssue(6);

//        Vector<Issue> ve = dao.listIssueByUser(1);
//        System.out.println(ve);
//        int n = dao.changeStatus(3, 1);
        // int n = dao.updateIssue(new Issue(1, "Test title", "Test Description", "Test url", "2022/2/2", "2022/2/6", 1, 1, "Task", 2));
//        System.out.println(n);
    }
}
