package com.flipkart.dao;

    /**
     * @author JEDI-09
     * Interface for payment DAO operations
     */
public interface PaymentDAO {
        /**
         * Adds a payment to the database.
         *
         * @param bookingId the ID of the booking for which the payment is being made
         * @param payment the amount of the payment being made
         * @return the ID of the newly added payment
         */
    public String addPayment(String bookingId, float payment);
}
