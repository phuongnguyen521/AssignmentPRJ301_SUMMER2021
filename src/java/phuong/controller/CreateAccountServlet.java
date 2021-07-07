/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuong.registration.RegistrationCreateError;
import phuong.registration.RegistrationDAO;

/**
 *
 * @author DELL
 */
public class CreateAccountServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String CREATE_ACCOUNT_PAGE = "createNewAccount.jsp";

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
        String url = CREATE_ACCOUNT_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");

        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();

        try {
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errors.setUsernameLengthErr("Username length has 6 - 20 chars");
                foundErr = true;
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errors.setPasswordLengthErr("Password length has 6 - 30 chars");
                foundErr = true;
            } else if (!confirm.trim().equals(password.trim())) {
                errors.setConfirmNotMatch("Confirm does not match password");
                foundErr = true;
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errors.setFullnameLengthErr("Fullname has length 2 - 50");
                foundErr = true;
            }

            if (foundErr) {
                request.setAttribute("INSERT_ERRORS", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.createNewAccount(username, password, fullname, false);
                if (result) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            errors.setUsernameIsExisted(username + " is existed");
            request.setAttribute("INSERT_ERRORS", errors);
            log("CreateRecordServlet _ SQL " + ex.getMessage());
        }catch(NamingException ex){
            log("CreateRecordServlet _ Class not found" + ex.getMessage());
        }finally {
            RequestDispatcher rq = request.getRequestDispatcher(url);
            rq.forward(request, response);
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
