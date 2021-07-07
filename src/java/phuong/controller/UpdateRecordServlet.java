/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuong.registration.RegistrationDAO;
import phuong.registration.UpdateAccountErrors;

/**
 *
 * @author DELL
 */
public class UpdateRecordServlet extends HttpServlet {

    private final String UPDATE_ERROR_PAGE = "updateErr";

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
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        boolean role = false;
        if (admin != null) {
            role = true;
        }
        String searchValue = request.getParameter("lastSearchValue");
        String urlRewriting = UPDATE_ERROR_PAGE;
        boolean result = true;
        boolean foundError = false;
        try {
            RegistrationDAO dao = new RegistrationDAO();
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                String mgs = "Password must have length from 6 to 30 chars";
                UpdateAccountErrors errors = new UpdateAccountErrors();
                errors.setPasswordLenghErr(mgs);
                errors.setUsername(username);
                foundError = true;
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_PASS", errors);
            } else {
                result = dao.updateRecord(username, password, role);
                HttpSession session = request.getSession();
                session.removeAttribute("ERROR_PASS");
            }
            if (result) {
                urlRewriting = "search_Account?"
                        + "&txtSearchValue="
                        + searchValue;
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                response.sendRedirect(urlRewriting);
                out.close();
//            }
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
