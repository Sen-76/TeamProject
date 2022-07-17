/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Criteria;

import DAO.DAOCriteria;
import Enitiy.Criteria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CriteriaSearch", urlPatterns = {"/CriteriaSearch"})
public class CriteriaSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String submit = request.getParameter("submit");
            String indexP = request.getParameter("index");
            if (indexP == null) {
                indexP = "1";
            }
            int index = Integer.parseInt(indexP);
            DAOCriteria dao = new DAOCriteria();
            int count = dao.getTotalList();
            int maxPage = count / 10;
            if (count % 10 != 0) {
                maxPage++;
            }
            if (submit == null) {
                List<Criteria> list = dao.viewCriteriaList(index);
//            List<Criteria> list1 = dao.viewCriteriaList();
                List<Criteria> listCri = dao.viewSubjectId();
                request.setAttribute("subjectList", listCri);
                //  out.print(listCri);        
                request.setAttribute("maxP", maxPage);
                request.setAttribute("CriteriaList", list);
                request.getRequestDispatcher("jsp/CriteriaList.jsp").forward(request, response);
            } else {
                String subject = request.getParameter("subject");
                String search = request.getParameter("searchName");
                if (subject.equals("all") && search.equals("")) {
                    List<Criteria> list = dao.viewCriteriaList(index);
//            List<Criteria> list1 = dao.viewCriteriaList();
                    List<Criteria> listCri = dao.viewSubjectId();
                    request.setAttribute("subjectList", listCri);
                    //  out.print(listCri);        
                    request.setAttribute("maxP", maxPage);
                    request.setAttribute("CriteriaList", list);
                    request.getRequestDispatcher("jsp/CriteriaList.jsp").forward(request, response);
                }
                if (!subject.equals("all")) {
                    List<Criteria> listCri = dao.viewSubjectId();
                    List<Criteria> listSub = dao.searchSubject(subject);
                    request.setAttribute("CriteriaList", listSub);
                    request.setAttribute("subjectList", listCri);
                    request.getRequestDispatcher("jsp/CriteriaList.jsp").forward(request, response);
                }
                if (!search.equals("") && search.matches("^\\d+$")) {

                    List<Criteria> list = dao.searchLoc(search);
                    List<Criteria> listCri = dao.viewSubjectId();
                    request.setAttribute("subjectList", listCri);
                    request.setAttribute("txtSearch", search);
                    request.setAttribute("CriteriaList", list);
                    request.getRequestDispatcher("jsp/CriteriaList.jsp").forward(request, response);
                }
                if (!search.equals("")) {
                    List<Criteria> list = dao.searchByIterName(search);
                    List<Criteria> listCri = dao.viewSubjectId();
                    request.setAttribute("subjectList", listCri);
//                out.print(list1);
                    request.setAttribute("txtSearch", search);
                    request.setAttribute("CriteriaList", list);
                    request.getRequestDispatcher("jsp/CriteriaList.jsp").forward(request, response);
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
