package Controller;

import DAO.DAOSen;
import Enitiy.Subject;
import Enitiy.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SearchSubject", urlPatterns = {"/SearchSubject"})
public class SearchSubject extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        DAOSen dao = new DAOSen();
        String txtSearch = request.getParameter("txt");
        String like = request.getParameter("likes");
        if (txtSearch == null) {
            txtSearch = like;
        }
        out.print("<input oninput=\"searchByName(this)\" id=\"search\" class=\"search form-control form-control-user\" name=\"txt\" type=\"hidden\" value=\"" + txtSearch + "\" placeholder=\"Search mã môn hoặc tên môn học ở đây\">");
        String orders = request.getParameter("lkkk");
        String orders2 = request.getParameter("lkkka");
        List<Subject> listl = dao.SearchSubjecta(txtSearch, orders + " " + orders2);
        List<Subject> listla = dao.SearchSubject(txtSearch, orders + " " + orders2);
        int n = 0;
        List<Subject> list = null;
        if (Loged == null || Loged.getRole_id() != 4) {
            list = dao.SearchSubject3(txtSearch, 0, orders + " " + orders2);
        } else {
            list = dao.SearchSubject2(txtSearch, 0, orders + " " + orders2);
        }
        request.setAttribute("txt", txtSearch);
        if (list.isEmpty()) {
            out.println("Khong co cai gi phu hop ca");
            return;
        }
        if (Loged == null || Loged.getRole_id() != 4) {
            for (Subject subject : listl) {
                n++;
            }
            out.println("<span class=\"spn\">" + n + " Subject(s) found</span><br>");
            for (Subject o : list) {
                out.println("<li>\n"
                        + "                                    <div class=\"count box\">\n"
                        + "                                        <div class=\"fortext\">\n"
                        + "                                        <a href=\"ShowAllClass?go=showBySubject&subjectId="+o.getSubject_id()+"\" style=\"color: black\"><h5 class=\"two-lines\">(" + o.getSubject_code() + ") " + o.getSubject_name() + "</h5></a>\n"
                        + "                                        </div>\n"
                        + "                                        <span><ion-icon name=\"person\"></ion-icon> Author: " + o.getAuthor_name() + "</span><br>\n");
                out.print("<div class=\"aa\">\n"
                        + "                                            <a class=\"a\"  href=\"ShowAllClass?go=showBySubject&subjectId="+o.getSubject_id()+"\">Go to your course  <ion-icon style=\"margin-left: 3px\" name=\"arrow-forward\"></ion-icon></a>\n"
                        + "                                        </div>   \n"
                        + "                                    </div>\n"
                        + "                                </li>");
            }
        } else {
            for (Subject subject : listla) {
                n++;
            }
            out.print("<span class=\"spn\">" + n + " Subject found</span>");
            for (Subject o : list) {
                out.println("<li>\n"
                        + "                                    <div class=\"count box\">\n"
                        + "                                        <c:if test=\"${Loged.role_id == 4}\">\n"
                        + "                                        <div class=\"aaa\">\n"
                        + "                                            <a href=\"SubjectList?id=" + o.getSubject_id() + "\"><ion-icon name=\"close\"></ion-icon></a>\n"
                        + "                                        </div> \n"
                        + "                                        </c:if>\n"
                        + "                                        <div class=\"fortext\">\n"
                        + "                                        <a  href=\"ShowAllClass?go=showBySubject&subjectId="+o.getSubject_id()+"\" style=\"color: black\"><h5 class=\"two-lines\">(" + o.getSubject_code() + ") " + o.getSubject_name() + "</h5></a>\n"
                        + "                                        </div>\n"
                        + "                                        <span><ion-icon name=\"person\"></ion-icon> Author: " + o.getAuthor_name() + "</span><br>\n");
                if (o.getStatus() == 1) {
                    out.print("<span><ion-icon name=\"person\"></ion-icon> Status: Active</span><br>\n");
                } else {
                    out.print("<span><ion-icon name=\"person\"></ion-icon> Status: Not Active</span><br>\n");
                }
                out.print("<div class=\"aa\">\n"
                        + "                                            <c:if test=\"${Loged.role_id == 4}\">\n"
                        + "                                            <a class=\"a\" style=\"margin-right: 15px\" href=\"UpdateSubject?id=" + o.getSubject_id() + "\"><ion-icon name=\"refresh\"></ion-icon>Update</a>\n"
                        + "                                                    </c:if>\n"
                        + "                                            <a class=\"a\"  href=\"ShowAllClass?go=showBySubject&subjectId="+o.getSubject_id()+"\">Go to your course  <ion-icon style=\"margin-left: 3px\" name=\"arrow-forward\"></ion-icon></a>\n"
                        + "                                        </div>   \n"
                        + "                                    </div>\n"
                        + "                                </li>");
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
