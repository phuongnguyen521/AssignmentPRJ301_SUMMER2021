/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.product;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class ProductErrors implements Serializable {

    private String namingError;
    private String sqlError;

    public ProductErrors() {
    }

    /**
     * @return the namingError
     */
    public String getNamingError() {
        return namingError;
    }

    /**
     * @param namingError the namingError to set
     */
    public void setNamingError(String namingError) {
        this.namingError = namingError;
    }

    /**
     * @return the sqlError
     */
    public String getSqlError() {
        return sqlError;
    }

    /**
     * @param sqlError the sqlError to set
     */
    public void setSqlError(String sqlError) {
        this.sqlError = sqlError;
    }

}
