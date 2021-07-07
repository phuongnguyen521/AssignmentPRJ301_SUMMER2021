/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.registration;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class UpdateAccountErrors implements Serializable{
    private String username;
    private String passwordLenghErr;

    public UpdateAccountErrors() {
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the passwordLenghErr
     */
    public String getPasswordLenghErr() {
        return passwordLenghErr;
    }

    /**
     * @param passwordLenghErr the passwordLenghErr to set
     */
    public void setPasswordLenghErr(String passwordLenghErr) {
        this.passwordLenghErr = passwordLenghErr;
    }
    
    
}
