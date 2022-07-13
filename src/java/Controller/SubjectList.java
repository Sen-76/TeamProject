package Controller;

import DAO.DAOSen;
import DAO.DAOchangePass;
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

@WebServlet(urlPatterns = {"/SubjectList"})
public class SubjectList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOSen dao = new DAOSen();
            DAOchangePass daoc = new DAOchangePass();
            List<User> listU = dao.AllAuthor();
            String add = request.getParameter("add");
            HttpSession session = request.getSession();
            User Loged = (User) session.getAttribute("Loged");
            
            if (Loged == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
                return;
            }
            if (add != null) {
                String status = "1";
                String code = daoc.chuannHoa(request.getParameter("code"));
                String name = daoc.chuannHoa(request.getParameter("name"));
                String author = daoc.chuannHoa(request.getParameter("author"));
                List<Subject> listl = dao.AllSubjecta();
                for (Subject o : listl) {
                    if (o.getSubject_code().equals(code) || o.getSubject_name().equals(name)) {
                        request.setAttribute("title", "Add thất bại");
                        request.setAttribute("message", "Môn học đã tồn tại!");
                        request.setAttribute("theme", "Danger");
                        request.setAttribute("active", "active");
                        request.setAttribute("code", code);
                        request.setAttribute("name", name);
                        request.setAttribute("author", author);
                        List<Subject> listl2 = dao.AllSubjecta2();
                        List<Subject> listl3 = dao.AllSubjecta();
                        int n = 0;
                        int nn = 0;
                        for (Subject subject : listl2) {
                            n++;
                        }
                        for (Subject subject : listl3) {
                            nn++;
                        }
                        request.setAttribute("count", n);
                        request.setAttribute("count1", nn);
                        List<Subject> list = dao.AllSubject(0, "subject_code");
                        request.setAttribute("list", list);
                        List<Subject> list2 = dao.AllSubject2(0, "subject_code");
                        request.setAttribute("list2", list2);
                        request.setAttribute("listU", listU);
                        request.getRequestDispatcher("jsp/SubjectList.jsp").forward(request, response);

                        return;
                    }
                }
                if (code.length() > 15 || name.length() > 100) {
                    request.setAttribute("title", "Add thất bại");
                    request.setAttribute("message", "Subject code hoặc Subject name quá dài!");
                    request.setAttribute("theme", "Danger");
                    request.setAttribute("active", "active");
                    request.setAttribute("code", code);
                    request.setAttribute("name", name);
                    request.setAttribute("author", author);
                }
                if (code.equals("") || name.equals("") || author.equals("name")) {
                    request.setAttribute("title", "Add thất bại");
                    request.setAttribute("message", "Hãy nhập đầy đủ thông tin!");
                    request.setAttribute("theme", "Danger");  // Danger == mau do
                    request.setAttribute("active", "active");
                    request.setAttribute("code", code);
                    request.setAttribute("name", name);
                    request.setAttribute("author", author);
                } else {
                    request.setAttribute("title", "Add thành công");
                    request.setAttribute("message", "Vua add duoc roi day dm search no ra di!");
                    request.setAttribute("theme", "Success");   // Success == mau xanh
                    dao.AddSubject(code, name, author, status);
                }
            }
            List<Subject> listl2 = dao.AllSubjecta2();
            List<Subject> listl3 = dao.AllSubjecta();
            int n = 0;
            int nn = 0;
            for (Subject subject : listl2) {
                n++;
            }
            for (Subject subject : listl3) {
                nn++;
            }
            
            request.setAttribute("count", n);
            request.setAttribute("count1", nn);
            List<Subject> list = dao.AllSubject(0, "subject_code");
            request.setAttribute("list", list);
            List<Subject> list2 = dao.AllSubject2(0, "subject_code");
            request.setAttribute("list2", list2);
            request.setAttribute("listU", listU);
            request.getRequestDispatcher("jsp/SubjectList.jsp").forward(request, response);
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
