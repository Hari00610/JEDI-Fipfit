package com.flipkart.dao;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Schedule;
import com.flipkart.constants.SQLConstants;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.UUID;

import static com.flipkart.constants.SQLConstants.ADD_SCHEDULE;


public class ScheduleDAOImpl implements ScheduleDAO{

    private Connection conn = null;
    private PreparedStatement statement = null;

    @Override
    public String addSchedule(Timestamp timestamp, String  slotId) {
        String scheduleId = UUID.randomUUID().toString();
        Connection conn = null;
        try {
            conn = DBConnection.connect();
            SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd");
            String formattedDate = sdf.format(timestamp);
            statement = conn.prepareStatement(ADD_SCHEDULE);
            statement.setString(1, scheduleId);
            statement.setString(2, formattedDate);
            statement.setString(3, slotId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  scheduleId;
    }

    @Override
    public Integer getSlotsBookedCountFromSlotId(String slotId) {
        Integer bookingCount = 0;
        try {
            conn = DBConnection.connect();
            System.out.println("Fetching count of booked slots");

            statement = conn.prepareStatement(SQLConstants.GET_BOOKED_SLOT_COUNT_FROM_SLOTID);
            statement.setString(1, slotId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                bookingCount = rs.getInt("count(*)");
            }

        } catch (SQLException e) {
            System.out.println("SQL error");
            throw new RuntimeException(e);
        }
        return bookingCount;
    }
}
