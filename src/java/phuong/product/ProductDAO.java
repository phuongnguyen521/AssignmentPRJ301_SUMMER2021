/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phuong.utils.DBHelpers;

/**
 *
 * @author DELL
 */
public class ProductDAO implements Serializable {
    private List<ProductDTO> product;

    public List<ProductDTO> getProduct() {
        return product;
    }

    public void loadProduct() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "EXEC GETPRODUCT";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int sku = rs.getInt("sku");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    if (this.product == null) {
                        this.product = new ArrayList<>();
                    }
                    ProductDTO dto = new ProductDTO(sku, name, price, description, quantity);
                    this.product.add(dto);
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
    }

    public ProductDTO getProduct(int sku)
        throws NamingException, SQLException{
        ProductDTO dto = null;
        loadProduct();
        for (ProductDTO list : product) {
            if (list.getSku() == sku){
                dto = new ProductDTO(sku, list.getName(), 
                        list.getPrice(), list.getDescription(), 1);
                break;
            }
        }
        return dto;
    }

    public boolean updateProduct(int sku, String action, int quantity)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            String sql = "EXEC UPDATEPRODUCTBYID ?, ?, ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, sku);
            stm.setString(2, action.trim().toUpperCase());
            stm.setInt(3, quantity);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
