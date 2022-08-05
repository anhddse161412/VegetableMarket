/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Utech
 */
public class Cart {

    private Map<String, Item> cart;

    public Cart() {
    }

    public Cart(Map<String, Item> cart) {
        this.cart = cart;
    }

    public Map<String, Item> getCart() {
        return cart;
    }

    public void setCart(Map<String, Item> cart) {
        this.cart = cart;
    }

    public boolean add(Item item) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(item.getId())) {
            int currentQuantity = cart.get(item.getId()).getQuantity();
            item.setQuantity(currentQuantity + item.getQuantity());
        }
        cart.put(item.getId(), item);
        check = true;
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.remove(id);
            }
        }
        return check;
    }

    public boolean edit(String id, Item item) {
        boolean check = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, item);
            }
        }
        return check;
    }
}
