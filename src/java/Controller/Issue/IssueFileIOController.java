package Controller.Issue;

import DAO.Issue.IssueExcelDAO;
import Enitiy.IsseExcel;
import java.io.File;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "IssueFileIOController", urlPatterns = {"/IssueFile"})
public class IssueFileIOController extends HttpServlet {

    IssueExcelDAO daoIU = new IssueExcelDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("go");
        if (service == null) {
            service = "uploadIssue";
        }
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            if (service.equals(("uploadIssue"))){
                String team_id = request.getParameter("team_id");
                session.setAttribute("teamID", team_id);
                request.getRequestDispatcher("/jsp/issue/UploadIssue.jsp").forward(request, response);
            }
            if (service.equals("importIssue")) {
                String filename = null;
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

                        if (filename == null || filename.equals("")) {

                            request.setAttribute("messE", "Hãy import file vào trước");
                            request.getRequestDispatcher("/jsp/issue/UploadIssue.jsp").forward(request, response);

                        } else {
                            filename = daoIU.RandomBullSh() + item.getName();
                            Path path = Paths.get(filename);
                            String storePath = servletContext.getRealPath("/uploads");
                            File uploadFile = new File(storePath + "/" + path.getFileName());
//                            out.print(storePath);
                            try {
                                item.write(uploadFile);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                String storePath = servletContext.getRealPath("/uploads");
                Vector<IsseExcel> veiu = daoIU.readExcel1(storePath + "/" + filename);
                String team_id =(String) session.getAttribute("teamID");
                for (IsseExcel o : veiu) {
                    o.setTeam_id(team_id);
                }
                session.setAttribute("vect", veiu);
                request.getRequestDispatcher("/jsp/issue/UploadIssue.jsp").forward(request, response);
//                out.print(team_id);
            }
        } catch (FileUploadException ex) {
            ex.printStackTrace();
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
    }// </editor-fold>

}
