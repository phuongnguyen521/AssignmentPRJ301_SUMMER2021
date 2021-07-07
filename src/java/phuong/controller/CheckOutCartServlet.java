/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import phuong.cart.CartObj;
import phuong.orderDetails.OrderDetailsDAO;
import phuong.product.ProductDTO;

/**
 *
 * @author DELL
 */
public class CheckOutCartServlet extends HttpServlet {

    private final String CHECK_OUT = "checkOut";
    private final String SHOPPING_ONLINE = "shopping_servlet";

    private boolean createOrderDetail(CartObj cart, int orderDetailId) {
        boolean result = true;
        OrderDetailsDAO dao = new OrderDetailsDAO();
        Set<Integer> set = cart.getItems().keySet();
        try {
            for (Integer sku : set) {
                ProductDTO dto = cart.getProductFromCart(sku);
                if (dto != null) {
                    result = dao.checkOutOrder(dto, orderDetailId);
                    if (result == false) {
                        break;
                    }
                }
            }
        } catch (NamingException ex) {
            log("createOrderDetail _Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("createOrderDetail _SQL" + ex.getMessage());
        }
        return result;
    }

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
        String url = CHECK_OUT;
        String button = request.getParameter("btAction");
        try {
            if (button.equals("Check Out")) {
                HttpSession session = request.getSession(false);
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    OrderDetailsDAO dao = new OrderDetailsDAO();
                    int orderDetailId = dao.getOrderDetailID();
                    if (orderDetailId == -1) {
                        orderDetailId = 1;
                    } else {
                        orderDetailId += 1;
                    }
                    boolean result = createOrderDetail(cart, orderDetailId);
                    result = dao.updateTotalInOrders(orderDetailId);
                    if (result) {
                        session.setAttribute("CART", null);
                        cart = null;
                        url = SHOPPING_ONLINE;
                    }
                }
            }
        } catch (NamingException ex) {
            log("createOrderDetail _Naming" + ex.getMessage());
        } catch (SQLException ex) {
            log("createOrderDetail _SQL" + ex.getMessage());
        } finally {
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
