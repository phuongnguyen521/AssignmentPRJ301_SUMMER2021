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
public class RegistrationEditError implements Serializable {

    private String passswordLengthErr;
    private String lastnameLengthErr;

    public RegistrationEditError() {
    }

    /**
     * @return the passswordLengthErr
     */
    public String getPassswordLengthErr() {
        return passswordLengthErr;
    }

    /**
     * @param passswordLengthErr the passswordLengthErr to set
     */
    public void setPassswordLengthErr(String passswordLengthErr) {
        this.passswordLengthErr = passswordLengthErr;
    }

    /**
     * @return the lastnameLengthErr
     */
    public String getLastnameLengthErr() {
        return lastnameLengthErr;
    }

    /**
     * @param lastnameLengthErr the lastnameLengthErr to set
     */
    public void setLastnameLengthErr(String lastnameLengthErr) {
        this.lastnameLengthErr = lastnameLengthErr;
    }

}
