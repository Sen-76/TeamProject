/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Class;

import DAO.DAOSen;
import DAO.DAOchangePass;
import Enitiy.Class_s;
import Enitiy.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ClassTrainer", urlPatterns = {"/ClassTrainer"})
public class ClassTrainer extends HttpServlet {

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
            String service = request.getParameter("go");
            DAOchangePass dao = new DAOchangePass();
            DAOSen daoS = new DAOSen();
            HttpSession session = request.getSession();
            if(service == null){
                service = "";
                request.getRequestDispatcher("Home").forward(request, response);
            }
            if(service.equals("showAllByTrain")){
                String id = request.getParameter("Tid");
                User Loged = (User) session.getAttribute("Loged");
                Vector<Class_s> vectC = dao.viewAllClassByTrainer(Loged.getUser_id() + "");
                
                //=====filter=========================================================
                
                String searchClass = request.getParameter("searchClass");
                if(searchClass == null || searchClass.equals("")){
                    searchClass = "";
                }
                else{
                    Vector<Class_s> vectC0 = new Vector<>();
                    for (Class_s o : vectC) {
                        out.print(o);
                        if(o.getClassCode().toLowerCase().contains(searchClass.toLowerCase())
                        || o.getClassTerm().toLowerCase().contains(searchClass.toLowerCase())
                        || o.getClassYear().toLowerCase().contains(searchClass.toLowerCase())
                        || o.getSubjectId().toLowerCase().contains(searchClass.toLowerCase())
                        || o.getClassTerm().toLowerCase().contains(searchClass.toLowerCase())
                                )
                            vectC0.add(o);
                    }
                    vectC = vectC0;
                }
                request.setAttribute("search", searchClass);
                
                String sta = request.getParameter("ClaSta");
                if(sta == null || sta.equals("")){
                    sta = "2";
                }
                
                if(!sta.equals("2")){
                    int a = Integer.parseInt(sta);
                     Vector<Class_s> vectC0 = new Vector<>();
                    for (Class_s o : vectC) {
                        out.print(o);
                        if(o.getStatus() == a)
                            vectC0.add(o);
                    }
                    vectC = vectC0;
                }
                
                request.setAttribute("sta", sta);
               
                //=====filter=========================================================
                
                request.setAttribute("vectC", vectC);
                
                request.getRequestDispatcher("/jsp/Class/ClassByTrain.jsp").forward(request, response);
            }
            if(service.equals("detailClass")){
                String id = request.getParameter("cid");
                List<Enitiy.User> list = daoS.StudentInClass(id);
                Enitiy.ClassUser oneClass = daoS.OneClass(id);

                request.setAttribute("list", list);
                request.setAttribute("oneClass", oneClass);
                
                request.getRequestDispatcher("/jsp/ClassUser/ViewStudenInClass.jsp").forward(request, response);
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
