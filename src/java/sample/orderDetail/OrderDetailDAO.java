/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author Utech
 */
public class OrderDetailDAO {

    private static final String CREATE = "  INSERT INTO tblOrderDetail(price,quantity,orderID,productID,status) values (?,?,?,?,1)";

    public boolean create(OrderDetailDTO orderDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setInt(1, orderDetail.getPrice());
                ptm.setInt(2, orderDetail.getQuantity());
                ptm.setInt(3, orderDetail.getOrderID());
                ptm.setString(4, orderDetail.getProductID());

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
