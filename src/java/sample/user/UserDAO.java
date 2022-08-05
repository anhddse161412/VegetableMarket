/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Utech
 */
public class UserDAO {

    private static final String LOGIN = "SELECT roleID,fullName,address,phone,birthday,email FROM tblUsers WHERE userID=? AND password=?";
    private static final String SEARCH = "SELECT userID, roleID,fullName,address,phone,birthday,email,password FROM tblUsers WHERE fullName like ? and status='true'";
    private static final String CHECK_DUPLICATE = "SELECT userID FROM tblUsers WHERE userID=?";
    private static final String CREATE = "INSERT INTO tblUsers(userID,fullName,roleID,password,email,address,phone,birthday,status) values(?,?,?,?,?,?,?,?,1)";
    private static final String CHECK_USER = "SELECT userID FROM tblUsers WHERE userID=?";
    private static final String LOGIN_GOOGLE = "SELECT  userID,fullName,address,phone,birthday,email FROM tblUsers WHERE userID =?";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String birthday = rs.getString("birthday");
                    String email = rs.getString("email");
                    user = new UserDTO(userID, fullName, roleID, password, address, birthday, phone, email);
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    
    public UserDTO checkLoginGoogle(String emailLogin) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN_GOOGLE);
                ptm.setString(1, emailLogin);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String password = "";
                    String roleID = "US";
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String birthday = rs.getString("birthday");
                    String email = rs.getString("email");
                    user = new UserDTO(userID, fullName, roleID, password, address, birthday, phone, email);
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public static List<UserDTO> getListUser(String user) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                psm = conn.prepareStatement(SEARCH);
                psm.setString(1, "%" + user + "%");
                rs = psm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String birthday = rs.getString("birthday");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    list.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phone, email));
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next() == true) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkUser(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_USER);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next() == true) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean create(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                ptm.setString(5, user.getEmail());
                ptm.setString(6, user.getAddress());
                ptm.setString(7, user.getPhone());
                ptm.setString(8, user.getBirthday());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
