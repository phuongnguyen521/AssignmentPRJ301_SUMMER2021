/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuong.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import phuong.product.ProductDTO;

/**
 *
 * @author DELL
 */
public class CartObj implements Serializable {

    private HashMap<Integer, ProductDTO> items;

    public Map<Integer, ProductDTO> getItems() {
        return items;
    }
    
    public boolean checkExistedProduct(int sku){
        boolean result = false;
        if (this.items == null){
            return result;
        } 
        result = this.items.containsKey(sku);
        return result;
    }
    
    
    public void addItemtoCart(int sku, ProductDTO dto) {
        //1.check Item's item is existed
        if (sku <= 0) {
            return;
        }
        //2. item is existed
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. when item is existed, checking existed id
        int quantity = 1;
        if (this.items.containsKey(sku)) {
            quantity = this.items.get(sku).getQuantity() + 1;
            dto.setQuantity(quantity);
        }
        //4.update items
        this.items.put(sku, dto);
    }

    public void removeItemFromCart(int sku) {
        //1. check selected item
        if (this.items == null) {
            return;
        }

        //checked item has existed, check existed id
        if (this.items.containsKey(sku)) {
            this.items.remove(sku);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

    public ProductDTO getProductFromCart(int sku) {
        ProductDTO dto = null;
        if (this.items.containsKey(sku)) {
            String name = this.items.get(sku).getName();
            double price = this.items.get(sku).getPrice();
            int quantity = this.items.get(sku).getQuantity();
            dto = new ProductDTO(sku, name, price, quantity);
        }
        return dto;
    }

}
