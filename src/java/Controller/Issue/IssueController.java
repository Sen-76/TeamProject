package Controller.Issue;

import DAO.Issue.IssueDAO;
import Enitiy.*;
import Enitiy.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "IssueController", urlPatterns = {"/IssueController"})
public class IssueController extends HttpServlet {

    IssueDAO dao = new IssueDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("go");
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
//        if (Loged == null) {
//            request.getRequestDispatcher("ControllerHome").forward(request, response);
//        }
        if (service == null) {
            service = "listByTeam";
        }
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("listIssueByUser")) {
                int u_id = Loged.getUser_id();

                Vector<Issue> ve = dao.listIssueByUser(u_id);
                request.setAttribute("listByUser", ve);
                request.getRequestDispatcher("/jsp/issue/ListIssueByUser.jsp").forward(request, response);
            }
            if (service.equals("changeStatus")) {
                int issueID = Integer.parseInt(request.getParameter("issueid"));
                int status = Integer.parseInt(request.getParameter("status"));
                int u_id = Loged.getUser_id();
                int n = dao.changeStatus(status, issueID);
                Vector<Issue> ve = dao.listIssueByUser(u_id);
                request.setAttribute("listByUser", ve);
                request.getRequestDispatcher("/jsp/issue/ListIssueByUser.jsp").forward(request, response);
            }
            if (service.equals("changeStatusTeam")) {
                int id = Integer.parseInt(request.getParameter("team_id"));
                int issueID = Integer.parseInt(request.getParameter("issueid"));
                int status = Integer.parseInt(request.getParameter("status"));
                
                int n = dao.changeStatus(status, issueID);
                Vector<Issue> ve = dao.listIssueByTeam(id);
                Vector<Assignee> veA = dao.getAllAssignee("1", id + "");
                request.setAttribute("listAss", veA);
//                List<String> lt = dao.listUserBiIssue(1);
                request.setAttribute("listTeam", ve);
//                request.setAttribute("lstUser", lt);
                request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
            }
            if (service.equals("updateIssue")) {
                LocalDate da = java.time.LocalDate.now();
                String submit = request.getParameter("update");
                Vector<Function> lstFUC = dao.getAllFunction(1);
                Vector<Milestone> lstMil = dao.getAllMilestones(1);
                int iuID = Integer.parseInt(request.getParameter("issueID"));
                String function_id = request.getParameter("function");

                if (submit == null) {
                    Issue v = dao.listIssueByID(iuID);
                    Vector<Assignee> veA = dao.getAllAssignee("1", "1");
                    request.setAttribute("listAss", veA);
                    request.setAttribute("date", da);
                    request.setAttribute("issueid", iuID);
                    request.setAttribute("lstFuntion", lstFUC);
                    request.setAttribute("lstMile", lstMil);
                    request.setAttribute("idF", function_id);
                    request.setAttribute("listUpdate", v);

                    request.getRequestDispatcher("/jsp/issue/UpdateIssue.jsp").forward(request, response);
                } else {
                    String title = request.getParameter("title");
                    String desc = request.getParameter("descrip");
                    String giturl = request.getParameter("urlgit");
                    String creatt = request.getParameter("createAT");
                    String due = request.getParameter("dueDA");
                    int assID = Integer.parseInt(request.getParameter("assID"));
                    int mile = Integer.parseInt(request.getParameter("milestone"));
                    String funct = request.getParameter("function");
                    String label = request.getParameter("label");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Vector<Issue> lstTe = dao.listIssueUrl(giturl);
                    Vector<Assignee> veA = dao.getAllAssignee("1", "1");
                    request.setAttribute("listAss", veA);
                    Issue iu2 = new Issue(iuID, assID, title, desc, giturl, creatt, due, mile, funct, label, status);

                    if (title.equals("") || giturl.equals("") || creatt.equals("")
                            || due.equals("") || label.equals("") || title == null || giturl == null || creatt == null
                            || due == null || label == null) {
                        request.setAttribute("listUpdate", iu2);
                        request.setAttribute("date", da);
                        request.setAttribute("issueid", iuID);
                        request.setAttribute("lstFuntion", lstFUC);
                        request.setAttribute("lstMile", lstMil);
                        request.setAttribute("idF", function_id);
                        request.setAttribute("err", "Data is not allowed null!");
                        request.getRequestDispatcher("/jsp/issue/UpdateIssue.jsp").forward(request, response);
                    }
                    if (!giturl.startsWith("https://gitlab.com/")) {
                        request.setAttribute("err", "Gitlab URL is invalid");
                        request.setAttribute("listUpdate", iu2);
                        request.setAttribute("date", da);
                        request.setAttribute("issueid", iuID);
                        request.setAttribute("lstFuntion", lstFUC);
                        request.setAttribute("lstMile", lstMil);
                        request.setAttribute("idF", function_id);
                        request.getRequestDispatcher("/jsp/issue/UpdateIssue.jsp").forward(request, response);
                    }
                    for (Issue iu : lstTe) {
                        if (giturl.equals(iu.getGitlab_url())) {
                            request.setAttribute("err", "Gitlab URL is already exists. Enter the other link!");
                            request.setAttribute("listUpdate", iu2);
                            request.setAttribute("date", da);
                            request.setAttribute("issueid", iuID);
                            request.setAttribute("lstFuntion", lstFUC);
                            request.setAttribute("lstMile", lstMil);
                            request.setAttribute("idF", function_id);
                            request.getRequestDispatcher("/jsp/issue/UpdateIssue.jsp").forward(request, response);
                        }
                    }
                    Issue iu1 = new Issue(iuID, assID, title, desc, giturl, creatt, due, mile, funct, label, status);
                    int n = dao.updateIssue(iu1);
                    Vector<Issue> ve = dao.listIssueByUser(1);
                    request.setAttribute("listByUser", ve);
                    request.getRequestDispatcher("/jsp/issue/ListIssueByUser.jsp").forward(request, response);
                }
            }
            if (service.equals("listByTeam")) {
                int id = Integer.parseInt(request.getParameter("team_id"));
                Vector<Issue> ve = dao.listIssueByTeam(id);
                Vector<Assignee> veA = dao.getAllAssignee("4", "4");
                request.setAttribute("listTeam", ve);
                request.setAttribute("listAss", veA);
                request.setAttribute("teamID", id);
//                request.setAttribute("lstUser", lt);
                request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
            }
            if (service.equals("changeAss")) {
                int id = Integer.parseInt(request.getParameter("team_id"));
                Vector<Issue> ve = dao.listIssueByTeam(id);
                int issuid = Integer.parseInt(request.getParameter("issue_ID"));
                int assID = Integer.parseInt(request.getParameter("assign_ID"));
                Vector<Assignee> veA = dao.getAllAssignee("1", "1");
                request.setAttribute("listAss", veA);
                int n = dao.changeAssign(issuid, assID);
                request.setAttribute("listTeam", ve);
                request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
            }
            if (service.equals("addIssue")) {
//                int id = Integer.parseInt(request.getParameter("team_id"));
                String submit = request.getParameter("addnew");
                LocalDate da = java.time.LocalDate.now();
                if (submit == null) {
                    Vector<Function> lstFUC = dao.getAllFunction(1);
                    Vector<Milestone> lstMil = dao.getAllMilestones(1);
                    Vector<Assignee> veA = dao.getAllAssignee("4", "4");
                    request.setAttribute("listAss", veA);
                    request.setAttribute("lstFunc", lstFUC);
                    request.setAttribute("lstMile", lstMil);
                    request.setAttribute("date", da);
//                    out.print(veA);
                    request.getRequestDispatcher("/jsp/issue/AddIssue.jsp").forward(request, response);
                } else {
                    int assID = Integer.parseInt(request.getParameter("assID"));
                    String title = request.getParameter("title");
                    String descrip = request.getParameter("description");
                    String url = request.getParameter("urlgit");
                    String create = request.getParameter("createAT");
                    String dueDa = request.getParameter("dueDA");
                    String mile = request.getParameter("mile");
                    int funt = Integer.parseInt(request.getParameter("funtion"));
                    String label = request.getParameter("label");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Vector<Function> lstFUC = dao.getAllFunction(1);
                    Vector<Milestone> lstMil = dao.getAllMilestones(1);
                    Vector<Assignee> veA = dao.getAllAssignee("4", "4");
                    Vector<Issue> ve = dao.listIssueByTeam(1);
                    request.setAttribute("listAss", veA);
                    request.setAttribute("date", create);
                    request.setAttribute("lstFunc", lstFUC);
                    request.setAttribute("lstMile", lstMil);
                    request.setAttribute("date", da);
                    request.setAttribute("title", title);
                    request.setAttribute("description", descrip);
                    request.setAttribute("urlgit", url);
                    request.setAttribute("dueDA", dueDa);
                    request.setAttribute("label", label);
                    if (title.equals("") || title == null || url.equals("") || url == null || create.equals("")
                            || create == null || dueDa.equals("") || dueDa == null || label.equals("") || label == null) {
                        request.setAttribute("err", "Data is not allowed null!");
                        request.getRequestDispatcher("/jsp/issue/AddIssue.jsp").forward(request, response);
                        return;
                    }
                    for (Issue iu : ve) {
                        if (url.equals(iu.getGitlab_url())) {
                            request.setAttribute("err", "Gitlab URL is already exists. Enter the other link!");
                            request.getRequestDispatcher("/jsp/issue/AddIssue.jsp").forward(request, response);
                            return;
                        }
                    }
                    if (!url.startsWith("https://gitlab.com/")) {
                        request.setAttribute("err", "GitLab URL is invalid!");
                        request.getRequestDispatcher("/jsp/issue/AddIssue.jsp").forward(request, response);
                        return;
                    }
                    Issue iuu = new Issue(assID, title, descrip, url, create, dueDa, 1, funt, mile, label, status);
                    int n = dao.addIssue(iuu);
                    request.setAttribute("date", da);
                    response.sendRedirect("IssueController");
                }
            }
            if (service.equals("searchStatus")) {
                int status = Integer.parseInt(request.getParameter("statusSearch"));
                Vector<Issue> ve = dao.searchByStatus(status, 4);
                Vector<Assignee> veA = dao.getAllAssignee("4", "4");
                if (status == 0) {
                    Vector<Issue> ve1 = dao.listIssueByTeam(4);
                    request.setAttribute("listTeam", ve1);
                    request.setAttribute("listAss", veA);
                    request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
                }
                request.setAttribute("statusSerach", status);
                request.setAttribute("listTeam", ve);
                request.setAttribute("listAss", veA);
                request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
            }
            if (service.equals("removeIssue")) {
                int iuID = Integer.parseInt(request.getParameter("issueID"));
                int n = dao.removeIssue(iuID);
                Vector<Issue> ve = dao.listIssueByUser(1);
                request.setAttribute("listByUser", ve);
                request.getRequestDispatcher("/jsp/issue/ListIssueByUser.jsp").forward(request, response);
            }
            if (service.equals("syncIssue")) {
                int TEMid = Integer.parseInt(request.getParameter("team_id"));
                String prID = dao.getProject_id(4);
                String toke = dao.getTeam_tokent(4);
                int clasId = dao.getClassID(4);
//                Function f = dao.searchFuntion(TEMid, "Function 1");
//                Vector<Assignee> veA = dao.getAllAssignee("4", "4");
//                request.setAttribute("listAss", veA);
                int n = dao.syncIssue(prID, toke, 4, clasId);
//                out.println("<h1>" +TEMid + "</h1>");
//                out.println("<h2>" +prID + "</h2>");
//                out.println("<h2>" +toke + "</h2>");
//                out.println("<h2>" +clasId + "</h2>");
//                out.print(f.toString());
                if (n > 0) {
                    Vector<Issue> ve = dao.listIssueByTeam(TEMid);
                    Vector<Assignee> veA = dao.getAllAssignee("4", "4");
                    request.setAttribute("listTeam", ve);
                    request.setAttribute("listAss", veA);
                    request.setAttribute("teamID", 4);
//                    request.setAttribute("mesa", "Sync successfully!");
                    request.setAttribute("title", "Successfully");
                    request.setAttribute("message", "Sync Successfully!");
                    request.setAttribute("theme", "Success");
                    request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
                } else {
                    request.setAttribute("teamID", TEMid);
                    request.setAttribute("mesa", "Sync failed!");
                    request.getRequestDispatcher("/jsp/issue/IssueListByTeam.jsp").forward(request, response);
                }
//                if (n != 0){
//                    out.println("success!");
//                } else {
//                    out.println("some thing wrong!");
//                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
