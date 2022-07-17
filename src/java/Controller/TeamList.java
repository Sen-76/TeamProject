package Controller;

import Controller.Setting.SettingListServlet;
import DAO.DAOSen;
import DAO.DAOTeam;
import DAO.DAOchangePass;
import Enitiy.Team;
import Enitiy.User;
import Enitiy.Class_s;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/TeamList"})
public class TeamList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        if (Loged == null) {
            request.getRequestDispatcher("Login_sen").forward(request, response);
            return;
        }
//        try {
//            if (Loged.getRole_id() > 2 ) {
//                request.setAttribute("messE", "Seems like you don't have permission to do this");
//                request.getRequestDispatcher("/jsp/Class/Error.jsp").forward(request, response);
//                return;
//            }
//        } catch (Exception e) {
//        }

        DAOTeam dao = new DAOTeam();
        DAOchangePass daoc = new DAOchangePass();

        String service = request.getParameter("go");
        if (service == null) {
            service = "listAllTeam";
        }

        try (PrintWriter out = response.getWriter()) {
            if (service.equals("addTeam")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Class_s> listClass = dao.viewClass();
                    List<Team> listLeader = dao.viewLeader();

                    request.setAttribute("listClass", listClass);
                    request.setAttribute("listLeader", listLeader);
                    request.getRequestDispatcher("/jsp/Team/addTeam.jsp").forward(request, response);
                } else {
                    String classId = request.getParameter("classId");
                    String classCode = request.getParameter("classCode");
                    String topicCode = request.getParameter("topicCode");
                    String topicName = request.getParameter("topicName");
                    String gitlabURL = request.getParameter("gitlabURL");
                    int status = Integer.parseInt(request.getParameter("status"));
                    String teamName = request.getParameter("teamName");
                    Team obj = new Team(classId, classCode, topicCode, topicName, gitlabURL, status, teamName);
                    int n = dao.addTeam(obj);
                    response.sendRedirect("TeamList");
                }
            }
            if (service.equals("listAllTeam")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int index = 0;
                    List<Team> listClass = dao.viewClassId();
                    String classes = request.getParameter("classes");
                    String teamTopic = request.getParameter("teamTopic");
                    String cid = request.getParameter("cid");
                    List list1 = dao.getClassId(cid);
                    List<Team> list = dao.viewTeamList(cid);
                    List<Team> viewTeamList = dao.viewTeamTopic(teamTopic);
                    String getClassName = dao.getClassName(classes);

                    request.setAttribute("viewTeamList", viewTeamList);
                    request.setAttribute("TeamList", list);
                    request.setAttribute("classID", list1);
                    request.setAttribute("classList", listClass);
                    request.setAttribute("getClassName", getClassName);
                    request.getRequestDispatcher("/jsp/Team/TeamList.jsp").forward(request, response);
                } else {
                    int cid = Integer.parseInt(request.getParameter("cid"));
                    int teamId = Integer.parseInt(request.getParameter("teamId"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    String topicName = request.getParameter("topicName");

                    List<Team> listSearch = dao.searchTeamByTopic(cid, topicName, status);
                    List<Team> listClass = dao.viewClassId();
                    request.setAttribute("TeamList", listSearch);
                    request.setAttribute("classList", listClass);
                    request.getRequestDispatcher("/jsp/Team/TeamList.jsp").forward(request, response);
                }
            }
            if (service.equals("updateStatus")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("TeamList");
                } else {
                    String teamId = request.getParameter("teamId");
                    Team t = new Team();
                    String sql = "Select * from team \n"
                            + "where team_id = " + teamId;
                    ResultSet rs = dao.getData(sql);
                    out.print(dao.getData(sql));
                    int update = -1,class_id=-1;
                    try {
                        if (rs.next()) {
                            if (rs.getInt(6) == 1) {
                                update = dao.updateStatus(2, rs.getInt(1));
                            }
                            if (rs.getInt(6) == 2) {
                                update = dao.updateStatus(1, rs.getInt(1));
                            }
                            class_id=rs.getInt(2);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(SettingListServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("TeamList?go=listAllTeam&cid="+class_id);
                }
            }
            if (service.equals("teamMember")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int index = 0;
                    List<User> listMember = dao.viewMemberOfTeam();
                    String team_id = request.getParameter("team_id");
                    List list = dao.getTeamId(team_id);
                    request.setAttribute("listMember", listMember);
                    request.setAttribute("team_id", list);
                    request.getRequestDispatcher("/jsp/Team/teamMember.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/jsp/Team/teamMember.jsp").forward(request, response);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
