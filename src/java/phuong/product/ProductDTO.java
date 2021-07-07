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
public class ProductDTO implements Serializable {

    private int sku;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private double total;

    public ProductDTO(int sku) {
        this.sku = sku;
    }

    public ProductDTO(int sku, String name, double price, String description, int quantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.total = price * quantity;
    }

    public ProductDTO(int sku, String name, double price, int quantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
    }

    /**
     * @return the sku
     */
    public int getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(int sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
    

}
