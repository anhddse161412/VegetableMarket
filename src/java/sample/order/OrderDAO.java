/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author Utech
 */
public class OrderDAO {

    private static final String CREATE = "INSERT INTO tblOrder (orderDate,total,userID,status) values (?,?,?,1)";
    private static final String GET_ORDER_ID = "SELECT TOP 1 orderID FROM tblOrder WHERE userID =?  ORDER BY orderID  DESC ";

    public boolean create(OrderDTO order) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, order.getOrderDate());
                ptm.setInt(2, order.getTotal());
                ptm.setString(3, order.getUserID());

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

    public int getOrderID(String userID) throws SQLException {
        int orderID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_ID);
                ptm.setString(1, userID);

                rs = ptm.executeQuery();
                if (rs.next()) {
                     orderID = rs.getInt("orderID");
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
        return orderID;
    }

}
