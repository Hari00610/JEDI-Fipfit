package com.flipkart.bean;

public class Booking {
    private String bookingId;
    private String userId;
    private String scheduleId;

    public Booking() {
    }

    public Booking(String bookingId, String userId, String scheduleId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", userId='" + userId + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                '}';
    }
}
