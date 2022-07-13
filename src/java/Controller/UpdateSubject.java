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

@WebServlet(name = "UpdateSubject", urlPatterns = {"/UpdateSubject"})
public class UpdateSubject extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String update = request.getParameter("update");
            String id = request.getParameter("id");
            request.setAttribute("id", id);
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            DAOSen dao = new DAOSen();
            Subject s = dao.IdSubject(id);
            List<User> listU = dao.AllAuthor();
            request.setAttribute("listU", listU);
            request.setAttribute("s", s);
            if (update == null) {
                request.getRequestDispatcher("jsp/UpdateSubject.jsp").forward(request, response);
            } else {
                String subject_code = request.getParameter("subject_code");
                String subject_name = request.getParameter("subject_name");
                String author = request.getParameter("author");
                String status = request.getParameter("status");
                if (subject_code.equals("") || subject_name.equals("") || author.equals("name")) {
                    request.setAttribute("title", "Update thất bại");
                    request.setAttribute("message", "Hãy nhập đầy đủ thông tin!");
                    request.setAttribute("theme", "Danger");
                    request.setAttribute("active", "active");
                    request.setAttribute("code", subject_code);
                    request.setAttribute("name", subject_name);
                    request.setAttribute("author", author);
                    request.getRequestDispatcher("jsp/UpdateSubject.jsp").forward(request, response);
                    return;
                }
                if (subject_code.length() > 15 || subject_name.length() > 100) {
                    request.setAttribute("title", "Update thất bại");
                    request.setAttribute("message", "Subject code hoặc Subject name quá dài!");
                    request.setAttribute("theme", "Danger");
                    request.setAttribute("active", "active");
                    request.setAttribute("code", subject_code);
                    request.setAttribute("name", subject_name);
                    request.setAttribute("author", author);
                    request.getRequestDispatcher("jsp/UpdateSubject.jsp").forward(request, response);
                    return;
                } else {
                    int n = dao.UpdateSubject(id, subject_name, author, status, subject_code);
                    if (n > 0) {
                        request.setAttribute("title", "Update thành công");
                        request.setAttribute("message", "Vua add duoc roi day dm search no ra di!");
                        request.setAttribute("theme", "Success");
                        request.getRequestDispatcher("SubjectList").forward(request, response);
                        return;
                    } else {
                        request.setAttribute("title", "Update thất bại");
                        request.setAttribute("message", "Môn học đã tồn tại!");
                        request.setAttribute("theme", "Danger");
                        request.setAttribute("active", "active");
                        request.setAttribute("code", subject_code);
                        request.setAttribute("name", subject_name);
                        request.setAttribute("author", author);
                        request.getRequestDispatcher("jsp/UpdateSubject.jsp").forward(request, response);
                    }
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
