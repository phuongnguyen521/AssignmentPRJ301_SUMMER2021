/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuong.registration.RegistrationDAO;

/**
 *
 * @author DELL
 */
public class DeleteProcesServlet extends HttpServlet {

    private final String CONFIRM_DELETE = "confirmDelete.jsp";
    private final String LOGOUT_SERVLET = "logOutAccount";
    private final String DELETE_ERROR = "deleteError.html";
    private String username = "";
    private String searchValue = "";

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
        HttpSession session = request.getSession();
        String username = request.getParameter("pk");
        String searchValue = request.getParameter("lastSearchValue");
        String button = request.getParameter("btAction");
        String url = DELETE_ERROR;
        try {
            if (button == null) {
                url = CONFIRM_DELETE;
                session.setAttribute("USERNAMEDELETE", username);
                session.setAttribute("SEARCHVALUE", searchValue);
            } else {
                if (button.equals("Confirm")) {
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.deleteRecord(username);
                    if (result) {
                        url = LOGOUT_SERVLET;
                    }
                }
                if (button.equals("Cancel")) {
                    url = "searchAccount?"
                            + "&txtSearchValue="
                            + searchValue;
                    session.removeAttribute("USERNAMEDELETE");
                    session.removeAttribute("SEARCHVALUE");
                }
            }
        } catch (NamingException ex) {
            log("DeleteProcesServlet _Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("DeleteProcesServlet _SQL" + ex.getMessage());
        } finally {
            if (url.contains("html") || url.contains("jsp")) {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            response.sendRedirect(url);
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
