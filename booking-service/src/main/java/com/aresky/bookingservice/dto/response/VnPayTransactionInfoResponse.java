package com.aresky.bookingservice.dto.response;

import com.aresky.bookingservice.model.VnPayTransactionInfo;
import com.aresky.bookingservice.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VnPayTransactionInfoResponse {
    private String bank;
    private String cardType;
    private String orderInfo;
    private String payDate;
    private String transactionNo;
    private String txnRef;
    private Integer amount;

    public VnPayTransactionInfoResponse(VnPayTransactionInfo input) {
        this.bank = input.getBank();
        this.cardType = input.getCardType();
        this.orderInfo = input.getOrderInfo();

        this.payDate = Optional.ofNullable(parseFromString(input.getPayDate()))
                .map(DateUtils::formatDateTime)
                .orElse(null);

        this.transactionNo = input.getTransactionNo();
        this.txnRef = input.getTxnRef();
        this.amount = Integer.parseInt(input.getAmount()) / 100;
    }

    private Date parseFromString(String dateString){
        try {
            return DateUtils.getSimpleDateFormat().parse(dateString);
        } catch (ParseException ex){
            return null;
        }
    }
}
