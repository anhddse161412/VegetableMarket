/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Utech
 */
public class ProductDTO {

    private String productID;
    private String productName;
    private String image;
    private int price;
    private int quantity;
    private String categoryID;
    private String importDate;
    private String usingDate;

    public ProductDTO() {
        this.productID = "";
        this.productName = "";
        this.image = "";
        this.price = 0;
        this.quantity = 0;
        this.categoryID = "";
        this.importDate = "";
        this.usingDate = "";
    }

    public ProductDTO(String productID, String productName, String image, int price, int quantity, String categoryID, String importDate, String usingDate) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.importDate = importDate;
        this.usingDate = usingDate;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getCateloryID() {
        return categoryID;
    }

    public void setCateloryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(String usingDate) {
        this.usingDate = usingDate;
    }
    
    public boolean checkDate(String importDate, String usingDate) throws ParseException{
        boolean check = false;
        Date iDate= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(importDate);
        Date uDate= (Date) new SimpleDateFormat("yyyy-MM-dd").parse(usingDate);
        if(uDate.compareTo(iDate)>0){
            check = true;
        }
        return check;
    }
    
}
