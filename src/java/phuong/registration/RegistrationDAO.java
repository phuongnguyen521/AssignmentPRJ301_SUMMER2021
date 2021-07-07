/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.registration;

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
public class RegistrationDAO implements Serializable {

    private RegistrationDTO user;

    private List<RegistrationDTO> listDTO;

    public List<RegistrationDTO> getListDTO() {
        return listDTO;
    }

    public RegistrationDTO getUser() {
        return user;
    }

    public String checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = "";
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT username, lastname "
                        + "FROM Registration "
                        + "WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                while (rs.next()) {
                    if (rs.getString("username").equals(username)) {
                        result = rs.getString("lastname");
                    }
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

    public void searchLastName(String searchValue)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT username, password, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastname LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    if (this.listDTO == null) {
                        listDTO = new ArrayList<>();
                    }
                    RegistrationDTO dto
                            = new RegistrationDTO(username, password, lastname, role);
                    this.listDTO.add(dto);
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

    public boolean deleteRecord(String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM Registration WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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

    public boolean updateRecord(String username, String password, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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
    
    public boolean updateRecordByEdit(RegistrationDTO editAccount)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ?, lastname = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, editAccount.getPassword());
                stm.setBoolean(2, editAccount.isRole());
                stm.setString(3, editAccount.getLastname());
                stm.setString(4, editAccount.getUsername());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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

    public boolean retrieveInfromation(String username, String searchValue) 
    throws NamingException, SQLException{
        searchLastName(searchValue);
        user = null;
        boolean result = false;
        RegistrationDTO temp = new RegistrationDTO(username);
        for (RegistrationDTO list : listDTO) {
            if (list.getUsername().equals(username)){
                user = new RegistrationDTO
        (username, list.getPassword(), list.getLastname(), list.isRole());
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean createNewAccount(String username, String password,
            String fullname, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Registration(username, password, lastname, isAdmin) "
                        + "VALUES(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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
