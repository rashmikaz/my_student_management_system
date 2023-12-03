package org.example.model;



import org.example.db.DbConnection;
import org.example.dto.PlacePaymentDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlacePaymentModel {
    private newPaymentmodel newPaymentmodel = new newPaymentmodel();
    private TobepaidModel tobepaidModel = new TobepaidModel();
    private PaymentDetailModel paymentDetailModel = new PaymentDetailModel();

    public boolean placeOrder(PlacePaymentDto placePaymentDto) throws SQLException {
        System.out.println(placePaymentDto);

        String orderId = placePaymentDto.getOrderId();
        String customerId = placePaymentDto.getCustomerId();
        LocalDate date = placePaymentDto.getDate();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = newPaymentmodel.saveOrder(orderId, customerId, date);
            if (isOrderSaved) {
                boolean isUpdated = tobepaidModel.updatePaid(placePaymentDto.getCartTmList());
                if (isUpdated) {
                    boolean isOrderDetailSaved = paymentDetailModel.savePaymentDetails(placePaymentDto.getOrderId(), placePaymentDto.getCartTmList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
