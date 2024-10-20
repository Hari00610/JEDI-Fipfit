package com.flipkart.dao;

import com.flipkart.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static com.flipkart.constants.SQLConstants.ADD_PAYMENT;

public class PaymentDAOImpl implements PaymentDAO{

    private Connection conn = null;
    private PreparedStatement statement = null;

    @Override
    public String addPayment(String bookingId, float payment) {
        String paymentId = UUID.randomUUID().toString();
        Connection conn = null;
        try {
            conn = DBConnection.connect();
            statement = conn.prepareStatement(ADD_PAYMENT);
            statement.setString(1, paymentId);
            statement.setString(2, bookingId);
            statement.setDouble(3, payment);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  paymentId;
    }
}
