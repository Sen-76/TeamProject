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

@WebServlet(name = "LoadList", urlPatterns = {"/LoadList"})
public class LoadList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User Loged = (User) session.getAttribute("Loged");
        String count = request.getParameter("total");
        String like = request.getParameter("likes");
        String orders = request.getParameter("lkkk");
        String orders2 = request.getParameter("lkkka");
        String het = request.getParameter("het");
        int total = Integer.parseInt(count);
        DAOSen dao = new DAOSen();
        List<Subject> list = null;
        if (like == null || like.equals("")) {
            if (Loged == null || Loged.getRole_id() != 4) {
                list = dao.AllSubject2(total, orders + " " + orders2);
            } else {
                list = dao.AllSubject(total, orders + " " + orders2);
            }
        } else {
            if (Loged == null || Loged.getRole_id() != 4) {
                list = dao.SearchSubject3(like, total, orders + " " + orders2);

            } else {
                list = dao.SearchSubject2(like, total, orders + " " + orders2);
            }
        }
        if (list.isEmpty()) {
            out.print("<input id=\"het\" type=\"hidden\" value=\"a\">");
            return;
        }
        if (Loged == null || Loged.getRole_id() != 4) {
            for (Subject o : list) {
                out.println("<li>\n"
                        + "                                    <div class=\"count box\">\n"
                        + "                                        <div class=\"fortext\">\n"
                        + "                                        <a  href=\"ShowAllClass?go=showBySubject&subjectId="+o.getSubject_id()+"\" style=\"color: black\"><h5 class=\"two-lines\">(" + o.getSubject_code() + ") " + o.getSubject_name() + "</h5></a>\n"
                        + "                                        </div>\n"
                        + "                                        <span><ion-icon name=\"person\"></ion-icon> Author: " + o.getAuthor_name() + "</span><br>\n");
                out.print("<div class=\"aa\">\n"
                        + "                                            <a class=\"a\"  href=\"ShowAllClass?go=showBySubject&subjectId="+o.getSubject_id()+"\">Go to your course  <ion-icon style=\"margin-left: 3px\" name=\"arrow-forward\"></ion-icon></a>\n"
                        + "                                        </div>   \n"
                        + "                                    </div>\n"
                        + "                                </li>");
            }
        } else {
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
