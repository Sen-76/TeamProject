package Controller;

import DAO.DAOSen;
import DAO.DAOUpdate;
import Enitiy.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpdateImg extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if (session.getAttribute("Loged") == null) {
                request.getRequestDispatcher("Login_sen").forward(request, response);
            }
            if (session.getAttribute("Loged") != null) {
                String filename = null;
                DAOUpdate dao = new DAOUpdate();
                DAOSen daos = new DAOSen();
                try {
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    ServletContext servletContext = this.getServletConfig().getServletContext();
                    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
                    factory.setRepository(repository);
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List<FileItem> items = upload.parseRequest(request);
                    Iterator<FileItem> iter = items.iterator();
                    HashMap<String, String> fields = new HashMap<>();
                    while (iter.hasNext()) {
                        FileItem item = iter.next();
                        if (item.isFormField()) {
                            fields.put(item.getFieldName(), item.getString());
                        } else {
                            filename = item.getName();
                            String useridd = fields.get("userid");
                            if (filename == null || filename.equals("")) {
                                filename = daos.Loged(useridd).getAvatar_link();
                                request.setAttribute("haizz", "Làm ơn hãy chọn một ảnh rồi mới Lưu");
                                request.getRequestDispatcher("jsp/update.jsp").forward(request, response);
                            } else {
                                filename = daos.RandomBullSh() + item.getName();
                                Path path = Paths.get(filename);
                                String storePath = servletContext.getRealPath("/uploads");
                                File uploadFile = new File(storePath + "/" + path.getFileName());
                                File deleteFile = new File(storePath + "/" + daos.Loged(useridd).getAvatar_link());
                                deleteFile.delete();
                                item.write(uploadFile);
                            }
                        }
                    }
                    String userid = fields.get("userid");
                    dao.UpdateAvatar(userid, filename);
                    session.removeAttribute("Loged");
                    User Loged = daos.Loged(userid);
                    session.setAttribute("Loged", Loged);
                } catch (Exception e) {
                }

                request.getRequestDispatcher("jsp/update.jsp").forward(request, response);
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
