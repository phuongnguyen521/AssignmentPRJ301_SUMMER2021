/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.orderDetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phuong.product.ProductDTO;
import phuong.utils.DBHelpers;

/**
 *
 * @author DELL
 */
public class OrderDetailsDAO implements Serializable {

    public boolean checkOutOrder(ProductDTO dto, int orderDetailId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "EXEC INSERTORDERDETAIL ?, ?, ?, ?, ?, ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderDetailId);
                stm.setInt(2, dto.getSku());
                stm.setString(3, dto.getName());
                stm.setDouble(4, dto.getPrice());
                stm.setInt(5, dto.getQuantity());
                double total = dto.getPrice() * dto.getQuantity();
                stm.setDouble(6, total);
                int row = stm.executeUpdate();
                if (row > 0){
                    result = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateTotalInOrders(int orderDetailId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "EXEC UPDATETOTALINORDER ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderDetailId);
                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getOrderDetailID()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = -1;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT orderDetailId "
                        + "FROM OrderDetail "
                        + "ORDER BY orderDetailId DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("orderDetailId");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
