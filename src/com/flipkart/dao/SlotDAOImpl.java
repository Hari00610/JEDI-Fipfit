package com.flipkart.dao;

import com.flipkart.bean.Slot;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBConnection;

import java.sql.*;

public class SlotDAOImpl implements SlotDAO {

    private Connection conn = null;
    private PreparedStatement statement = null;


    public SlotDAOImpl() {}

    @Override
    public void addSlot(Slot slot) {
        String query = "INSERT INTO slot (slotId, centreId, time) VALUES (?, ?, ?)"; // Adjust query according to your table schema

        try (Connection connection = DBConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, slot.getSlotId());
            stmt.setString(2, slot.getCentreId());
            stmt.setTime(3, new java.sql.Time(slot.getTime().getTime()));

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " record(s) inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getSlotbyCentreIdAndTimeStamp(String gymCentreId, Timestamp timestamp) {
        String slotId=null;
        try {
            conn = DBConnection.connect();
            assert conn != null;
            statement = conn.prepareStatement(SQLConstants.GET_SLOT_FROM_GYMOWNER_AND_TIMESTAMP);
            statement.setString(1,gymCentreId);
            statement.setTimestamp(2,timestamp);

            ResultSet rs = statement.executeQuery();
            System.out.print(rs);
            while(rs.next()) {
                slotId = rs.getString("slotId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return slotId;
    }

}


