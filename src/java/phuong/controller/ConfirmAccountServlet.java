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
import phuong.registration.RegistrationDTO;

/**
 *
 * @author DELL
 */
public class ConfirmAccountServlet extends HttpServlet {

    private final String EDIT_ACCOUNT = "edit";

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
        String url = EDIT_ACCOUNT;
        HttpSession session = request.getSession();
        RegistrationDTO dto = (RegistrationDTO) session.getAttribute("INFORM");
        String username = dto.getUsername();
        String button = request.getParameter("btAction");
        String searchValue = (String) session.getAttribute("lastSearchValue");
        try {
            if (button.equals("Confirm")) {
                RegistrationDAO dao = new RegistrationDAO();
                if (dao.updateRecordByEdit(dto)) {
                    url = "searchAccount?"
                            + "&txtSearchValue="
                            + searchValue;
                    session.removeAttribute("INFORM");
                    session.removeAttribute("EDIT_ERROR");
                }
            } else if (button.equals("Cancel")) {
                request.setAttribute("INFORM", dto);
                request.setAttribute("lastSearchValue", searchValue);
            }
        } catch (SQLException ex) {
            log("EditAccountServlet _IOException" + ex.getMessage());
        } catch (NamingException ex) {
            log("EditAccountServlet _Naming" + ex.getMessage());
        } finally {
            if (url.contains("&txtSearchValue")) {
                response.sendRedirect(url);
            } else {
                ServletContext context = request.getServletContext();
                Map<String, String> mapper = (Map<String, String>) context.getAttribute("MAP");
                String resource = mapper.get(url);
                RequestDispatcher rq = request.getRequestDispatcher(resource);
                rq.forward(request, response);
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
