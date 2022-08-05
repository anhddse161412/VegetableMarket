/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orderDetail;

/**
 *
 * @author Utech
 */
public class OrderDetailDTO {
    private int detailID;
    private int price;
    private int quantity;
    private int orderID;
    private String productID;

    public OrderDetailDTO() {
    }
    
     public OrderDetailDTO(int price, int quantity, int orderID, String productID) {
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.productID = productID;
    }

    public OrderDetailDTO(int detailID, int price, int quantity, int orderID, String productID) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.productID = productID;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    
}
