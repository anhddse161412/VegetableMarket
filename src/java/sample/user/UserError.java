/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author Utech
 */
public class UserError {
    private String userID;
    private String fullName;
    private String roleID;
    private String password;
    private String confirm;
    private String message;

    public UserError() {
        this.userID = "";
        this.fullName = "";
        this.roleID = "";
        this.password = "";
        this.confirm = "";
        this.message = "";
    }
    
    public UserError(String message) {
        this.message = message;
    }
    

    public UserError(String userID, String fullName, String roleID, String password, String confirm, String message) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.confirm = confirm;
        this.message = message;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
