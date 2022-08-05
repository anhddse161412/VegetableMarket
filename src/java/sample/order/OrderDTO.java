/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

/**
 *
 * @author Utech
 */
public class OrderDTO {
    private int orderID;
    private String orderDate;
    private int total;
    private String userID;

    public OrderDTO() {
        this.orderDate = "";
        this.total = 0;
        this.userID = "";
    }
    
    public OrderDTO(String orderDate, int total, String userID) {
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
    }

    public OrderDTO(int orderID, String orderDate, int total, String userID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
