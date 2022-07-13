package Controller.Traking;

import DAO.DAOSen;
import Enitiy.Function;
import Enitiy.Milestone;
import Enitiy.Team;
import Enitiy.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddTracking", urlPatterns = {"/AddTracking"})
public class AddTracking extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOSen dao = new DAOSen();
        String add = request.getParameter("add");
        if (add != null) {
            String team_id = request.getParameter("team_id");
            String milestone_id = request.getParameter("milestone_id");
            String function_id = request.getParameter("function_id");
            String assigner_id = request.getParameter("assigner_id");
            String assignee_id = request.getParameter("assignee_id");
            String tracking_note = request.getParameter("tracking_note");
            String updates = request.getParameter("updates");
            String status = request.getParameter("status");
            request.setAttribute("team_id", team_id);
            request.setAttribute("milestone_id", milestone_id);
            request.setAttribute("function_id", function_id);
            request.setAttribute("assigner_id", assigner_id);
            request.setAttribute("assignee_id", assignee_id);
            request.setAttribute("updates", updates);
            request.setAttribute("tracking_note", tracking_note);
            request.setAttribute("status", status);
            request.setAttribute("title", "Add ok");
            request.setAttribute("message", "Add Tracking moi ok roi day");
            request.setAttribute("theme", "Success");
            dao.AddTracking(team_id, milestone_id, function_id, assigner_id, assignee_id, tracking_note, updates, status);
            request.getRequestDispatcher("Tracking").forward(request, response);
            return;
        }
        List<Team> lteam = dao.Team();
        request.setAttribute("lteam", lteam);
        List<Milestone> lMilestone = dao.Milestone();
        request.setAttribute("lMilestone", lMilestone);
        List<Function> lFunction = dao.Function();
        request.setAttribute("lFunction", lFunction);
        List<User> lStudent = dao.Student2();
        request.setAttribute("lStudent", lStudent);
        List<User> lNotStudent = dao.NotStudent();
        request.setAttribute("lNotStudent", lNotStudent);
        request.getRequestDispatcher("jsp/Tracking/AddTracking.jsp").forward(request, response);

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